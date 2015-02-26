package com.bio4j.model.ucsc.programs;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;


public abstract class ImportUCSCGenes<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET> {

    private static final Logger logger = Logger.getLogger("ImportUCSCGenes");
    private static FileHandler fh;

    protected abstract UCSCGenesGraph<I, RV, RVT, RE, RET> config(String dbFolder, String propertiesFile);

    public void importUCSCGenes(String[] args) {

        if (args.length != 3) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Some filename \n"
                    + "2. Bio4j DB folder \n"
                    + "3. DB Properties file (.properties)");
        } else {

            long initTime = System.nanoTime();

            int chromosomeCounter = 0;
            int limitForTransaction = 1000;

            BufferedWriter statsBuff = null;

            File nodesDumpFile = new File(args[0]);
            String dbFolder = args[1];
            String propertiesFile = args[2];

            logger.log(Level.INFO, "creating manager...");

            UCSCGenesGraph<I, RV, RVT, RE, RET> ucscGenesGraph = config(dbFolder, propertiesFile);


            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUCSCGenes.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportUCSCGenesStats.txt")));

                BufferedReader reader = new BufferedReader(new FileReader(nodesDumpFile));
                String line;


                logger.log(Level.INFO, "reading nodes file...");

                for (int i = 1; i <= 22; i++) {

                    UCSCGenesChromosome<I, RV, RVT, RE, RET> chromosome = ucscGenesGraph.addVertex(ucscGenesGraph.UCSCGenesChromosome());
                    chromosome.set(ucscGenesGraph.UCSCGenesChromosome().id, "chr" + i);

                    chromosomeCounter++;

                    if ((chromosomeCounter % limitForTransaction) == 0) {
                        ucscGenesGraph.raw().commit();
                    }
                }

                reader.close();
                ucscGenesGraph.raw().commit();
                logger.log(Level.INFO, "done!");


            } catch (Exception ex) {
                Logger.getLogger(ImportUCSCGenes.class.getName()).log(Level.SEVERE, null, ex);

            } finally {

                //committing last transaction
                ucscGenesGraph.raw().commit();
                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                ucscGenesGraph.raw().shutdown();

                try {

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportUCSCGenes:\nInput file: " + nodesDumpFile.getName()
                            + "\nThere were " + chromosomeCounter + " chromosome units inserted.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}