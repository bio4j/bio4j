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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.GoTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.go.*;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom.Element;

/**
 * Imports the Gene Ontology into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportGeneOntologyTitan implements Executable {

    public static final String TERM_TAG_NAME = "term";
    public static final String ID_TAG_NAME = "id";
    public static final String NAME_TAG_NAME = "name";
    public static final String DEF_TAG_NAME = "def";
    public static final String DEFSTR_TAG_NAME = "defstr";
    public static final String IS_ROOT_TAG_NAME = "is_root";
    public static final String IS_OBSOLETE_TAG_NAME = "is_obsolete";
    public static final String COMMENT_TAG_NAME = "comment";
    public static final String NAMESPACE_TAG_NAME = "namespace";
    public static final String RELATIONSHIP_TAG_NAME = "relationship";
    private static final Logger logger = Logger.getLogger("ImportGeneOntologyBP");
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

        if (args.length != 3) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Gene ontology xml filename \n"
                    + "2. Bio4j DB folder \n"
                    + "3. Batch inserter .properties file");

        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);

            //----------DB configuration------------------
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", args[1]);
            conf.setProperty("storage.backend", "local");

            //-------creating graph handlers---------------------
            Bio4jManager manager = new Bio4jManager(conf);
            BatchGraph bGraph = new BatchGraph(manager.getGraph(), BatchGraph.IdType.STRING, 1000);
            NodeRetriever nodeRetriever = new NodeRetriever(manager);

            BufferedWriter statsBuff = null;

            int termCounter = 0;
            int limitForPrintingOut = 10000;

            try {

                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportGeneOntologyTitan.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportGeneOntologyBPStats.txt")));

                Map<String, ArrayList<String>> termParentsMap = new HashMap<String, ArrayList<String>>();
                Map<String, ArrayList<String>> regulatesMap = new HashMap<String, ArrayList<String>>();
                Map<String, ArrayList<String>> negativelyRegulatesMap = new HashMap<String, ArrayList<String>>();
                Map<String, ArrayList<String>> positivelyRegulatesMap = new HashMap<String, ArrayList<String>>();
                Map<String, ArrayList<String>> partOfMap = new HashMap<String, ArrayList<String>>();
                Map<String, ArrayList<String>> hasPartMap = new HashMap<String, ArrayList<String>>();


                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;
                StringBuilder termStBuilder = new StringBuilder();

                logger.log(Level.INFO, "inserting nodes....");

                //-----first I create all the elements whitout their relationships-------------

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + TERM_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + TERM_TAG_NAME + ">")) {
                            termStBuilder.append(line);
                            line = reader.readLine();
                        }
                        //linea final del organism
                        termStBuilder.append(line);
                        //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                        XMLElement termXMLElement = new XMLElement(termStBuilder.toString());
                        termStBuilder.delete(0, termStBuilder.length());

                        String goId = termXMLElement.asJDomElement().getChildText(ID_TAG_NAME);
                        String goName = termXMLElement.asJDomElement().getChildText(NAME_TAG_NAME);
                        if (goName == null) {
                            goName = "";
                        }
                        String goNamespace = termXMLElement.asJDomElement().getChildText(NAMESPACE_TAG_NAME);
                        if (goNamespace == null) {
                            goNamespace = "";
                        }
                        String goDefinition = "";
                        Element defElem = termXMLElement.asJDomElement().getChild(DEF_TAG_NAME);
                        if (defElem != null) {
                            Element defstrElem = defElem.getChild(DEFSTR_TAG_NAME);
                            if (defstrElem != null) {
                                goDefinition = defstrElem.getText();
                            }
                        }
                        String goComment = termXMLElement.asJDomElement().getChildText(COMMENT_TAG_NAME);
                        if (goComment == null) {
                            goComment = "";
                        }
                        String goIsObsolete = termXMLElement.asJDomElement().getChildText(IS_OBSOLETE_TAG_NAME);
                        if (goIsObsolete == null) {
                            goIsObsolete = "";
                        } else {
                            if (goIsObsolete.equals("1")) {
                                goIsObsolete = "true";
                            } else {
                                goIsObsolete = "false";
                            }
                        }


                        List<Element> altIdElems = termXMLElement.asJDomElement().getChildren("alt_id");
                        String[] alternativeIds = new String[altIdElems.size()];
                        for (int i = 0; i < altIdElems.size(); i++) {
                            alternativeIds[i] = altIdElems.get(i).getText();
                        }


                        //----term parents----
                        List<Element> termParentTerms = termXMLElement.asJDomElement().getChildren(IsAGoRel.OBOXML_RELATIONSHIP_NAME);
                        ArrayList<String> array = new ArrayList<String>();
                        for (Element elem : termParentTerms) {
                            array.add(elem.getText().trim());
                        }
                        termParentsMap.put(goId, array);
                        //---------------------

                        //-------relationship tags-----------
                        List<Element> relationshipTags = termXMLElement.asJDomElement().getChildren(RELATIONSHIP_TAG_NAME);

                        for (Element relationshipTag : relationshipTags) {

                            String relType = relationshipTag.getChildText("type");
                            String toSt = relationshipTag.getChildText("to");

                            if (relType.equals(RegulatesGoRel.OBOXML_RELATIONSHIP_NAME)) {

                                ArrayList<String> tempArray = regulatesMap.get(goId);
                                if (tempArray == null) {
                                    tempArray = new ArrayList<String>();
                                    regulatesMap.put(goId, tempArray);
                                }
                                tempArray.add(toSt);

                            } else if (relType.equals(PositivelyRegulatesGoRel.OBOXML_RELATIONSHIP_NAME)) {

                                ArrayList<String> tempArray = positivelyRegulatesMap.get(goId);
                                if (tempArray == null) {
                                    tempArray = new ArrayList<String>();
                                    positivelyRegulatesMap.put(goId, tempArray);
                                }
                                tempArray.add(toSt);

                            } else if (relType.equals(NegativelyRegulatesGoRel.OBOXML_RELATIONSHIP_NAME)) {

                                ArrayList<String> tempArray = negativelyRegulatesMap.get(goId);
                                if (tempArray == null) {
                                    tempArray = new ArrayList<String>();
                                    negativelyRegulatesMap.put(goId, tempArray);
                                }
                                tempArray.add(toSt);

                            } else if (relType.equals(PartOfGoRel.OBOXML_RELATIONSHIP_NAME)) {

                                ArrayList<String> tempArray = partOfMap.get(goId);
                                if (tempArray == null) {
                                    tempArray = new ArrayList<String>();
                                    partOfMap.put(goId, tempArray);
                                }
                                tempArray.add(toSt);

                            } else if (relType.equals(HasPartOfGoRel.OBOXML_RELATIONSHIP_NAME)) {

                                ArrayList<String> tempArray = hasPartMap.get(goId);
                                if (tempArray == null) {
                                    tempArray = new ArrayList<String>();
                                    hasPartMap.put(goId, tempArray);
                                }
                                tempArray.add(toSt);

                            }
                        }
                        //-------------------------------------

                        Vertex goTermVertex = bGraph.addVertex(null);

                        goTermVertex.setProperty(GoTermNode.ID_PROPERTY, goId);
                        goTermVertex.setProperty(GoTermNode.NAME_PROPERTY, goName);
                        goTermVertex.setProperty(GoTermNode.DEFINITION_PROPERTY, goDefinition);
                        goTermVertex.setProperty(GoTermNode.NAMESPACE_PROPERTY, goNamespace);
                        goTermVertex.setProperty(GoTermNode.ALTERNATIVE_IDS_PROPERTY, alternativeIds);
                        goTermVertex.setProperty(GoTermNode.OBSOLETE_PROPERTY, goIsObsolete);
                        goTermVertex.setProperty(GoTermNode.COMMENT_PROPERTY, goComment);
                        goTermVertex.setProperty(GoTermNode.NODE_TYPE_PROPERTY, GoTermNode.NODE_TYPE);
                        //----------------------                        

                    }
                    termCounter++;
                    if ((termCounter % limitForPrintingOut) == 0) {
                        logger.log(Level.INFO, (termCounter + " terms inserted!!"));
                    }
                }
                reader.close();

                //-----------------------------------------------------------------------

                logger.log(Level.INFO, "Inserting relationships....");

                logger.log(Level.INFO, "'is_a' relationships....");

                //-------------------'is_a' relationships-----------------
                Set<String> keys = termParentsMap.keySet();
                for (String key : keys) {

                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = termParentsMap.get(key);

                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), IsAGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "'regulates' relationships....");
                //-------------------'regulates' relationships----------------------
                keys = regulatesMap.keySet();
                for (String key : keys) {
                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = regulatesMap.get(key);
                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), RegulatesGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "'negatively_regulates' relationships....");
                //-------------------'regulates' relationships----------------------
                keys = negativelyRegulatesMap.keySet();
                for (String key : keys) {
                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = negativelyRegulatesMap.get(key);
                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), NegativelyRegulatesGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "'positively_regulates' relationships....");
                //-------------------'regulates' relationships----------------------
                keys = positivelyRegulatesMap.keySet();
                for (String key : keys) {
                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = positivelyRegulatesMap.get(key);
                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), PositivelyRegulatesGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "'part_of' relationships....");
                //-------------------'regulates' relationships----------------------
                keys = partOfMap.keySet();
                for (String key : keys) {
                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = partOfMap.get(key);
                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), PartOfGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "'has_part' relationships....");
                //-------------------'regulates' relationships----------------------
                keys = hasPartMap.keySet();
                for (String key : keys) {
                    GoTermNode tempGoTerm = nodeRetriever.getGoTermById(key);
                    ArrayList<String> tempArray = hasPartMap.get(key);
                    for (String string : tempArray) {
                        GoTermNode tempGoTerm2 = nodeRetriever.getGoTermById(string);
                        bGraph.addEdge(null, tempGoTerm.getNode(), tempGoTerm2.getNode(), HasPartOfGoRel.NAME);
                    }
                }

                logger.log(Level.INFO, "Done! :)");


            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                try {
                    //closing logger file handler
                    fh.close();
                    logger.log(Level.INFO, "Closing up inserter and index service....");
                    // shutdown, makes sure all changes are written to disk
                    manager.shutDown();

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportGeneOntology:\nInput file: " + inFile.getName()
                            + "\nThere were " + termCounter + " terms inserted.\n"
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
        }
    }
}
