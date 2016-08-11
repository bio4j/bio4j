package com.bio4j.model.uniref.programs;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.UntypedGraph;


import java.io.*;
import java.util.List;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jdom2.Element;
import com.bio4j.xml.XMLUtils;

public abstract class ImportUniRef<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  public static final String ENTRY_TAG_NAME = "entry";

  private static final Logger logger = Logger.getLogger("ImportUniRef");
  private static FileHandler fh;

  protected abstract UniRefGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  public void importUniRef(File inFile, File dbFolder) {


    final long initTime = System.nanoTime();

    File uniref100File  = new File(inFile, "hola100");
    File uniref90File   = new File(inFile, "hola90");
    File uniref50File   = new File(inFile, "hola50");

    BufferedWriter statsBuff = null;

    int uniref100EntryCounter = 0, uniref90EntryCounter = 0, uniref50EntryCounter = 0;

    logger.log(Level.INFO, "creating manager...");

    final UniRefGraph<I,RV,RVT,RE,RET> uniRefGraph = config(dbFolder);

    try {

      // This block configure the logger with handler and formatter
      fh = new FileHandler("ImportUniRefTitan.log", true);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      //---creating writer for stats file-----
      statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniRefStats.txt")));

      //------------------- UNIREF 100----------------------------
      System.out.println("Reading UniRef 100 file...");
      uniref100EntryCounter = importUniRefFile(uniRefGraph, uniref100File, 100);
      System.out.println("Done! :)");
      System.out.println("Reading UniRef 90 file...");
      uniref90EntryCounter = importUniRefFile(uniRefGraph, uniref90File, 90);
      System.out.println("Done! :)");
      System.out.println("Reading UniRef 50 file...");
      uniref50EntryCounter = importUniRefFile(uniRefGraph, uniref50File, 50);
      System.out.println("Done! :)");


    }
    catch (Exception ex) {

      logger.log(Level.SEVERE, ex.getMessage());
      StackTraceElement[] trace = ex.getStackTrace();
      for (StackTraceElement stackTraceElement : trace) {
        logger.log(Level.SEVERE, stackTraceElement.toString());
      }
    }
    finally {

      try {
        uniRefGraph.raw().commit();
        //closing logger file handler
        fh.close();
        //closing neo4j managers
        uniRefGraph.raw().shutdown();

        //-----------------writing stats file---------------------
        long elapsedTime = System.nanoTime() - initTime;
        long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
        long hours = elapsedSeconds / 3600;
        long minutes = (elapsedSeconds % 3600) / 60;
        long seconds = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportUniRefTitan:\nInput files: " +
        "\nUniRef 100 file: " + uniref100File.getName() +
        "\nUniRef 90 file: " + uniref90File.getName() +
        "\nUniRef 50 file: " + uniref50File.getName()
        + "\nThe following number of entries was parsed:\n"
        + "UniRef 100 --> " + uniref100EntryCounter + " entries\n"
        + "UniRef 90 --> " + uniref90EntryCounter + " entries\n"
        + "UniRef 50 --> " + uniref50EntryCounter + " entries\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();

      } catch (Exception e) {
        logger.log(Level.SEVERE, e.getMessage());
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : trace) {
          logger.log(Level.SEVERE, stackTraceElement.toString());
        }
      }

    }

    System.out.println("Program finished!! :D");
  }

  private static String getRepresentantAccession(Element elem) {
    String result = null;
    Element dbReference = elem.getChild("dbReference");
    List<Element> properties = dbReference.getChildren("property");
    for (Element prop : properties) {
      if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
        result = prop.getAttributeValue("value");
      }
    }

    return result;
  }

  private int importUniRefFile(UniRefGraph<I,RV,RVT,RE,RET> uniRefGraph,
  File unirefFile,
  int unirefClusterNumber) throws Exception {

    StringBuilder entryStBuilder = new StringBuilder();

    BufferedReader reader = new BufferedReader(new FileReader(unirefFile));
    String line;

    int entryCounter = 0;
    int limitForPrintingOut = 10000;

    while ((line = reader.readLine()) != null) {
      //----we reached a entry line-----
      if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

        while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
          entryStBuilder.append(line);
          line = reader.readLine();
        }
        //organism last line
        entryStBuilder.append(line);

        final Element entryXMLElem = XMLUtils.parseXMLFrom(entryStBuilder.toString());
        entryStBuilder.delete(0, entryStBuilder.length());

        String entryId = entryXMLElem.getAttributeValue("id");
        String updatedDate = entryXMLElem.getAttributeValue("updated");
        String name = entryXMLElem.getChildText("name");
        Element representativeMember = entryXMLElem.getChild("representativeMember");
        String representantAccession = getRepresentantAccession(representativeMember);

        if(representantAccession != null){
          if(unirefClusterNumber == 50){
            UniRef50Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.addVertex(uniRefGraph.UniRef50Cluster());
            cluster.set(uniRefGraph.UniRef50Cluster().id, entryId);
            cluster.set(uniRefGraph.UniRef50Cluster().updatedDate, updatedDate);
            cluster.set(uniRefGraph.UniRef50Cluster().name, name);
            cluster.set(uniRefGraph.UniRef50Cluster().representantAccession, representantAccession);
          }else if(unirefClusterNumber == 90){
            UniRef90Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.addVertex(uniRefGraph.UniRef90Cluster());
            cluster.set(uniRefGraph.UniRef90Cluster().id, entryId);
            cluster.set(uniRefGraph.UniRef90Cluster().updatedDate, updatedDate);
            cluster.set(uniRefGraph.UniRef90Cluster().name, name);
            cluster.set(uniRefGraph.UniRef90Cluster().representantAccession, representantAccession);
          }else if(unirefClusterNumber == 100){
            UniRef100Cluster<I,RV,RVT,RE,RET> cluster = uniRefGraph.addVertex(uniRefGraph.UniRef100Cluster());
            cluster.set(uniRefGraph.UniRef100Cluster().id, entryId);
            cluster.set(uniRefGraph.UniRef100Cluster().updatedDate, updatedDate);
            cluster.set(uniRefGraph.UniRef100Cluster().name, name);
            cluster.set(uniRefGraph.UniRef100Cluster().representantAccession, representantAccession);
          }
        }else{
          logger.log(Level.INFO, (entryId + " cluster does not have a valid representant value, it won't be stored... :("));
        }

      }

      entryCounter++;
      if ((entryCounter % limitForPrintingOut) == 0) {
        logger.log(Level.INFO, (entryCounter + " entries parsed!!"));
        uniRefGraph.raw().commit();
      }

    }
    reader.close();

    return entryCounter;
  }
}
