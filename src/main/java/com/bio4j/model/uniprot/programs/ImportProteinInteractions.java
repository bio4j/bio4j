package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;

import static com.bio4j.model.uniprot.programs.XMLConstants.*;

import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ImportProteinInteractions<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportProteinInteractions");
  private static FileHandler fh;

  final int limitForPrintingOut = 10000;

  protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  protected void importProteinInteractions(File inFile, File dbFolder) {

    final long initTime = System.nanoTime();

    final UniProtGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

    String currentAccessionId = "";

    BufferedWriter statsBuff = null;

    int proteinCounter = 0;

    try {

      // This block configures the logger with handler and formatter
      fh = new FileHandler("ImportProteinInteractions.log", false);

      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      //---creating writer for stats file-----
      statsBuff = new BufferedWriter(new FileWriter(new File("ImportProteinInteractionsStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

      final BufferedReader inFileReader = new BufferedReader(new FileReader(inFile));

      String line;
      final StringBuilder entryStBuilder = new StringBuilder();

      /* Iterate over the input file lines */
      while((line = inFileReader.readLine()) != null) {

        if(line.trim().startsWith("<"+ENTRY_TAG_NAME)) {

          while(!line.trim().startsWith("</"+ENTRY_TAG_NAME+">")) {

            entryStBuilder.append(line);
            line = inFileReader.readLine();
          }
          entryStBuilder.append(line);

          final XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
          entryStBuilder.delete(0, entryStBuilder.length());

          importProteinInteractionsWithSource(entryXMLElem, graph);

          proteinCounter++;

          if((proteinCounter % limitForPrintingOut) == 0) {

            logger.log(Level.INFO, (proteinCounter + " proteins updated with interactions!!"));
          }
        }
      }
    }
    catch(Exception e) {
      logger.log(Level.SEVERE, ("Exception retrieving protein " + currentAccessionId));
      logger.log(Level.SEVERE, e.getMessage());
      StackTraceElement[] trace = e.getStackTrace();
      for (StackTraceElement stackTraceElement : trace) {
        logger.log(Level.SEVERE, stackTraceElement.toString());
      }
    }
    finally {

      try {

        // shutdown, makes sure all changes are written to disk
        graph.raw().shutdown();

        // closing logger file handler
        fh.close();

        //-----------------writing stats file---------------------
        long elapsedTime    = System.nanoTime() - initTime;
        long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
        long hours          = elapsedSeconds / 3600;
        long minutes        = (elapsedSeconds % 3600) / 60;
        long seconds        = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportProteinInteractions:\nInput file: " + inFile.getName()
        + "\nThere were " + proteinCounter + " proteins inserted.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();

      } catch (IOException ex) {

        Logger.getLogger(ImportProteinInteractions.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private void importProteinInteractionsWithSource(
    XMLElement entryXMLElem,
    UniProtGraph<I,RV,RVT,RE,RET> graph
  )
  {
    // get elements
    entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME)
      .stream()
      .filter(
        commentElem -> commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE).equals(COMMENT_TYPE_INTERACTION)
      )
      .forEach(

        commentElem -> {

          final Element srcInteractant  = commentElem.getChildren("interactant").get(0);
          final String srcInteractantId = srcInteractant.getChildText("id");

          final Element tgtInteractant  = commentElem.getChildren("interactant").get(1);
          final String tgtInteractantId = tgtInteractant.getChildText("id");

          final Optional<Protein<I,RV,RVT,RE,RET>> optionalSrcProtein =
            graph.proteinAccessionIndex().getVertex(srcInteractantId);

          // src is a protein
          if(optionalSrcProtein.isPresent()) {

            final Optional<Protein<I,RV,RVT,RE,RET>> optionalTgtProtein = graph.proteinAccessionIndex()
              .getVertex(tgtInteractantId);

            // tgt is a protein
            if(optionalTgtProtein.isPresent()) {

              // create edge, set properties
              final ProteinProteinInteraction<I,RV,RVT,RE,RET> edge =
                optionalSrcProtein.get().addOutEdge(graph.ProteinProteinInteraction(), optionalTgtProtein.get());

              Optional.ofNullable( commentElem.getChild("organismsDiffer") ).ifPresent(
                elem -> edge.set(edge.type().organismsDiffer, elem.getText())
              );
              Optional.ofNullable( commentElem.getChild("experiments") ).ifPresent(
                elem -> edge.set(edge.type().experiments, elem.getText())
              );
              edge.set(edge.type().intActId1, srcInteractantId);
              edge.set(edge.type().intActId2, tgtInteractantId);
            }
            // tgt is an isoform, or just crap
            else {

              graph.isoformIdIndex()
                .getVertex(tgtInteractantId)
                .ifPresent(
                  tgtIsoform -> {
                    // create edge, set properties
                    final ProteinIsoformInteraction<I,RV,RVT,RE,RET> edge =
                      optionalSrcProtein.get().addOutEdge(graph.ProteinIsoformInteraction(), tgtIsoform);

                    Optional.ofNullable( commentElem.getChild("organismsDiffer") ).ifPresent(
                      elem -> edge.set(edge.type().organismsDiffer, elem.getText())
                    );
                    Optional.ofNullable( commentElem.getChild("experiments") ).ifPresent(
                      elem -> edge.set(edge.type().experiments, elem.getText())
                    );
                    edge.set(edge.type().intActId1, srcInteractantId);
                    edge.set(edge.type().intActId2, tgtInteractantId);
                  }
                );
            }
          }
          // src is an isoform, or just crap
          else {

            graph.isoformIdIndex()
              .getVertex(srcInteractantId)
              .ifPresent(
                srcIsoform -> {

                  final Optional<Protein<I,RV,RVT,RE,RET>> optionalTgtProtein = graph.proteinAccessionIndex()
                    .getVertex(tgtInteractantId);

                  // tgt is a protein
                  if(optionalTgtProtein.isPresent()) {

                    // create edge, set properties
                    final IsoformProteinInteraction<I,RV,RVT,RE,RET> edge =
                      srcIsoform.addOutEdge(graph.IsoformProteinInteraction(), optionalTgtProtein.get());

                    Optional.ofNullable( commentElem.getChild("organismsDiffer") ).ifPresent(
                      elem -> edge.set(edge.type().organismsDiffer, elem.getText())
                    );
                    Optional.ofNullable( commentElem.getChild("experiments") ).ifPresent(
                      elem -> edge.set(edge.type().experiments, elem.getText())
                    );
                    edge.set(edge.type().intActId1, srcInteractantId);
                    edge.set(edge.type().intActId2, tgtInteractantId);
                  }
                  // tgt is an isoform, or just crap
                  else {
                    graph.isoformIdIndex()
                      .getVertex(tgtInteractantId)
                      .ifPresent(
                        // TODO what's up with IsoformIsoformInteraction?
                        tgtIsoform -> {}
                      );
                  }
                }
              );
          }
      }
    );
  }
}
