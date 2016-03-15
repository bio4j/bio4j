package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;

import static com.bio4j.model.uniprot.programs.XMLConstants.*;

import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

      /*
        Iterate over the input file lines. We'd like to replace this with a `Stream<String>`-based solution, but there's no easy lazy way of partitioning streams (going from lines to `XMLElement` entries).
      */
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

  /*
    By direct inspection of UniProt XML file I have concluded that:

    1. the source id is always a protein, I don't know why
    2. target can be either an isoform or a protein, and the id is in `interactant/id`. Isoform ids *look* to be `${protein.id}-{number}`
    3. the `intactId`s have nothing to do with UniProt, and should be dropped

    Note that because of 1. it would be easier if we passed the protein as an argument.
  */
  private void importProteinInteractionsWithSource(
    XMLElement entryXMLElem,
    UniProtGraph<I,RV,RVT,RE,RET> graph
  )
  {

    /* We first get the protein from the entry xml element accession */
    final Optional<Protein<I,RV,RVT,RE,RET>> optionalSrcProtein = graph.proteinAccessionIndex()
      .getVertex(entryXMLElem.asJDomElement().getChildren(ENTRY.ACCESSION.element).get(0).getText());

    optionalSrcProtein.ifPresent(
      srcProtein -> {
        entryXMLElem.asJDomElement().getChildren(ENTRY.COMMENT.element)
          .stream()
          .filter(
            commentElem ->
              commentElem.getAttributeValue(ENTRY.COMMENT.TYPE.attribute).equals(ENTRY.COMMENT.TYPE.INTERACTION)
          )
          .forEach(
            commentElem -> {
              /* these two elements are required by the schema */
              final Element srcInteractant  = commentElem
                .getChildren(ENTRY.COMMENT.INTERACTANT.element).get(0);
              final String srcInteractantId = srcInteractant
                .getAttributeValue(ENTRY.COMMENT.INTERACTANT.INTACTID.attribute);
              final Element tgtInteractant  = commentElem
                .getChildren(ENTRY.COMMENT.INTERACTANT.element).get(1);
              final String tgtInteractantId = tgtInteractant
                .getAttributeValue(ENTRY.COMMENT.INTERACTANT.INTACTID.attribute);
              /* this is always there, but it is not documented */
              final String tgtId = tgtInteractant
                .getChildText(ENTRY.COMMENT.INTERACTANT.ID.element);
              /* we now try to get the target protein from the accession index; if it's there, create a protein-protein interaction edge, otherwise a protein-isoform one */
              final Optional<Protein<I,RV,RVT,RE,RET>> optionalTgtProtein = graph.proteinAccessionIndex()
                .getVertex(tgtId);
              // tgt is a protein
              if(optionalTgtProtein.isPresent()) {
                // create edge, set properties
                final ProteinProteinInteraction<I,RV,RVT,RE,RET> edge =
                  srcProtein.addOutEdge(graph.ProteinProteinInteraction(), optionalTgtProtein.get());
                Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.ORGANISMSDIFFER.element) ).ifPresent(
                  elem -> edge.set(edge.type().organismsDiffer, elem.getText())
                );
                Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.EXPERIMENTS.element) ).ifPresent(
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
                      Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.ORGANISMSDIFFER.element) ).ifPresent(
                        elem -> edge.set(edge.type().organismsDiffer, elem.getText())
                      );
                      Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.EXPERIMENTS.element) ).ifPresent(
                        elem -> edge.set(edge.type().experiments, elem.getText())
                      );
                      edge.set(edge.type().intActId1, srcInteractantId);
                      edge.set(edge.type().intActId2, tgtInteractantId);
                    }
                  );
              }
            }
        );
      }
    );
  }
}
