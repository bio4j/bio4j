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

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.IsoformNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.graphdb.index.BatchInserterIndex;
import org.neo4j.graphdb.index.BatchInserterIndexProvider;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 * Imports every uniref(100,90,50) cluster info into Bio4j
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

        if (args.length != 3) {
            System.out.println("This program expects three parameters: \n"
                    + "1. Uniref 100 xml filename \n"
                    + "2. Uniref 90 xml filename \n"
                    + "3. Uniref 50 xml filename \n");
        } else {

            File uniref100File = new File(args[0]);
            File uniref90File = new File(args[1]);
            File uniref50File = new File(args[2]);


            UniRef100MemberRel uniRef100MemberRel = new UniRef100MemberRel(null);
            UniRef50MemberRel uniRef50MemberRel = new UniRef50MemberRel(null);
            UniRef90MemberRel uniRef90MemberRel = new UniRef90MemberRel(null);

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;


            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUniref.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                //------------------indexes creation----------------------------------
                BatchInserterIndex proteinAccessionIndex = indexProvider.nodeIndex(ProteinNode.PROTEIN_ACCESSION_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                BatchInserterIndex isoformIdIndex = indexProvider.nodeIndex(IsoformNode.ISOFORM_ID_INDEX,
                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                //--------------------------------------------------------------------

                //------------------- UNIREF 100----------------------------
                System.out.println("Reading Uniref 100 file...");
                importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref100File, uniRef100MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 90 file...");
                importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref90File, uniRef90MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 50 file...");
                importUnirefFile(inserter, proteinAccessionIndex, isoformIdIndex, uniref50File, uniRef50MemberRel);
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
                    //closing no4j managers
                    indexProvider.shutdown();
                    inserter.shutdown();
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

    private static void importUnirefFile(BatchInserter inserter,
            BatchInserterIndex proteinAccessionIndex,
            BatchInserterIndex isoformIdIndex,
            File unirefFile,
            BasicRelationship relationship) throws Exception {

        StringBuilder entryStBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(unirefFile));
        String line = null;

        while ((line = reader.readLine()) != null) {
            //----we reached a entry line-----
            if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                    entryStBuilder.append(line);
                    line = reader.readLine();
                }
                //linea final del organism
                entryStBuilder.append(line);
                //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
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

                long representantId = -1;

                //---The representant is an isoform----
                if (representantAccession.contains("-")) {
                    representantId = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, representantAccession).getSingle();
                } //---The representant is a protein
                else {
                    representantId = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, representantAccession).getSingle();
                }

                for (String memberAccession : membersAccessionList) {
                    long memberId = -1;
                    if (memberAccession.contains("-")) {
                        memberId = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, memberAccession).getSingle();
                    } else {
                        memberId = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, memberAccession).getSingle();
                    }
                    inserter.createRelationship(representantId, memberId, relationship, null);
                }
            }
        }
        reader.close();

    }
}
