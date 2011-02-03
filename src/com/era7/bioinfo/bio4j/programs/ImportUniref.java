/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import org.neo4j.index.lucene.LuceneIndexBatchInserter;
import org.neo4j.index.lucene.LuceneIndexBatchInserterImpl;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportUniref implements Executable {

    private static final Logger logger = Logger.getLogger("ImportUniref");
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
            LuceneIndexBatchInserter indexService = null;



            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportUniref.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                indexService = new LuceneIndexBatchInserterImpl(inserter);

                //------------------- UNIREF 100----------------------------
                System.out.println("Reading Uniref 100 file...");
                importUnirefFile(inserter, indexService, uniref100File, uniRef100MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 90 file...");
                importUnirefFile(inserter, indexService, uniref90File, uniRef90MemberRel);
                System.out.println("Done! :)");
                System.out.println("Reading Uniref 50 file...");
                importUnirefFile(inserter, indexService, uniref50File, uniRef50MemberRel);
                System.out.println("Done! :)");


            } catch (Exception ex) {
                Logger.getLogger(ImportUniref.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                indexService.shutdown();
                inserter.shutdown();
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
            LuceneIndexBatchInserter indexService,
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
                    representantId = indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, representantAccession);
                } //---The representant is a protein
                else {
                    representantId = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, representantAccession);
                }

                for (String memberAccession : membersAccessionList) {
                    long memberId = -1;
                    if (memberAccession.contains("-")) {
                        memberId = indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, memberAccession);
                    } else {
                        memberId = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, memberAccession);
                    }
                    inserter.createRelationship(representantId, memberId, relationship, null);
                }
            }
        }
        reader.close();

    }
}
