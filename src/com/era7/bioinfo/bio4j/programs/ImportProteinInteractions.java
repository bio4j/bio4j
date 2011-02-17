/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.nodes.*;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinIsoformInteractionRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinProteinInteractionRel;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.index.lucene.LuceneIndexBatchInserter;
import org.neo4j.index.lucene.LuceneIndexBatchInserterImpl;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 * Imports protein interactions:
 * - protein <--> protein
 * - protein <--> isoform
 * - isoform <--> isoform
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportProteinInteractions implements Executable {

    private static final Logger logger = Logger.getLogger("ImportProteinInteractions");
    private static FileHandler fh;

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

        if (args.length != 1) {
            System.out.println("This program expects one parameter: \n"
                    + "1. Uniprot xml filename \n");
        } else {
            File inFile = new File(args[0]);

            BatchInserter inserter = null;
            LuceneIndexBatchInserter indexService = null;
            String accessionSt = "";

            //writer for the file with the entries accessions that supposably have interactions
            //with themselves
            BufferedWriter outbBuff = null;

            try {

                outbBuff = new BufferedWriter(new FileWriter(new File("conflictEntries.txt")));

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportProteinInteractions" + args[0].split("\\.")[0] + ".log", false);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                indexService = new LuceneIndexBatchInserterImpl(inserter);

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


                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder entryStBuilder = new StringBuilder();


                int counter = 1;
                int limitForPrintingOut = 10000;
                int limitForClosingBatchInserter = 100000;

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

                        long currentProteinId = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, accessionSt);

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

                                boolean interactionWithItself = false;

                                //----now we try to retrieve the interactant 2 accession--
                                String interactant2AccessionSt = interactant2.getChildText("id");
                                long protein2Id = -1;
                                if (interactant2AccessionSt != null) {
                                    protein2Id = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, interactant2AccessionSt);
                                } else {
//                                    System.out.println("intactId1St = " + intactId1St);
//                                    System.out.println("intactId2St = " + intactId2St);
                                    //this is the case where one protein interacts with itself
                                    if (intactId1St.equals(intactId2St)) {
                                        interactionWithItself = true;
                                        outbBuff.write(accessionSt + "\n");
                                    }
//                                    System.out.println("protein2Id = " + protein2Id);
//                                    System.out.println("currentProteinId = " + currentProteinId);
                                }

                                if (!interactionWithItself) {
                                    if (protein2Id < 0) {
                                        //Since we did not find the protein we try to find a isoform instead
                                        long isoformId = indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, interactant2AccessionSt);
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

                            counter++;
                            if ((counter % limitForPrintingOut) == 0) {
                                logger.log(Level.INFO, (counter + " proteins updated with interactions!!"));
                            }
                            if ((counter % limitForClosingBatchInserter) == 0) {
                                inserter.shutdown();
                                indexService.shutdown();
                                // create the batch inserter again
                                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));
                                // create the batch index service again
                                indexService = new LuceneIndexBatchInserterImpl(inserter);
                            }

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
                outbBuff.close();

                //closing logger file handler
                fh.close();

                // shutdown, makes sure all changes are written to disk
                inserter.shutdown();
                indexService.shutdown();

            }
        }

    }
}
