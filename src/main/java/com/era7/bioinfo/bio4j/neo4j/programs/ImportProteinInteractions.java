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

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinIsoformInteractionRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinProteinInteractionRel;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.*;

/**
 * Imports protein interactions: - protein <--> protein - protein <--> isoform -
 * isoform <--> isoform
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportProteinInteractions implements Executable {

    private static final Logger logger = Logger.getLogger("ImportProteinInteractions");
    private static FileHandler fh;
    //--------indexing API constans-----
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    //-----------------------------------

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        try {
            main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Uniprot xml filename \n"
                    + "2. Bio4j DB folder\n"
                    + "3. Batch inserter .properties file");
        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;
            String accessionSt = "";

            BufferedWriter statsBuff = null;

            int proteinCounter = 0;
            int limitForPrintingOut = 10000;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportProteinInteractions" + args[0].split("\\.")[0] + ".log", false);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);
                //---------------------------------

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportProteinInteractionsStats_" + inFile.getName().split("\\.")[0] + ".txt")));

                // create the batch inserter
                inserter = BatchInserters.inserter(args[1], MapUtil.load(new File(args[2])));

                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);

                //------------------nodes properties maps-----------------------------------
                //---------------------------------------------------------------------

                //-------------------relationships properties maps--------------------------
                Map<String, Object> proteinProteinInteractionProperties = new HashMap<String, Object>();
                Map<String, Object> proteinIsoformInteractionProperties = new HashMap<String, Object>();
                //----------------------------------------------------------------------------

                //--------------------------------relationships------------------------------------------
                ProteinProteinInteractionRel proteinProteinInteractionRel = new ProteinProteinInteractionRel(null);
                ProteinIsoformInteractionRel proteinIsoformInteractionRel = new ProteinIsoformInteractionRel(null);
                //------------------------------------------------------------------------------------------------

                //------------------indexes creation----------------------------------
                BatchInserterIndex proteinAccessionIndex = indexProvider.nodeIndex(ProteinNode.PROTEIN_ACCESSION_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                BatchInserterIndex isoformIdIndex = indexProvider.nodeIndex(IsoformNode.ISOFORM_ID_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                //--------------------------------------------------------------------


                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;
                StringBuilder entryStBuilder = new StringBuilder();


                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            entryStBuilder.append(line);
                            line = reader.readLine();
                        }
                        //linea final del organism
                        entryStBuilder.append(line);
                        //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                        XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                        entryStBuilder.delete(0, entryStBuilder.length());

                        accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);


                        long currentProteinId = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, accessionSt).getSingle();

                        List<Element> comments = entryXMLElem.asJDomElement().getChildren(CommonData.COMMENT_TAG_NAME);

                        for (Element commentElem : comments) {

                            String commentTypeSt = commentElem.getAttributeValue(CommonData.COMMENT_TYPE_ATTRIBUTE);

                            //----------interaction----------------
                            if (commentTypeSt.equals(ProteinProteinInteractionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                List<Element> interactants = commentElem.getChildren("interactant");
                                Element interactant1 = interactants.get(0);
                                Element interactant2 = interactants.get(1);
                                Element organismsDiffer = commentElem.getChild("organismsDiffer");
                                Element experiments = commentElem.getChild("experiments");
                                String intactId1St = interactant1.getAttributeValue("intactId");
                                String intactId2St = interactant2.getAttributeValue("intactId");
                                String organismsDifferSt = "";
                                String experimentsSt = "";
                                if (intactId1St == null) {
                                    intactId1St = "";
                                }
                                if (intactId2St == null) {
                                    intactId2St = "";
                                }
                                if (organismsDiffer != null) {
                                    organismsDifferSt = organismsDiffer.getText();
                                }
                                if (experiments != null) {
                                    experimentsSt = experiments.getText();
                                }

                                //----now we try to retrieve the interactant 2 accession--
                                String interactant2AccessionSt = interactant2.getChildText("id");
                                long protein2Id = -1;
                                if (interactant2AccessionSt != null) {

                                    IndexHits<Long> protein2IdIndexHits = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, interactant2AccessionSt);
                                    if (protein2IdIndexHits.hasNext()) {
                                        if (protein2IdIndexHits.size() == 1) {
                                            protein2Id = protein2IdIndexHits.getSingle();
                                        }
                                    }

                                    if (protein2Id < 0) {
                                        //Since we did not find the protein we try to find a isoform instead
                                        long isoformId = -1;
                                        IndexHits<Long> isoformIdIndexHits = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, interactant2AccessionSt);
                                        if (isoformIdIndexHits.hasNext()) {
                                            if (isoformIdIndexHits.size() == 1) {
                                                isoformId = isoformIdIndexHits.getSingle();
                                            }
                                        }
                                        if (isoformId >= 0) {

                                            proteinIsoformInteractionProperties.put(ProteinIsoformInteractionRel.EXPERIMENTS_PROPERTY, experimentsSt);
                                            proteinIsoformInteractionProperties.put(ProteinIsoformInteractionRel.ORGANISMS_DIFFER_PROPERTY, organismsDifferSt);
                                            proteinIsoformInteractionProperties.put(ProteinIsoformInteractionRel.INTACT_ID_1_PROPERTY, intactId1St);
                                            proteinIsoformInteractionProperties.put(ProteinIsoformInteractionRel.INTACT_ID_2_PROPERTY, intactId2St);

                                            inserter.createRelationship(currentProteinId, isoformId, proteinIsoformInteractionRel, proteinIsoformInteractionProperties);

                                        }
                                    } else {

                                        proteinProteinInteractionProperties.put(ProteinProteinInteractionRel.EXPERIMENTS_PROPERTY, experimentsSt);
                                        proteinProteinInteractionProperties.put(ProteinProteinInteractionRel.ORGANISMS_DIFFER_PROPERTY, organismsDifferSt);
                                        proteinProteinInteractionProperties.put(ProteinProteinInteractionRel.INTACT_ID_1_PROPERTY, intactId1St);
                                        proteinProteinInteractionProperties.put(ProteinProteinInteractionRel.INTACT_ID_2_PROPERTY, intactId2St);

                                        inserter.createRelationship(currentProteinId, protein2Id, proteinProteinInteractionRel, proteinProteinInteractionProperties);

                                    }

                                }

                            }

                        }

                        proteinCounter++;
                        if ((proteinCounter % limitForPrintingOut) == 0) {
                            logger.log(Level.INFO, (proteinCounter + " proteins updated with interactions!!"));
                        }

                    }
                }


            } catch (Exception e) {
                logger.log(Level.SEVERE, ("Exception retrieving protein " + accessionSt));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }

            } finally {
                //outbBuff.close();

                try {

                    // shutdown, makes sure all changes are written to disk
                    indexProvider.shutdown();
                    inserter.shutdown();

                    //closing logger file handler
                    fh.close();

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportProteinInteractions:\nInput file: " + inFile.getName()
                            + "\nThere were " + proteinCounter + " proteins analyzed.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();

                } catch (Exception e) {

                    logger.log(Level.SEVERE, ("Exception retrieving protein " + accessionSt));
                    logger.log(Level.SEVERE, e.getMessage());
                    StackTraceElement[] trace = e.getStackTrace();
                    for (StackTraceElement stackTraceElement : trace) {
                        logger.log(Level.SEVERE, stackTraceElement.toString());
                    }
                    //closing logger file handler
                    fh.close();
                }


            }
        }

    }
}
