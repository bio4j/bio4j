package com.bio4j.model.ucsc_uniprot.programs;

import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;
import com.bio4j.model.ucsc_uniprot.UCSCGenesUniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;


import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.GZIPInputStream;

/**
 * @author Evdokim Kovach</a>
 */
public abstract class ImportUCSCGenesUniProt<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET> {

    private static final Logger logger = Logger.getLogger("ImportUCSCGenesUniProt");
    private static FileHandler fh;


    protected abstract UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> config(String dbFolder, String propertiesFile);


    public void importUCSCGenesUniProt(String[] args) {

        if (args.length != 3) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. knownGene.txt.gz filename \n"
                    + "2. Bio4j DB folder \n"
                    + "3. DB Properties file (.properties)");
        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);
            String dbFolder = args[1];
            String propertiesFile = args[2];

            UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> ucscGenesUniProtGraph = config(dbFolder, propertiesFile);

            BufferedWriter statsBuff = null;

            int chromosomeProteinCounter = 1;
            int limitForPrintingOut = 10000;

            try {

                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportUCSCGenesUniProt" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportUCSCGenesUniProtStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

                BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(inFile))));
                StringBuilder entryStBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\\t");
                    String name = columns[0];
                    String chrom = columns[1];
                    String strand = columns[2];
                    String txStart = columns[3];
                    String txEnd = columns[4];
                    String cdsStart = columns[5];
                    String cdsEnd = columns[6];
                    String exonCount = columns[7];
                    String exonStarts = columns[8];
                    String exonEnds = columns[9];
                    String proteinID = columns[10];
                    String alignID = columns[11];

                    if (!proteinID.isEmpty() && !chrom.isEmpty()) {

                        Optional<Protein<I, RV, RVT, RE, RET>> proteinOptional = ucscGenesUniProtGraph.uniProtGraph().proteinAccessionIndex().getVertex(proteinID);
                        if (proteinOptional.isPresent()) {

                            Protein<I, RV, RVT, RE, RET> protein = proteinOptional.get();

                            Optional<UCSCGenesChromosome<I, RV, RVT, RE, RET>> ucscGenesChromosomeOptional = ucscGenesUniProtGraph.ucscGenesGraph().ucscGenesChromosomeIdIndex().getVertex(chrom);
                            if (ucscGenesChromosomeOptional.isPresent()) {
                                ucscGenesChromosomeOptional.get().addOutEdge(ucscGenesUniProtGraph.UCSCGenesChromosomeProtein(), protein);
                                chromosomeProteinCounter++;
                            } else {
                                logger.warning("chromosome " + chrom + " not found");
                            }
                        } else {
                            logger.warning("protein " + proteinID + " not found");
                        }
                        //---------------------------------------------------------------------------------------
                    }

                    if ((chromosomeProteinCounter % limitForPrintingOut) == 0) {
                        String countProteinsSt = chromosomeProteinCounter + " proteins updated!!";
                        logger.log(Level.INFO, countProteinsSt);
                    }
                }


            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                try {
                    //------closing writers-------

                    // shutdown, makes sure all changes are written to disk
                    ucscGenesUniProtGraph.raw().shutdown();

                    // closing logger file handler
                    fh.close();

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportUCSCGenesUniProt:\nInput file: " + inFile.getName()
                            + "\nThere were " + chromosomeProteinCounter + " positions inserted.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();


                } catch (IOException ex) {
                    Logger.getLogger(ImportUCSCGenesUniProt.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

}