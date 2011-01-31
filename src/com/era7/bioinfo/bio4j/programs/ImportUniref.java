/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4jmodel.relationships.uniref.UniRef90MemberRel;
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

    private static final Logger logger = Logger.getLogger("ImportaUniref");
    private static FileHandler fh;
    public static int contadorIsoformas = 0;

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
            System.out.println("El programa espera tres parametros: \n"
                    + "1. Nombre del archivo xml de uniref 100 a importar \n"
                    + "2. Nombre del archivo xml de uniref 90 a importar \n"
                    + "3. Nombre del archivo xml de uniref 50 a importar \n");
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
                fh = new FileHandler("ImportaUniref.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                //inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                //indexService = new LuceneIndexBatchInserterImpl(inserter);

                StringBuilder entryStBuilder = new StringBuilder();

                BufferedReader reader = new BufferedReader(new FileReader(uniref100File));
                String line = null;

                //-----------------------------------------------------------
                //------------------- UNIREF 100----------------------------

                System.out.println("Reading Uniref 100 file...");

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

                        if (representantAccession.contains("-")) {
                            //Here we already have the ids of the members of the cluster and the representatn id
                            for (String memberAccession : membersAccessionList) {
                            }
                            
                        }else{
                            long representantId = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, representantAccession);
                        }
                        
                        


                    }
                }
                reader.close();

                System.out.println("Done! :)");
                System.out.println(contadorIsoformas + " representant isoforms where found in UniRef100...");

            } catch (Exception ex) {
                Logger.getLogger(ImportUniref.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                //indexService.shutdown();
                //inserter.shutdown();
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
                if (result.contains("-")) {
                    System.out.println("representantAccession = " + result);
                    contadorIsoformas++;
                }
            }
        }
        return result;
    }
}
