/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.era7.bioinfo.bio4j.titan.programs;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.CDSNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GeneNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.rna.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.refseq.*;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfo.bioinfoutil.genbank.GBCommon;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.TransactionalGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Imports RefSeq complete release into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportRefSeqTitan implements Executable {

    private static final Logger logger = Logger.getLogger("ImportRefSeqTitan");
    private static FileHandler fh;

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {


        if (args.length != 2) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Folder name with all the .gbk files \n"
                    + "2. Bio4j DB folder \n");
        } else {

            long initTime = System.nanoTime();

            File inFolder = new File(args[0]);

            File[] files = inFolder.listFiles();

            BufferedWriter statsBuff = null;
            Bio4jManager manager = null;
            int genomeElementCounter = 0;
            int limitForPrintingOut = 1000;
            int limitForTransaction = 100;

            try {
                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportRefSeqTitan.log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportRefSeqTitanStats.txt")));

                //----------DB configuration------------------
                Configuration conf = new BaseConfiguration();
                conf.setProperty("storage.directory", args[1]);
                conf.setProperty("storage.backend", "local");

                //-------creating graph handlers---------------------
                manager = new Bio4jManager(conf);
                TitanGraph graph = manager.getGraph();

                for (File file : files) {
                    if (file.getName().endsWith(".gbff")) {

                        logger.log(Level.INFO, ("file: " + file.getName()));

                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = reader.readLine()) != null) {

                            //this is the first line where the locus is
                            String accessionSt = "";
                            String definitionSt = "";
                            String versionSt = "";
                            String commentSt = "";
                            StringBuilder seqStBuilder = new StringBuilder();

                            ArrayList<String> cdsList = new ArrayList<String>();
                            ArrayList<String> geneList = new ArrayList<String>();
                            ArrayList<String> miscRnaList = new ArrayList<String>();
                            ArrayList<String> mRnaList = new ArrayList<String>();
                            ArrayList<String> ncRnaList = new ArrayList<String>();
                            ArrayList<String> rRnaList = new ArrayList<String>();
                            ArrayList<String> tmRnaList = new ArrayList<String>();
                            ArrayList<String> tRnaList = new ArrayList<String>();

                            boolean originFound = false;

                            //Now I get all the lines till I reach the string '//'
                            do {
                                boolean readLineFlag = true;

                                if (line.startsWith(GBCommon.LOCUS_STR)) {
                                    // do nothing right now
                                } else if (line.startsWith(GBCommon.ACCESSION_STR)) {

                                    accessionSt = line.split(GBCommon.ACCESSION_STR)[1].trim();

                                } else if (line.startsWith(GBCommon.VERSION_STR)) {

                                    versionSt = line.split(GBCommon.VERSION_STR)[1].trim().split(" ")[0];

                                } else if (line.startsWith(GBCommon.DEFINITION_STR)) {

                                    definitionSt += line.split(GBCommon.DEFINITION_STR)[1].trim();
                                    do {
                                        line = reader.readLine();
                                        if (line.startsWith(" ")) {
                                            definitionSt += line.trim();
                                        }
                                    } while (line.startsWith(" "));
                                    readLineFlag = false;

                                } else if (line.startsWith(GBCommon.COMMENT_STR)) {

                                    commentSt += line.split(GBCommon.COMMENT_STR)[1].trim();
                                    do {
                                        line = reader.readLine();
                                        if (line.startsWith(" ")) {
                                            commentSt += "\n" + line.trim();
                                        }
                                    } while (line.startsWith(" "));
                                    readLineFlag = false;

                                } else if (line.startsWith(GBCommon.FEATURES_STR)) {


                                    do {
                                        line = reader.readLine();

                                        String lineSubstr5 = line.substring(5);

                                        if (lineSubstr5.startsWith(GBCommon.CDS_STR)) {
                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.CDS_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            cdsList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.GENE_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.GENE_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            geneList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.MISC_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.MISC_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            miscRnaList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.TM_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.TM_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            tmRnaList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.R_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.R_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            rRnaList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.M_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.M_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            mRnaList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.NC_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.NC_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            ncRnaList.add(positionsSt);

                                        } else if (lineSubstr5.startsWith(GBCommon.T_RNA_STR)) {

                                            String positionsSt = "";
                                            positionsSt += line.trim().split(GBCommon.T_RNA_STR)[1].trim();

                                            line = reader.readLine();

                                            while (!line.trim().startsWith("/")) {
                                                positionsSt += line.trim();
                                                line = reader.readLine();
                                            }

                                            tRnaList.add(positionsSt);

                                        }

                                    } while (line.startsWith(" "));
                                    readLineFlag = false;

                                } else if (line.startsWith(GBCommon.ORIGIN_STR)) {

                                    originFound = true;

                                    do {
                                        line = reader.readLine();
                                        String[] tempArray = line.trim().split(" ");
                                        for (int i = 1; i < tempArray.length; i++) {
                                            seqStBuilder.append(tempArray[i]);
                                        }

                                    } while (line.startsWith(" "));
                                    readLineFlag = false;
                                }

                                if (readLineFlag) {
                                    line = reader.readLine();
                                }



                            } while (line != null && !line.startsWith(GBCommon.LAST_LINE_STR));

                            GenomeElementNode genomeElementNode = new GenomeElementNode(manager.createNode(GenomeElementNode.NODE_TYPE));
                            genomeElementNode.setVersion(versionSt);
                            genomeElementNode.setComment(commentSt);
                            genomeElementNode.setDefinition(definitionSt);
                            
                            //-----------genes-----------------
                            for (String genePositionsSt : geneList) {
                                GeneNode geneNode = new GeneNode(manager.createNode(GeneNode.NODE_TYPE));
                                geneNode.setPositions(genePositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), geneNode.getNode(), GenomeElementGeneRel.NAME);
                            }

                            //-----------CDS-----------------
                            for (String cdsPositionsSt : cdsList) {
                                CDSNode cDSNode = new CDSNode(manager.createNode(CDSNode.NODE_TYPE));
                                cDSNode.setPositions(cdsPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), cDSNode.getNode(), GenomeElementCDSRel.NAME);
                            }

                            //-----------misc rna-----------------
                            for (String miscRnaPositionsSt : miscRnaList) {
                                MiscRNANode miscRNANode = new MiscRNANode(manager.createNode(MiscRNANode.NODE_TYPE));
                                miscRNANode.setPositions(miscRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), miscRNANode.getNode(), GenomeElementMiscRnaRel.NAME);
                            }

                            //-----------m rna-----------------
                            for (String mRnaPositionsSt : mRnaList) {
                                MRNANode mRNANode = new MRNANode(manager.createNode(MRNANode.NODE_TYPE));
                                mRNANode.setPositions(mRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), mRNANode.getNode(), GenomeElementMRnaRel.NAME);
                            }

                            //-----------nc rna-----------------
                            for (String ncRnaPositionsSt : ncRnaList) {
                                NcRNANode ncRNANode = new NcRNANode(manager.createNode(NcRNANode.NODE_TYPE));
                                ncRNANode.setPositions(ncRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), ncRNANode.getNode(), GenomeElementNcRnaRel.NAME);
                            }

                            //-----------r rna-----------------
                            for (String rRnaPositionsSt : rRnaList) {
                                RRNANode rRNANode = new RRNANode(manager.createNode(RRNANode.NODE_TYPE));
                                rRNANode.setPositions(rRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), rRNANode.getNode(), GenomeElementRRnaRel.NAME);
                            }

                            //-----------tm rna-----------------
                            for (String tmRnaPositionsSt : tmRnaList) {
                                TmRNANode tmRNANode = new TmRNANode(manager.createNode(TmRNANode.NODE_TYPE));
                                tmRNANode.setPositions(tmRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), tmRNANode.getNode(), GenomeElementTmRnaRel.NAME);
                            }

                            //-----------t rna-----------------
                            for (String tRnaPositionsSt : tRnaList) {
                                TRNANode tRNANode = new TRNANode(manager.createNode(TRNANode.NODE_TYPE));
                                tRNANode.setPositions(tRnaPositionsSt);
                                graph.addEdge(null, genomeElementNode.getNode(), tRNANode.getNode(), GenomeElementTRnaRel.NAME);
                            }

                            //logger.log(Level.INFO, (versionSt + " saved!"));

                            genomeElementCounter++;
                            
                            
                            if(genomeElementCounter % limitForTransaction == 0){
                                manager.getGraph().stopTransaction(TransactionalGraph.Conclusion.SUCCESS);
                            }
                            if((genomeElementCounter % limitForPrintingOut) == 0){                                
                                logger.log(Level.INFO, (genomeElementCounter + " genome elements stored..."));
                            }

                        }

                    }
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                // shutdown db manager
                manager.shutDown();

                try {
                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportRefSeq:\nInput folder: " + inFolder.getName()
                            + "\nThere were " + genomeElementCounter + " genome elements stored.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // closing logger file handler
                fh.close();

            }
        }

    }

}
