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


/**
* Imports the sequences of isoforms into Bio4j
*
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public abstract class ImportIsoformSequences<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportIsoformSequences");
  private static FileHandler fh;

  protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(String dbFolder, String propertiesFile);

  public void importIsoformSequences(String[] args) {

    if (args.length != 3) {
      System.out.println("This program expects two parameters: \n"
      + "1. Fasta file including all isoforms \n"
      + "2. Bio4j DB folder \n"
      + "3. DB Properties file (.properties)");
    } else {

      long initTime = System.nanoTime();

      File inFile = new File(args[0]);
      String dbFolder = args[1];
      String propertiesFile = args[2];

      String isoformIdSt = null;

      BufferedWriter statsBuff = null;

      UniProtGraph<I,RV,RVT,RE,RET> uniProtGraph = config(dbFolder, propertiesFile);

      int isoformCounter = 0;

      try {

        // This block configures the logger with handler and formatter
        fh = new FileHandler("ImporIsoformSequences.log", true);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);

        //---creating writer for stats file-----
        statsBuff = new BufferedWriter(new FileWriter(new File("ImportIsoformSequencesStats.txt")));

        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String line;
        StringBuilder seqStBuilder = new StringBuilder();

        logger.log(Level.INFO, "updating isoforms data....");

        while ((line = reader.readLine()) != null) {
          if (line.trim().startsWith(">")) {

            String[] columns = line.split("\\|");
            isoformIdSt = columns[1];
            String isoformNameSt = columns[2].split("OS=")[0].trim();

            //sequence read line
            line = reader.readLine();

            while (!line.trim().startsWith(">")) {
              seqStBuilder.append(line);
              line = reader.readLine();
              if (line == null) {
                break;
              }
            }

            String sequence = seqStBuilder.toString();
            seqStBuilder.delete(0, seqStBuilder.length());

            Optional<Isoform<I,RV,RVT,RE,RET>> isoformOptional = uniProtGraph.isoformIdIndex().getVertex(isoformIdSt);

            if(isoformOptional.isPresent()){
              Isoform<I,RV,RVT,RE,RET> isoform = isoformOptional.get();
              isoform.set(uniProtGraph.Isoform().sequence, sequence);
              isoform.set(uniProtGraph.Isoform().name, isoformNameSt);
              System.out.println("Setting name for: " + isoform.id());
              isoformCounter++;
            }

          }

          if (line == null) {
            break;
          }

        }

        reader.close();

        logger.log(Level.INFO, "Done! :)");

      } catch (Exception e) {
        logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
        logger.log(Level.SEVERE, e.getMessage());
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : trace) {
          logger.log(Level.SEVERE, stackTraceElement.toString());
        }
      } finally {

        uniProtGraph.raw().commit();

        //closing logger file handler
        fh.close();
        logger.log(Level.INFO, "Closing up graph....");
        // shutdown, makes sure all changes are written to disk
        uniProtGraph.raw().shutdown();

        try {

          //-----------------writing stats file---------------------
          long elapsedTime = System.nanoTime() - initTime;
          long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
          long hours = elapsedSeconds / 3600;
          long minutes = (elapsedSeconds % 3600) / 60;
          long seconds = (elapsedSeconds % 3600) % 60;

          statsBuff.write("Statistics for program ImportIsoformSequences:\nInput file: " + inFile.getName()
          + "\nThere were " + isoformCounter + " isoforms updated.\n"
          + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

          //---closing stats writer---
          statsBuff.close();

        }catch(Exception e){
          e.printStackTrace();
        }

      }
    }
  }
}
