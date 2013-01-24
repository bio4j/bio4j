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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.IndexHits;

/**
 *
 * Imports the sequence of every existent protein isoform
 *
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

        if (args.length != 2) {
            System.out.println("This program expects two parameters: \n"
                    + "1. Fasta file including all isoforms \n"
                    + "2. Bio4j DB folder");
        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);

            String isoformIdSt = null;
            Transaction txn = null;
            Bio4jManager manager = null;

            BufferedWriter statsBuff = null;

            int isoformCounter = 0;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImporIsoformSeqsFile.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportIsoformSequencesStats.txt")));

                logger.log(Level.INFO, "creating manager...");
                manager = new Bio4jManager(args[1]);

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line;
                StringBuilder seqStBuilder = new StringBuilder();


                txn = manager.beginTransaction();

                logger.log(Level.INFO, "updating isoforms data....");

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
                            if (line == null) {
                                break;
                            }
                        }

                        String sequence = seqStBuilder.toString();
                        seqStBuilder.delete(0, seqStBuilder.length());

                        IndexHits<Node> isoformHits = manager.getIsoformIdIndex().get(IsoformNode.ISOFORM_ID_INDEX, isoformIdSt);
                        if (isoformHits.size() == 1) {
                            IsoformNode node = new IsoformNode(isoformHits.getSingle());
                            node.setSequence(sequence);
                            node.setName(isoformNameSt);
                            System.out.println("Setting name for: " + node.getId());

                            isoformCounter++;
                        }

                    }

                    if (line == null) {
                        break;
                    }

                }

                reader.close();

                logger.log(Level.INFO, "Done! :)");

            } catch (Exception e) {
                logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                txn.finish();

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

                    statsBuff.write("Statistics for program ImportIsoformSequences:\nInput file: " + inFile.getName()
                            + "\nThere were " + isoformCounter + " isoforms updated.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();
                    
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
