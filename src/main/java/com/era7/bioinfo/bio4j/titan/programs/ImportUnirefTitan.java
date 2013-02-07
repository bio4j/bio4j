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

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.wrappers.batch.BatchGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom.Element;

/**
 * Imports uniref(100,90,50) clusters info into Bio4j
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportUnirefTitan implements Executable {

    private static final Logger logger = Logger.getLogger("ImportUnirefTitan");
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

        if (args.length != 4) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Uniref 100 xml filename \n"
                    + "2. Uniref 90 xml filename \n"
                    + "3. Uniref 50 xml filename \n"
                    + "4. Bio4j DB folder \n");
        } else {

            long initTime = System.nanoTime();

            File uniref100File = new File(args[0]);
            File uniref90File = new File(args[1]);
            File uniref50File = new File(args[2]);

            BufferedWriter statsBuff = null;

            int uniref100EntryCounter = 0, uniref90EntryCounter = 0, uniref50EntryCounter = 0;

            Bio4jManager manager = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUnirefTitan.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportUnirefStats.txt")));

                //----------DB configuration------------------
                Configuration conf = new BaseConfiguration();
                conf.setProperty("storage.directory", args[3]);
                conf.setProperty("storage.backend", "local");

                //-------creating graph handlers---------------------
                manager = new Bio4jManager(conf);
                BatchGraph bGraph = new BatchGraph(manager.getGraph(), BatchGraph.IdType.STRING, 1000);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);

                //------------------- UNIREF 100----------------------------
                System.out.println("Reading Uniref 100 file...");
                uniref100EntryCounter = importUnirefFile(bGraph, nodeRetriever, uniref100File, UniRef100MemberRel.NAME);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 90 file...");
                uniref90EntryCounter = importUnirefFile(bGraph, nodeRetriever, uniref90File, UniRef90MemberRel.NAME);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 50 file...");
                uniref50EntryCounter = importUnirefFile(bGraph, nodeRetriever, uniref50File, UniRef50MemberRel.NAME);
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
                    //closing managers
                    manager.shutDown();

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportUniref:\nInput files: "
                            + "\nUniref 100 file: " + uniref100File.getName()
                            + "\nUniref 90 file: " + uniref90File.getName()
                            + "\nUniref 50 file: " + uniref50File.getName()
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

    private static int importUnirefFile(BatchGraph bGraph,
            NodeRetriever nodeRetriever,
            File unirefFile,
            String edgeName) throws Exception {

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

                    Vertex vertex = null;

                    //---The representant is an isoform----
                    if (representantAccession.contains("-")) {

                        vertex = nodeRetriever.getIsoformById(representantAccession).getNode();

                    } //---The representant is a protein
                    else {

                        vertex = nodeRetriever.getProteinNodeByAccession(representantAccession).getNode();

                    }

                    if (vertex != null) {
                        for (String memberAccession : membersAccessionList) {

                            Vertex memberVertex = null;

                            if (memberAccession.contains("-")) {
                                IsoformNode tempIsoformNode = nodeRetriever.getIsoformById(memberAccession);
                                if (tempIsoformNode != null) {
                                    memberVertex = tempIsoformNode.getNode();
                                }
                            } else {
                                ProteinNode tempProteinNode = nodeRetriever.getProteinNodeByAccession(memberAccession);
                                if(tempProteinNode != null){
                                    memberVertex = tempProteinNode.getNode();
                                }
                            }

                            if (memberVertex != null) {
                                bGraph.addEdge(null, vertex, memberVertex, edgeName);
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
