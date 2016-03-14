package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ImportIsoformSequences<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportIsoformSequences");
  private static FileHandler fh;

  protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  public void importIsoformSequences(File inFile, File dbFolder) {

    final long initTime = System.nanoTime();

    int isoformCounter = 0;

    UniProtGraph<I,RV,RVT,RE,RET> uniProtGraph = config(dbFolder);

    String isoformIdSt = null;
    BufferedWriter statsBuff = null;

    try {

      // This block configures the logger with handler and formatter
      fh = new FileHandler("ImporIsoformSequences.log", true);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      statsBuff = new BufferedWriter(new FileWriter(new File("ImportIsoformSequencesStats.txt")));

      BufferedReader reader = new BufferedReader(new FileReader(inFile));
      String line;
      StringBuilder seqStBuilder = new StringBuilder();

      logger.log(Level.INFO, "updating isoforms data");

      // this parses a fasta file I guess
      while((line = reader.readLine()) != null) {

        // got the header
        if(line.trim().startsWith(">")) {

          String[] columns = line.split("\\|");

          isoformIdSt = columns[1];

          String isoformNameSt = columns[2].split("OS=")[0].trim();

          //sequence read line
          line = reader.readLine();

          // accumulate the sequence lines
          // TODO I *think* that this will skip every other isoform
          while (!line.trim().startsWith(">")) {

            seqStBuilder.append(line);
            line = reader.readLine();

            if (line == null) {
              break;
            }
          }

          final String sequence = seqStBuilder.toString();
          seqStBuilder.delete(0, seqStBuilder.length());

          // TODO clean this a bit
          uniProtGraph.isoformIdIndex()
            .getVertex(isoformIdSt)
            .ifPresent(
              isoform -> {

                isoform.set(uniProtGraph.Isoform().sequence, sequence);
                isoform.set(uniProtGraph.Isoform().name, isoformNameSt);

                // isoformCounter++;
              }
            );
        }

        if(line == null) {

          break;
        }
      }

      reader.close();

      logger.log(Level.INFO, "Done! :)");

    }
    catch(Exception e) {

      logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
      logger.log(Level.SEVERE, e.getMessage());
      StackTraceElement[] trace = e.getStackTrace();
      for (StackTraceElement stackTraceElement: trace) {

        logger.log(Level.SEVERE, stackTraceElement.toString());
      }
    }
    finally {

      uniProtGraph.raw().commit();

      //closing logger file handler
      fh.close();
      logger.log(Level.INFO, "Closing up graph....");
      // shutdown, makes sure all changes are written to disk
      uniProtGraph.raw().shutdown();

      try {

        //-----------------writing stats file---------------------
        long elapsedTime    = System.nanoTime() - initTime;
        long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
        long hours          = elapsedSeconds / 3600;
        long minutes        = (elapsedSeconds % 3600) / 60;
        long seconds        = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportIsoformSequences:\nInput file: " + inFile.getName()
        + "\nThere were " + isoformCounter + " isoforms updated.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();

      }
      catch(Exception e) {

        e.printStackTrace();
      }
    }
  }
}
