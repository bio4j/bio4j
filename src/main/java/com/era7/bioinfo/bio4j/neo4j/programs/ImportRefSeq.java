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
package com.era7.bioinfo.bio4j.neo4j.programs;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.CDSNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.GeneNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.rna.*;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.refseq.*;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfo.bioinfoutil.genbank.GBCommon;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.*;

/**
 * Imports RefSeq complete release into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportRefSeq implements Executable {

    //--------indexing API constans-----
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    //-----------------------------------
    public static final String BASE_FOLDER = "refseq/release/complete/";
    private static final Logger logger = Logger.getLogger("ImportRefSeq");
    private static FileHandler fh;
    //------------------nodes properties maps-----------------------------------
    public static Map<String, Object> genomeElementProperties = new HashMap<String, Object>();
    public static Map<String, Object> geneProperties = new HashMap<String, Object>();
    public static Map<String, Object> cdsProperties = new HashMap<String, Object>();
    public static Map<String, Object> miscRnaProperties = new HashMap<String, Object>();
    public static Map<String, Object> mRnaProperties = new HashMap<String, Object>();
    public static Map<String, Object> ncRnaProperties = new HashMap<String, Object>();
    public static Map<String, Object> rRnaProperties = new HashMap<String, Object>();
    public static Map<String, Object> tmRnaProperties = new HashMap<String, Object>();
    public static Map<String, Object> tRnaProperties = new HashMap<String, Object>();
    //----------------------------------------------------------------------------------
    //--------------------------------relationships------------------------------------------
    public static GenomeElementGeneRel genomeElementGeneRel = new GenomeElementGeneRel(null);
    public static GenomeElementCDSRel genomeElementCDSRel = new GenomeElementCDSRel(null);
    public static GenomeElementMiscRnaRel genomeElementMiscRnaRel = new GenomeElementMiscRnaRel(null);
    public static GenomeElementMRnaRel genomeElementMRnaRel = new GenomeElementMRnaRel(null);
    public static GenomeElementNcRnaRel genomeElementNcRnaRel = new GenomeElementNcRnaRel(null);
    public static GenomeElementRRnaRel genomeElementRRnaRel = new GenomeElementRRnaRel(null);
    public static GenomeElementTmRnaRel genomeElementTmRnaRel = new GenomeElementTmRnaRel(null);
    public static GenomeElementTRnaRel genomeElementTRnaRel = new GenomeElementTRnaRel(null);
    //----------------------------------------------------------------------------------

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {


        if (args.length != 3) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Folder name with all the .gbk files \n"
                    + "2. Bio4j DB folder \n"
                    + "3. batch inserter .properties file");
        } else {

            long initTime = System.nanoTime();

            File inFolder = new File(args[0]);

            File[] files = inFolder.listFiles();

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;


            //----------------------------------------------------------------------------------
            //---------------------initializing node type properties----------------------------
            genomeElementProperties.put(GenomeElementNode.NODE_TYPE_PROPERTY, GenomeElementNode.NODE_TYPE);
            geneProperties.put(GeneNode.NODE_TYPE_PROPERTY, GeneNode.NODE_TYPE);
            cdsProperties.put(CDSNode.NODE_TYPE_PROPERTY, CDSNode.NODE_TYPE);
            miscRnaProperties.put(MiscRNANode.NODE_TYPE_PROPERTY, MiscRNANode.NODE_TYPE);
            mRnaProperties.put(MRNANode.NODE_TYPE_PROPERTY, MRNANode.NODE_TYPE);
            ncRnaProperties.put(NcRNANode.NODE_TYPE_PROPERTY, NcRNANode.NODE_TYPE);
            rRnaProperties.put(RRNANode.NODE_TYPE_PROPERTY, RRNANode.NODE_TYPE);
            tmRnaProperties.put(TmRNANode.NODE_TYPE_PROPERTY, TmRNANode.NODE_TYPE);
            tRnaProperties.put(TRNANode.NODE_TYPE_PROPERTY, TRNANode.NODE_TYPE);
            //----------------------------------------------------------------------------------
            //----------------------------------------------------------------------------------


            BufferedWriter statsBuff = null;
            int genomeElementCounter = 0;

            try {
                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportRefSeq.log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportRefSeqStats.txt")));


                // create the batch inserter
                inserter = BatchInserters.inserter(args[1], MapUtil.load(new File(args[2])));

                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);


                //-----------------create batch indexes----------------------------------
                //----------------------------------------------------------------------
                BatchInserterIndex genomeElementVersionIndex = indexProvider.nodeIndex(GenomeElementNode.GENOME_ELEMENT_VERSION_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                BatchInserterIndex nodeTypeIndex = indexProvider.nodeIndex(Bio4jManager.NODE_TYPE_INDEX_NAME,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));


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

                            //--------create genome element node--------------
                            long genomeElementId = createGenomeElementNode(versionSt,
                                    commentSt, definitionSt, inserter, genomeElementVersionIndex, nodeTypeIndex);

                            //-----------genes-----------------
                            for (String genePositionsSt : geneList) {
                                geneProperties.put(GeneNode.POSITIONS_PROPERTY, genePositionsSt);
                                long geneId = inserter.createNode(geneProperties);
                                inserter.createRelationship(genomeElementId, geneId, genomeElementGeneRel, null);

                                //indexing gene node by its node_type
                                nodeTypeIndex.add(geneId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, GeneNode.NODE_TYPE));
                            }

                            //-----------CDS-----------------
                            for (String cdsPositionsSt : cdsList) {
                                cdsProperties.put(CDSNode.POSITIONS_PROPERTY, cdsPositionsSt);
                                long cdsID = inserter.createNode(cdsProperties);
                                inserter.createRelationship(genomeElementId, cdsID, genomeElementCDSRel, null);

                                //indexing CDS node by its node_type
                                nodeTypeIndex.add(cdsID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, CDSNode.NODE_TYPE));
                            }

                            //-----------misc rna-----------------
                            for (String miscRnaPositionsSt : miscRnaList) {
                                miscRnaProperties.put(MiscRNANode.POSITIONS_PROPERTY, miscRnaPositionsSt);
                                long miscRnaID = inserter.createNode(miscRnaProperties);
                                inserter.createRelationship(genomeElementId, miscRnaID, genomeElementMiscRnaRel, null);

                                //indexing MiscRNA node by its node_type
                                nodeTypeIndex.add(miscRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, MiscRNANode.NODE_TYPE));
                            }

                            //-----------m rna-----------------
                            for (String mRnaPositionsSt : mRnaList) {
                                mRnaProperties.put(MRNANode.POSITIONS_PROPERTY, mRnaPositionsSt);
                                long mRnaID = inserter.createNode(mRnaProperties);
                                inserter.createRelationship(genomeElementId, mRnaID, genomeElementMRnaRel, null);

                                //indexing MRNA node by its node_type
                                nodeTypeIndex.add(mRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, MRNANode.NODE_TYPE));
                            }

                            //-----------nc rna-----------------
                            for (String ncRnaPositionsSt : ncRnaList) {
                                ncRnaProperties.put(NcRNANode.POSITIONS_PROPERTY, ncRnaPositionsSt);
                                long ncRnaID = inserter.createNode(ncRnaProperties);
                                inserter.createRelationship(genomeElementId, ncRnaID, genomeElementNcRnaRel, null);

                                //indexing NCRNA node by its node_type
                                nodeTypeIndex.add(ncRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, NcRNANode.NODE_TYPE));
                            }

                            //-----------r rna-----------------
                            for (String rRnaPositionsSt : rRnaList) {
                                rRnaProperties.put(RRNANode.POSITIONS_PROPERTY, rRnaPositionsSt);
                                long rRnaID = inserter.createNode(rRnaProperties);
                                inserter.createRelationship(genomeElementId, rRnaID, genomeElementRRnaRel, null);

                                //indexing RRNA node by its node_type
                                nodeTypeIndex.add(rRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, RRNANode.NODE_TYPE));
                            }

                            //-----------tm rna-----------------
                            for (String tmRnaPositionsSt : tmRnaList) {
                                tmRnaProperties.put(TmRNANode.POSITIONS_PROPERTY, tmRnaPositionsSt);
                                long tmRnaID = inserter.createNode(tmRnaProperties);
                                inserter.createRelationship(genomeElementId, tmRnaID, genomeElementTmRnaRel, null);

                                //indexing TmRNA node by its node_type
                                nodeTypeIndex.add(tmRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, TmRNANode.NODE_TYPE));
                            }

                            //-----------t rna-----------------
                            for (String tRnaPositionsSt : tRnaList) {
                                tRnaProperties.put(TRNANode.POSITIONS_PROPERTY, tRnaPositionsSt);
                                long tRnaID = inserter.createNode(tRnaProperties);
                                inserter.createRelationship(genomeElementId, tRnaID, genomeElementTRnaRel, null);

                                //indexing TRNA node by its node_type
                                nodeTypeIndex.add(tRnaID, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, TRNANode.NODE_TYPE));
                            }

                            logger.log(Level.INFO, (versionSt + " saved!"));

                            genomeElementCounter++;

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

                // shutdown, makes sure all changes are written to disk
                indexProvider.shutdown();
                inserter.shutdown();

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

    private static long createGenomeElementNode(String version,
            String comment,
            String definition,
            BatchInserter inserter,
            BatchInserterIndex genomeElementVersionIndex,
            BatchInserterIndex nodeTypeIndex) {

        genomeElementProperties.put(GenomeElementNode.VERSION_PROPERTY, version);
        genomeElementProperties.put(GenomeElementNode.COMMENT_PROPERTY, comment);
        genomeElementProperties.put(GenomeElementNode.DEFINITION_PROPERTY, definition);

        long genomeElementId = inserter.createNode(genomeElementProperties);
        genomeElementVersionIndex.add(genomeElementId, MapUtil.map(GenomeElementNode.GENOME_ELEMENT_VERSION_INDEX, version));
        nodeTypeIndex.add(genomeElementId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, GenomeElementNode.NODE_TYPE));

        return genomeElementId;

    }
}
