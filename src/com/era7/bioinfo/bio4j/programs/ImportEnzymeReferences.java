/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.EnzymeNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinEnzymaticActivityRel;
import com.era7.bioinfo.bio4jmodel.util.Bio4jManager;
import com.era7.bioinfo.bio4jmodel.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportEnzymeReferences implements Executable {
    
    private static final Logger logger = Logger.getLogger("ImportEnzymeReferences");
    private static FileHandler fh;
    
    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        try {
            main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("This program expects two parameters: \n"
                    + "1. Uniprot xml filename \n"
                    + "2. Bio4j DB folder");
        } else {
            File inFile = new File(args[0]);
            
            Transaction txn = null;
            Bio4jManager manager = null;
            

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImportEnzymeReferences.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                logger.log(Level.INFO, "creating manager...");
                manager = new Bio4jManager(args[1]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                txn = manager.beginTransaction();

                logger.log(Level.INFO, "reading Uniprot file....");
                
                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder entryStBuilder = new StringBuilder();
                
                ProteinEnzymaticActivityRel proteinEnzymaticActivityRel = new ProteinEnzymaticActivityRel(null);
                
                int relCounter = 0;
                
                while ((line = reader.readLine()) != null) {
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

                        String accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);
                        
                        List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(CommonData.DB_REFERENCE_TAG_NAME);
                        for (Element dbReferenceElem : dbReferenceList) {
                            String refId = dbReferenceElem.getAttributeValue("id");
                            if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("EC")) {
                                
                                ProteinNode proteinNode = nodeRetriever.getProteinNodeByAccession(accessionSt);
                                EnzymeNode enzymeNode = nodeRetriever.getEnzymeById(refId);
                                if(enzymeNode != null){
                                    proteinNode.getNode().createRelationshipTo(enzymeNode.getNode(), proteinEnzymaticActivityRel);                                
                                    relCounter++;
                                }else{
                                    System.out.println("Enzyme not found for id: " + refId);
                                }
                                
                                if(relCounter % 1000 == 0){                                    
                                    txn.success();
                                    txn.finish();
                                    txn = manager.beginTransaction();
                                    System.out.println(relCounter + " protein enzymatic activity rels created...");
                                }
                            } 
                        }
                    }
                }

                txn.success();
                
                logger.log(Level.INFO, "Done! :)");

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
                txn.failure();
            } finally {

                txn.finish();

                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();
            }

        }
    }
    
    
}
