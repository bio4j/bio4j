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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.OrganismNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonParentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonRel;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Imports NCBI taxonomy into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportNCBITaxonomyTitan implements Executable {

    private static final Logger logger = Logger.getLogger("ImportNCBITaxonomyTitan");
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

        if (args.length != 5) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Nodes DMP filename \n"
                    + "2. Names DMP filename \n"
                    + "3. Merged DMP filename \n"
                    + "4. Bio4j DB folder \n"
                    + "5. Associate Uniprot taxonomy (true/false)");
        } else {

            long initTime = System.nanoTime();

            Bio4jManager manager = null;
            int taxonCounter = 0;

            boolean associateUniprotTaxonomy = Boolean.parseBoolean(args[4]);

            BufferedWriter statsBuff = null;

            File nodesDumpFile = new File(args[0]);
            File namesDumpFile = new File(args[1]);
            File mergedDumpFile = new File(args[2]);


            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportNCBITaxonomy.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportNCBITaxonomyStats.txt")));

                BufferedReader reader = new BufferedReader(new FileReader(nodesDumpFile));
                String line;

                logger.log(Level.INFO, "creating manager...");
                //----------DB configuration------------------
                Configuration conf = new BaseConfiguration();
                conf.setProperty("storage.directory", args[3]);
                conf.setProperty("storage.backend", "local");

                //-------creating graph handlers---------------------
                manager = new Bio4jManager(conf);
                BatchGraph bGraph = new BatchGraph(manager.getGraph(), BatchGraph.IdType.STRING, 1000);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);

                HashMap<String, String> nodeParentMap = new HashMap<String, String>();

                logger.log(Level.INFO, "reading nodes file...");

                while ((line = reader.readLine()) != null) {

                    if (line.trim().length() > 0) {

                        String[] columns = line.split("\\|");

                        NCBITaxonNode node = new NCBITaxonNode(manager.createNode(NCBITaxonNode.NODE_TYPE));
                        node.setTaxId(columns[0].trim());
                        node.setRank(columns[2].trim());
                        node.setEmblCode(columns[3].trim());

                        //saving the parent of the node for later
                        nodeParentMap.put(node.getTaxId(), columns[1].trim());

                        taxonCounter++;

                    }

                }

                reader.close();
                logger.log(Level.INFO, "done!");

                logger.log(Level.INFO, "reading names file...");
                //------------reading names file-----------------
                reader = new BufferedReader(new FileReader(namesDumpFile));
                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\\|");

                    if (columns[columns.length - 1].trim().equals("scientific name")) {

                        String taxId = columns[0].trim();
                        String nameSt = columns[1].trim();

                        NCBITaxonNode node = nodeRetriever.getNCBITaxonByTaxId(taxId);
                        node.setScientificName(nameSt);

                    }

                }
                reader.close();
                logger.log(Level.INFO, "done!");

                logger.log(Level.INFO, "storing relationships...");

                Set<String> nodesSet = nodeParentMap.keySet();
                for (String nodeTaxId : nodesSet) {

                    String parentTaxId = nodeParentMap.get(nodeTaxId);

                    NCBITaxonNode currentNode = nodeRetriever.getNCBITaxonByTaxId(nodeTaxId);

                    if (!nodeTaxId.equals(parentTaxId)) {
                        NCBITaxonNode parentNode = nodeRetriever.getNCBITaxonByTaxId(parentTaxId);
                        bGraph.addEdge(null, parentNode.getNode(), currentNode.getNode(), NCBITaxonParentRel.NAME);
                    } 

                }
                
                logger.log(Level.INFO, "Done!");

                if (associateUniprotTaxonomy) {

                    logger.log(Level.INFO, "Associating uniprot taxonomy...");
                    associateTaxonomy(manager, nodeRetriever, bGraph);
                    logger.log(Level.INFO, "Done!");
                }


                logger.log(Level.INFO, "reading merged file...");
                //------------reading merged file-----------------
                reader = new BufferedReader(new FileReader(mergedDumpFile));
                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\\|");

                    String oldId = columns[0].trim();
                    String goodId = columns[1].trim();

                    NCBITaxonNode goodNode = nodeRetriever.getNCBITaxonByTaxId(goodId);
                    goodNode.addOldTaxId(oldId);
                    

                }
                reader.close();
                logger.log(Level.INFO, "done!");

            } catch (Exception ex) {
                Logger.getLogger(ImportNCBITaxonomyTitan.class.getName()).log(Level.SEVERE, null, ex);

            } finally {

                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();

                try {

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportNCBITaxonomy:\nInput file: " + nodesDumpFile.getName()
                            + "\nThere were " + taxonCounter + " taxonomic units inserted.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void associateTaxonomy(Bio4jManager manager,
            NodeRetriever nodeRetriever,
            BatchGraph bGraph) {

        
        Iterator<Vertex> organismIterator = manager.getGraph().getVertices(OrganismNode.NODE_TYPE_PROPERTY, OrganismNode.NODE_TYPE).iterator();

        while (organismIterator.hasNext()) {
            OrganismNode organismNode = new OrganismNode(organismIterator.next());
            Vertex ncbiNode = nodeRetriever.getNCBITaxonByTaxId(organismNode.getNcbiTaxonomyId()).getNode();
            bGraph.addEdge(null, organismNode.getNode(), ncbiNode, NCBITaxonRel.NAME);
        }
    }
}
