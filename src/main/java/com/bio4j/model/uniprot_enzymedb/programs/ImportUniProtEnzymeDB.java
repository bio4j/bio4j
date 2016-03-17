package com.bio4j.model.uniprot_enzymedb.programs;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.angulillos.UntypedGraph;

import org.jdom2.*;
import com.bio4j.xml.XMLUtils;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ImportUniProtEnzymeDB<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportUniProtEnzymeDB");
  private static FileHandler fh;

  protected abstract UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  public static final String ENTRY_TAG_NAME               = "entry";
  public static final String ENTRY_ACCESSION_TAG_NAME     = "accession";
  public static final String DB_REFERENCE_TAG_NAME        = "dbReference";
  public static final String DB_REFERENCE_TYPE_ATTRIBUTE  = "type";
  public static final String DB_REFERENCE_ID_ATTRIBUTE    = "id";
  public static final String ENZYME_REFERENCE_TYPE        = "EC";

  public void importUniProtEnzymeDB(File inFile, File dbFolder) {

    long initTime = System.nanoTime();

    final UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> uniprotEnzymeDBGraph = config(dbFolder);

    BufferedWriter statsBuff = null;

    int proteinCounter = 0;
    int limitForPrintingOut = 10000;

    try {

      // This block configures the logger with handler and formatter
      fh = new FileHandler("ImportUniProtEnzymeDB.log", false);

      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      //---creating writer for stats file-----
      statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtEnzymeDBStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

      BufferedReader reader = new BufferedReader(new FileReader(inFile));

      /* Iterate over the input file lines */
      final BufferedReader inFileReader = new BufferedReader(new FileReader(inFile));
      String line;
      while((line = inFileReader.readLine()) != null && line.trim().startsWith("<"+ENTRY_TAG_NAME)) {
        // this will advance the reader until the next extry
        final Element entryXMLElem = XMLUtils.uniProtEntryFrom(line, inFileReader);

        String accessionSt = entryXMLElem.getChildText(ENTRY_ACCESSION_TAG_NAME);

        Protein<I,RV,RVT,RE,RET> protein = null;

        //-----db references-------------
        List<Element> dbReferenceList = entryXMLElem.getChildren(DB_REFERENCE_TAG_NAME);

        for (Element dbReferenceElem: dbReferenceList) {

          //-------------------ENZYME DB -----------------------------
          if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).toUpperCase().equals(ENZYME_REFERENCE_TYPE)) {

            if(protein == null) {
              protein = uniprotEnzymeDBGraph.uniProtGraph().proteinAccessionIndex().getVertex(accessionSt).get();
            }

            String enzymeID = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

            if(enzymeID != null) {

              //uniprotEnzymeDBGraph.enzymeDBGraph().enzymeIdIndex()

              Optional<Enzyme<I,RV,RVT,RE,RET>> enzymeOptional = uniprotEnzymeDBGraph.enzymeDBGraph().enzymeIdIndex().getVertex(enzymeID);

              if(enzymeOptional.isPresent()) {
                protein.addOutEdge(uniprotEnzymeDBGraph.EnzymaticActivity(), enzymeOptional.get());
              }
              else {

                logger.log(Level.INFO, "The enzyme with id: " + enzymeID + " could not be found... :|");
              }
            }
            else {

              logger.log(Level.INFO, "Null enzyme id found for protein: " + accessionSt);
            }
          }
          //---------------------------------------------------------------------------------------
          proteinCounter++;
          if ((proteinCounter % limitForPrintingOut) == 0) {

            String countProteinsSt = proteinCounter + " proteins updated!!";
            logger.log(Level.INFO, countProteinsSt);
          }
        }
      }
    }
    catch (Exception e) {

      logger.log(Level.SEVERE, e.getMessage());
      StackTraceElement[] trace = e.getStackTrace();
      for (StackTraceElement stackTraceElement : trace) {
        logger.log(Level.SEVERE, stackTraceElement.toString());
      }
    }
    finally {

      try {
        //------closing writers-------

        // shutdown, makes sure all changes are written to disk
        uniprotEnzymeDBGraph.raw().shutdown();

        // closing logger file handler
        fh.close();

        //-----------------writing stats file---------------------
        long elapsedTime      = System.nanoTime() - initTime;
        long elapsedSeconds   = Math.round((elapsedTime / 1000000000.0));
        long hours            = elapsedSeconds / 3600;
        long minutes          = (elapsedSeconds % 3600) / 60;
        long seconds          = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportUniProtEnzymeDB:\nInput file: " + inFile.getName()
        + "\nThere were " + proteinCounter + " proteins analyzed.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();

      }
      catch (IOException ex) {

        Logger.getLogger(ImportUniProtEnzymeDB.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
