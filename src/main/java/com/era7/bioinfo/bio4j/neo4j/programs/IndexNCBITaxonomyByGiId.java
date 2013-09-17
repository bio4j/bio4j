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
package com.era7.bioinfo.bio4j.neo4j.programs;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ncbi.NCBITaxonNode;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.*;

/**
 * Indexes NCBI taxonomy elements by GI (gene identifiers) as specified in the
 * official mapping file
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IndexNCBITaxonomyByGiId implements Executable {

    private static final Logger logger = Logger.getLogger("IndexNCBITaxonomyByGiId");
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
                    + "1. Tax-id <--> Gi-id table file \n"
                    + "2. Bio4j DB folder \n"
                    + "3. Batch inserter .properties file name");
        } else {

            long initTime = System.nanoTime();

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;
            BatchInserterIndex giIndex;
            BatchInserterIndex taxonIndex;

            //-------writer for storing incorrect gene identifiers-taxon id pairs----
            BufferedWriter outBufferedWriter;
            BufferedWriter statsBuff = null;

            int lineCounter = 0;

            File inFile = new File(args[0]);

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("IndexNCBITaxonomyByGiId.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                outBufferedWriter = new BufferedWriter(new FileWriter(new File("incorrectGiTaxIdPairs.txt")));
                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("IndexNCBITaxonomyByGIIdStats.txt")));

                // create the batch inserter
                inserter = BatchInserters.inserter(args[1], MapUtil.load(new File(args[2])));


                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);
                Map<String, String> indexProps = MapUtil.stringMap("provider", "lucene", "type", "exact");

                giIndex = indexProvider.nodeIndex(NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, indexProps);
                taxonIndex = indexProvider.nodeIndex(NCBITaxonNode.NCBI_TAXON_ID_INDEX, indexProps);

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;

                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\t");

                    int giId = Integer.parseInt(columns[0]);
                    int taxId = Integer.parseInt(columns[1]);

                    Long nCBITaxonNodeId = taxonIndex.get(NCBITaxonNode.NCBI_TAXON_ID_INDEX, String.valueOf(taxId)).getSingle();

                    if (nCBITaxonNodeId != null) {
                        giIndex.add(nCBITaxonNodeId, MapUtil.map(NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, giId));
                    } else {
                        outBufferedWriter.write(giId + "\t" + taxId + "\n");
                    }

                    lineCounter++;

                    if (lineCounter % 100000 == 0) {
                        logger.log(Level.INFO, (lineCounter + " lines parsed..."));
                        outBufferedWriter.flush();
                    }
                }
                reader.close();

                outBufferedWriter.close();

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            } finally {

                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
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

                    statsBuff.write("Statistics for program IndexNCBITaxonomyByGiId:\nInput file: " + inFile.getName()
                            + "\nThere were " + lineCounter + " association pairs processed.\n"
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
