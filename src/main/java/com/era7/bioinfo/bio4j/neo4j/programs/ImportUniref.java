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

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportUniref implements Executable {

    private static final Logger logger = Logger.getLogger("ImportUniref");
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
        main(args);
    }

    public static void main(String[] args) {

        if (args.length != 5) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Uniref 100 xml filename \n"
                    + "2. Uniref 90 xml filename \n"
                    + "3. Uniref 50 xml filename \n"
                    + "4. Bio4j DB folder \n"
                    + "5. batch inserter .properties file");
        } else {

            long initTime = System.nanoTime();

            File uniref100File = new File(args[0]);
            File uniref90File = new File(args[1]);
            File uniref50File = new File(args[2]);


            UniRef100MemberRel uniRef100MemberRel = new UniRef100MemberRel(null);
            UniRef50MemberRel uniRef50MemberRel = new UniRef50MemberRel(null);
            UniRef90MemberRel uniRef90MemberRel = new UniRef90MemberRel(null);

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;

            BufferedWriter statsBuff = null;
            
            int uniref100EntryCounter =0, uniref90EntryCounter = 0, uniref50EntryCounter = 0;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUniref.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);
                
                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportUnirefStats.txt")));

                // create the batch inserter
                inserter = BatchInserters.inserter(args[3], MapUtil.load(new File(args[4])));

                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);

                //------------------indexes creation----------------------------------
                BatchInserterIndex proteinAccessionIndex = indexProvider.nodeIndex(ProteinNode.PROTEIN_ACCESSION_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                BatchInserterIndex isoformIdIndex = indexProvider.nodeIndex(IsoformNode.ISOFORM_ID_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                //--------------------------------------------------------------------

                //------------------- UNIREF 100----------------------------
                System.out.println("Reading Uniref 100 file...");
                uniref100EntryCounter = importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref100File, uniRef100MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 90 file...");
                uniref90EntryCounter = importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref90File, uniRef90MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 50 file...");
                uniref50EntryCounter = importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref50File, uniRef50MemberRel);
                System.out.println("Done! :)");


            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage());
                StackTraceElement[] trace = ex.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {
                try {
                    //closing logger file handler
                    fh.close();
                    //closing neo4j managers
                    indexProvider.shutdown();
                    inserter.shutdown();
                    
                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportUniref:\nInput files: " + 
                            "\nUniref 100 file: " + uniref100File.getName() +
                            "\nUniref 90 file: " + uniref90File.getName() +
                            "\nUniref 50 file: " + uniref50File.getName()
                            + "\nThe following number of entries was parsed:\n"
                            + "Uniref 100 --> " + uniref100EntryCounter + " entries\n"
                            + "Uniref 90 --> " + uniref90EntryCounter + " entries\n"
                            + "Uniref 50 --> " + uniref50EntryCounter + " entries\n"
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

            System.out.println("Program finished!! :D");


        }
    }

    private static String getRepresentantAccession(Element elem) {
        String result = null;
        Element dbReference = elem.getChild("dbReference");
        List<Element> properties = dbReference.getChildren("property");
        for (Element prop : properties) {
            if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
                result = prop.getAttributeValue("value");
            }
        }

        return result;
    }

    private static int importUnirefFile(BatchInserter inserter,
            BatchInserterIndex proteinAccessionIndex,
            BatchInserterIndex isoformIdIndex,
            File unirefFile,
            BasicRelationship relationship) throws Exception {

        StringBuilder entryStBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(unirefFile));
        String line;

        int entryCounter = 0;
        int limitForPrintingOut = 10000;

        while ((line = reader.readLine()) != null) {
            //----we reached a entry line-----
            if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                    entryStBuilder.append(line);
                    line = reader.readLine();
                }
                //organism last line
                entryStBuilder.append(line);
                
                XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                entryStBuilder.delete(0, entryStBuilder.length());

                ArrayList<String> membersAccessionList = new ArrayList<String>();
                Element representativeMember = entryXMLElem.asJDomElement().getChild("representativeMember");
                String representantAccession = getRepresentantAccession(representativeMember);

                List<Element> members = entryXMLElem.asJDomElement().getChildren("member");
                for (Element member : members) {
                    Element memberDbReference = member.getChild("dbReference");
                    List<Element> memberProperties = memberDbReference.getChildren("property");
                    for (Element prop : memberProperties) {
                        if (prop.getAttributeValue("type").equals("UniProtKB accession")) {
                            String memberAccession = prop.getAttributeValue("value");
                            membersAccessionList.add(memberAccession);
                        }
                    }
                }


                if (representantAccession != null) {

                    long representantId = -1;

                    //---The representant is an isoform----
                    if (representantAccession.contains("-")) {

                        IndexHits<Long> repIndexHits = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, representantAccession);
                        if (repIndexHits.size() == 1) {
                            representantId = repIndexHits.getSingle();
                        }
                        repIndexHits.close();

                    } //---The representant is a protein
                    else {

                        IndexHits<Long> hits = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, representantAccession);
                        if (hits.size() == 1) {
                            //System.out.println("representantAccession = " + representantAccession);
                            representantId = hits.getSingle();
                        }
                        hits.close();

                    }

                    //----we only create the relationships in the case where we found
                    // a valid representant id-----
                    if (representantId >= 0) {

                        for (String memberAccession : membersAccessionList) {
                            long memberId = -1;
                            if (memberAccession.contains("-")) {
                                IndexHits<Long> isoHits = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, memberAccession);
                                if (isoHits.size() == 1) {
                                    memberId = isoHits.getSingle();
                                }
                                isoHits.close();
                            } else {
                                IndexHits<Long> protHits = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, memberAccession);
                                if (protHits.size() == 1) {
                                    memberId = protHits.getSingle();
                                }
                                protHits.close();
                            }

                            if (memberId >= 0) {
                                inserter.createRelationship(representantId, memberId, relationship, null);
                            }

                        }
                    }
                } else {
                    logger.log(Level.SEVERE, ("null representant accession for entry: " + entryXMLElem.asJDomElement().getAttributeValue("id")));
                }


            }

            entryCounter++;
            if ((entryCounter % limitForPrintingOut) == 0) {
                logger.log(Level.INFO, (entryCounter + " entries parsed!!"));
            }

        }
        reader.close();
        
        return entryCounter;
    }
}
