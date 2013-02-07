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
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinIsoformInteractionRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinProteinInteractionRel;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom.Element;

/**
 * Imports protein interactions: - protein <--> protein - protein <--> isoform -
 * isoform <--> isoform
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportProteinInteractionsTitan implements Executable {

    private static final Logger logger = Logger.getLogger("ImportProteinInteractionsTitan");
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

        if (args.length != 2) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Uniprot xml filename \n"
                    + "2. Bio4j DB folder\n");
        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);

            String accessionSt = "";

            BufferedWriter statsBuff = null;
            Bio4jManager manager = null;

            int proteinCounter = 0;
            int limitForPrintingOut = 10000;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportProteinInteractionsTitan" + args[0].split("\\.")[0] + ".log", false);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);
                //---------------------------------
                
                //----------DB configuration------------------
                Configuration conf = new BaseConfiguration();
                conf.setProperty("storage.directory", args[3]);
                conf.setProperty("storage.backend", "local");

                //-------creating graph handlers---------------------
                manager = new Bio4jManager(conf);
                BatchGraph bGraph = new BatchGraph(manager.getGraph(), BatchGraph.IdType.STRING, 1000);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportProteinInteractionsTitanStats_" + inFile.getName().split("\\.")[0] + ".txt")));

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;
                StringBuilder entryStBuilder = new StringBuilder();


                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            entryStBuilder.append(line);
                            line = reader.readLine();
                        }
                        //organism last line
                        entryStBuilder.append(line);
                        
                        XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                        entryStBuilder.delete(0, entryStBuilder.length());

                        accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);

                        ProteinNode currentProteinNode = nodeRetriever.getProteinNodeByAccession(accessionSt);

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
                                
                                if (interactant2AccessionSt != null) {

                                    ProteinNode protein2Node = nodeRetriever.getProteinNodeByAccession(interactant2AccessionSt);                                    

                                    if (protein2Node == null) {
                                        //Since we did not find the protein we try to find a isoform instead
                                        IsoformNode isoformNode = nodeRetriever.getIsoformById(interactant2AccessionSt);
                                       
                                        if (isoformNode != null) {

                                            ProteinIsoformInteractionRel proteinIsoformInteractionRel = new ProteinIsoformInteractionRel(bGraph.addEdge(null, currentProteinNode.getNode(), isoformNode.getNode(), ProteinIsoformInteractionRel.NAME));
                                            proteinIsoformInteractionRel.setExperiments(experimentsSt);
                                            proteinIsoformInteractionRel.setOrganismsDiffer(organismsDifferSt);
                                            proteinIsoformInteractionRel.setIntactId1(intactId1St);
                                            proteinIsoformInteractionRel.setIntactId2(intactId2St);
                                            
                                        }
                                    } else {
                                        
                                        ProteinProteinInteractionRel proteinProteinInteractionRel = new ProteinProteinInteractionRel(bGraph.addEdge(null, currentProteinNode.getNode(), protein2Node.getNode(), ProteinProteinInteractionRel.NAME));
                                        proteinProteinInteractionRel.setExperiments(experimentsSt);
                                        proteinProteinInteractionRel.setOrganismsDiffer(organismsDifferSt);
                                        proteinProteinInteractionRel.setIntactId1(intactId1St);
                                        proteinProteinInteractionRel.setIntactId2(intactId2St);

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

                    // shutdown db manager
                    manager.shutDown();

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
