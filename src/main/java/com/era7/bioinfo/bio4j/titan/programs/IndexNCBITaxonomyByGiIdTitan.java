/*
 * Copyright (C) 2010-2013  "Bio4j
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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetrieverTitan;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Indexes NCBI taxonomy elements by GI (gene identifiers) as specified in the
 * official mapping file
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IndexNCBITaxonomyByGiIdTitan implements Executable {

    private static final Logger logger = Logger.getLogger("IndexNCBITaxonomyByGiIdTitan");
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
                    + "1. Tax-id <--> Gi-id table file \n"
                    + "2. Bio4j DB folder \n");
        } else {

            long initTime = System.nanoTime();

            //-------writer for storing incorrect gene identifiers-taxon id pairs----
            BufferedWriter outBufferedWriter;
            BufferedWriter statsBuff = null;

            logger.log(Level.INFO, "creating manager...");
            //----------DB configuration------------------
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", args[1]);
            conf.setProperty("storage.backend", "local");

            //-------creating graph handlers---------------------
            Bio4jManager manager = new Bio4jManager(conf);
            BatchGraph bGraph = new BatchGraph(manager.getGraph(), BatchGraph.IdType.STRING, 1000);
            NodeRetrieverTitan nodeRetriever = new NodeRetrieverTitan(manager);

            int lineCounter = 0;

            File inFile = new File(args[0]);

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("IndexNCBITaxonomyByGiIdTitan.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                outBufferedWriter = new BufferedWriter(new FileWriter(new File("incorrectGiTaxIdPairs.txt")));
                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("IndexNCBITaxonomyByGiIdTitanStats.txt")));

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;

                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\t");

                    String giId = columns[0];
                    String taxId = columns[1];
                    
                    NCBITaxonNode nCBITaxonNode = nodeRetriever.getNCBITaxonByTaxId(String.valueOf(taxId));

                    if (nCBITaxonNode != null) {
                        nCBITaxonNode.addGiId(giId);
                    } else {

                        outBufferedWriter.write(giId + "\t" + taxId + "\n");
                    }

                    lineCounter++;

                    if (lineCounter % 100000 == 0) {
                        System.out.println("lineCounter = " + lineCounter);
                        outBufferedWriter.flush();
                    }
                }
                reader.close();

                outBufferedWriter.close();

            } catch (Exception e) {
                Logger.getLogger(ImportNCBITaxonomyTitan.class.getName()).log(Level.SEVERE, null, e);
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

                    statsBuff.write("Statistics for program IndexNCBITaxonomyByGiIdTitan:\nInput file: " + inFile.getName()
                            + "\nThere were " + lineCounter + " taxonomic units indexed.\n"
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
