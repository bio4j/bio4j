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

import com.era7.bioinfo.bio4j.Bio4jManager;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.IsoformNode;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexService;

/**
 *
 * Imports the sequence of every existent protein isoform
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportIsoformSequences implements Executable {

    private static final Logger logger = Logger.getLogger("ImportIsoformSequences");
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
                    + "1. Fasta file including all isoforms \n");
        } else {
            File inFile = new File(args[0]);

            String isoformIdSt = null;
            Transaction txn = null;
            Bio4jManager manager = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImporIsoformSeqsFile.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                logger.log(Level.INFO, "creating manager...");
                manager = new Bio4jManager(CommonData.DATABASE_FOLDER);
                logger.log(Level.INFO, "creating index manager...");
                IndexService indexService = manager.getIndexService();
                txn = manager.beginTransaction();

                int counter = 1;
                int limitForTransaction = 100;

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder seqStBuilder = new StringBuilder();



                logger.log(Level.INFO, "updating isoform data....");

                //-----first I create all the elements whitout their relationships-------------

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith(">")) {

                        String[] columns = line.split("\\|");
                        isoformIdSt = columns[1];
                        String isoformNameSt = columns[2].split("OS=")[0].trim();

                        //sequence read line
                        line = reader.readLine();

                        while (!line.trim().startsWith(">")) {
                            seqStBuilder.append(line);
                            line = reader.readLine();
                        }

                        String sequence = seqStBuilder.toString();
                        seqStBuilder.delete(0, seqStBuilder.length());

                        IsoformNode node = new IsoformNode(indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, isoformIdSt));
                        node.setSequence(sequence);
                        node.setName(isoformNameSt);
                    }

                    counter++;
                    if ((counter % limitForTransaction) == 0) {
                        txn.success();
                        txn.finish();
                    }
                }

                if(counter % limitForTransaction != 0){
                    txn.success();
                    txn.finish();
                }

                reader.close();

                logger.log(Level.INFO, "Done! :)");

            } catch (Exception e) {
                txn.failure();
                txn.finish();
                logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }finally{
                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();
            }
        }
    }
}
