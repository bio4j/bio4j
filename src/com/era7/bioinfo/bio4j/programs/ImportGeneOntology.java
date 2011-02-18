/*
 * Copyright (C) 2010-2011  "Bio4j"
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
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.nodes.GoTermNode;
import com.era7.bioinfo.bio4jmodel.relationships.GoParentRel;
import com.era7.bioinfo.bio4jmodel.relationships.go.BiologicalProcessRel;
import com.era7.bioinfo.bio4jmodel.relationships.go.CellularComponentRel;
import com.era7.bioinfo.bio4jmodel.relationships.go.MainGoRel;
import com.era7.bioinfo.bio4jmodel.relationships.go.MolecularFunctionRel;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportGeneOntology implements Executable {

    public static final String PROPERTIES_FILE_NAME = "batchInserter.properties";
    public static final String TERM_TAG_NAME = "term";
    public static final String ID_TAG_NAME = "id";
    public static final String NAME_TAG_NAME = "name";
    public static final String DEF_TAG_NAME = "def";
    public static final String DEFSTR_TAG_NAME = "defstr";
    public static final String IS_A_TAG_NAME = "is_a";
    public static final String IS_ROOT_TAG_NAME = "is_root";
    public static final String NAMESPACE_TAG_NAME = "namespace";
    public static final String MOLECULAR_FUNCTION_GO_ID = "GO:0003674";
    public static final String BIOLOGICAL_PROCESS_GO_ID = "GO:0008150";
    public static final String CELLULAR_COMPONENT_GO_ID = "GO:0005575";
    private static final Logger logger = Logger.getLogger("ImportGeneOntology");
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

        if (args.length != 1) {
            System.out.println("This program expects one parameter: \n"
                    + "1. Gene ontology xml filename \n");
        } else {
            File inFile = new File(args[0]);


            BatchInserter inserter = null;
            LuceneIndexBatchInserter indexService = null;

            try {

                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportGeneOntology.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(PROPERTIES_FILE_NAME));

                // create the batch index service
                indexService = new LuceneIndexBatchInserterImpl(inserter);

                //------------------nodes properties maps-----------------------------------
                Map<String, Object> goProperties = new HashMap<String, Object>();
                //--------------------------------------------------------------------------

                //--------------------------------relationships------------------------------------------
                GoParentRel goParentRel = new GoParentRel(null);
                MainGoRel mainGoRel = new MainGoRel(null);
                CellularComponentRel cellularComponentRel = new CellularComponentRel(null);
                BiologicalProcessRel biologicalProcessRel = new BiologicalProcessRel(null);
                MolecularFunctionRel molecularFunctionRel = new MolecularFunctionRel(null);
                //--------------------------------------------------------------------------

                Map<String, ArrayList<String>> termParentsMap = new HashMap<String, ArrayList<String>>();

                int counter = 1;
                int limitForPrintingOut = 10000;

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
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

                        //----term parents----
                        List<Element> termParentTerms = termXMLElement.asJDomElement().getChildren(IS_A_TAG_NAME);
                        ArrayList<String> array = new ArrayList<String>();
                        for (Element elem : termParentTerms) {
                            array.add(elem.getText().trim());
                        }
                        termParentsMap.put(goId, array);
                        //---------------------

                        goProperties.put(GoTermNode.ID_PROPERTY, goId);
                        goProperties.put(GoTermNode.NAME_PROPERTY, goName);
                        goProperties.put(GoTermNode.DEFINITION_PROPERTY, goDefinition);
                        goProperties.put(GoTermNode.NAMESPACE_PROPERTY, goNamespace);
                        long currentGoTermId = inserter.createNode(goProperties);
                        indexService.index(currentGoTermId, GoTermNode.GO_TERM_ID_INDEX, goId);

                        //----IS ROOT ? ----
                        Element isRootElem = termXMLElement.asJDomElement().getChild(IS_ROOT_TAG_NAME);
                        if (isRootElem != null) {
                            String temp = isRootElem.getTextTrim();
                            if (temp.equals("1")) {
                                inserter.createRelationship(inserter.getReferenceNode(), currentGoTermId, mainGoRel, null);
                                if (goId.equals(MOLECULAR_FUNCTION_GO_ID)) {
                                    inserter.createRelationship(inserter.getReferenceNode(), currentGoTermId, molecularFunctionRel, null);
                                } else if (goId.equals(BIOLOGICAL_PROCESS_GO_ID)) {
                                    inserter.createRelationship(inserter.getReferenceNode(), currentGoTermId, biologicalProcessRel, null);
                                } else if (goId.equals(CELLULAR_COMPONENT_GO_ID)) {
                                    inserter.createRelationship(inserter.getReferenceNode(), currentGoTermId, cellularComponentRel, null);
                                }

                            }
                        }
                        //----------------------                        

                    }
                    counter++;
                    if ((counter % limitForPrintingOut) == 0) {
                        logger.log(Level.INFO, (counter + " terms inserted!!"));
                    }
                }
                reader.close();

                //-----------------------------------------------------------------------

                logger.log(Level.INFO, "Inserting relationships....");

                //-------------------now I create the relationships-----------------
                Set<String> keys = termParentsMap.keySet();
                for (String key : keys) {
                    long currentNodeId = indexService.getSingleNode(GoTermNode.GO_TERM_ID_INDEX, key);
                    ArrayList<String> tempArray = termParentsMap.get(key);
                    for (String string : tempArray) {
                        long tempNodeId = indexService.getSingleNode(GoTermNode.GO_TERM_ID_INDEX, string);
                        inserter.createRelationship(currentNodeId, tempNodeId, goParentRel, null);
                    }
                }
                //------------------------------------------------------------------

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
                    indexService.shutdown();
                    inserter.shutdown();

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
