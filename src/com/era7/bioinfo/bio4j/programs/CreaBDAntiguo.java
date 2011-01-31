/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexService;
import org.neo4j.index.lucene.LuceneIndexBatchInserter;
import org.neo4j.index.lucene.LuceneIndexBatchInserterImpl;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 *
 * @author ppareja
 */
public class CreaBDAntiguo {

    public static final String PROPERTIES_FILE_NAME = "batchInserter.properties";

    public static void main(String[] args) {
        File inFile = new File(args[0]);

        //boolean transactionDone = true;

//        try {
//            // create the batch inserter
//            BatchInserter inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(PROPERTIES_FILE_NAME));
//
//            // create the batch index service
//            LuceneIndexBatchInserter indexService = new LuceneIndexBatchInserterImpl(inserter);
//
//            Map<String, Object> organismProperties = new HashMap<String, Object>();
//            Map<String, Object> proteinProperties = new HashMap<String, Object>();
//            Map<String, Object> keywordProperties = new HashMap<String, Object>();
//            Map<String, Object> taxonProperties = new HashMap<String, Object>();
//
//
//            //Neo4jManager manager = Neo4jManager.getNeo4JManager(CommonData.DATABASE_FOLDER);
//            //IndexService indexService = manager.getIndexService();
//
//            BufferedReader reader = new BufferedReader(new FileReader(inFile));
//            String line = null;
//            StringBuilder entryStBuilder = new StringBuilder();
//
//
//            int contador = 1;
//            int limitForPrintingOut = 10000;
//            //int maxPerTxn = 10000;
//
//            //Transaction txn = null;
//
//            while ((line = reader.readLine()) != null) {
//                if (line.trim().startsWith("<entry")) {
//
//                    while (!line.trim().startsWith("</entry>")) {
//                        entryStBuilder.append(line);
//                        line = reader.readLine();
//                    }
//                    //linea final del organism
//                    entryStBuilder.append(line);
//                    //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
//                    XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
//                    entryStBuilder.delete(0, entryStBuilder.length());
//
//                    //System.out.println("Starting transacion...");
//
////                    if (transactionDone) {
////                        txn = manager.beginTransaction();
////                        transactionDone = false;
////                    }
//
//
//                    try {
//
//                        String accessionSt = entryXMLElem.asJDomElement().getChildText("accession");
//                        String nameSt = entryXMLElem.asJDomElement().getChildText("name");
////                        Protein currentProtein = new Protein(manager.createNode());
////                        currentProtein.setAccession(accessionSt);
////                        currentProtein.setName(nameSt);
////                        indexService.index(currentProtein.getNode(), Protein.PROTEIN_ACCESSION_NAME_INDEX, accessionSt);
//
//                        proteinProperties.put(Protein.ACCESSION_PROPERTY, accessionSt);
//                        proteinProperties.put(Protein.NAME_PROPERTY, nameSt);
//                        long currentProteinId = inserter.createNode(proteinProperties);
//                        indexService.index(currentProteinId, Protein.PROTEIN_ACCESSION_NAME_INDEX, accessionSt);
//                        // optimize the index
//                        //indexService.optimize();
//
//
//
//                        //-----------keywords---------------------
//                        List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren("keyword");
//                        for (Element keywordElem : keywordsList) {
//                            String keywordId = keywordElem.getAttributeValue("id");
//                            String keywordName = keywordElem.getText();
////                            Node keywordNode = indexService.getSingleNode(Keyword.ID_PROPERTY, keywordId);
//                            long keywordNodeId = indexService.getSingleNode(Keyword.ID_PROPERTY, keywordId);
////                            Keyword currentKeyword = null;
////                            if (keywordNode == null) {
////                                currentKeyword = new Keyword(manager.createNode());
////                                currentKeyword.setId(keywordId);
////                                currentKeyword.setName(keywordName);
////
////                                indexService.index(currentKeyword.getNode(), Keyword.KEYWORD_ID_INDEX, keywordId);
////                                indexService.index(currentKeyword.getNode(), Keyword.KEYWORD_NAME_INDEX, keywordName);
////                            } else {
////                                currentKeyword = new Keyword(keywordNode);
////                            }
////                            currentProtein.getNode().createRelationshipTo(currentKeyword.getNode(), RelationshipTypes.PROTEIN_KEYWORD);
//                            if (keywordNodeId < 0) {
//
//                                keywordProperties.put(Keyword.ID_PROPERTY, keywordId);
//                                keywordProperties.put(Keyword.NAME_PROPERTY, keywordName);
//
//                                keywordNodeId = inserter.createNode(keywordProperties);
//
//                                indexService.index(keywordNodeId, Keyword.KEYWORD_ID_INDEX, keywordId);
//                                indexService.index(keywordNodeId, Keyword.KEYWORD_NAME_INDEX, keywordName);
//                                //indexService.optimize();
//                            }
//                        }
//
//                        //-----------organism--------------------
//                        String scName, commName, synName;
//                        scName = "";
//                        commName = "";
//                        synName = "";
//
//                        List<Element> organismNames = entryXMLElem.asJDomElement().getChild("organism").getChildren("name");
//                        for (Element element : organismNames) {
//                            String type = element.getAttributeValue("type");
//                            if (type.equals("scientific")) {
//                                scName = element.getText();
//                            } else if (type.equals("common")) {
//                                commName = element.getText();
//                            } else if (type.equals("synonym")) {
//                                synName = element.getText();
//                            }
//                        }
//
//                        //Organism organism = null;
//                        //Node orgNode = indexService.getSingleNode(Organism.ORGANISM_SCIENTIFIC_NAME_INDEX, scName);
//                        long organismNodeId = indexService.getSingleNode(Organism.ORGANISM_SCIENTIFIC_NAME_INDEX, scName);
//                        if (organismNodeId < 0) {
////                            organism = new Organism(manager.createNode());
////                            organism.setScientificName(scName);
////                            organism.setCommonName(commName);
////                            organism.setSynonymName(synName);
//
//
//                            organismProperties.put(Organism.COMMON_NAME_PROPERTY,commName);
//                            organismProperties.put(Organism.SCIENTIFIC_NAME_PROPERTY,scName);
//                            organismProperties.put(Organism.SYNONYM_NAME_PROPERTY, synName);
//
//
//                            List<Element> dbRefElems = entryXMLElem.asJDomElement().getChildren("dbReference");
//                            boolean ncbiIdFound = false;
//                            if (dbRefElems != null) {
//                                for (Element dbRefElem : dbRefElems) {
//                                    String t = dbRefElem.getAttributeValue("type");
//                                    if (t.equals("NCBI Taxonomy")) {
//                                        //organism.setNcbiTaxonomyId(dbRefElem.getAttributeValue("id"));
//                                        organismProperties.put(Organism.NCBI_TAXONOMY_ID_PROPERTY, dbRefElem.getAttributeValue("id"));
//                                        ncbiIdFound = true;
//                                    }
//                                    break;
//                                }
//                            }
//                            if (!ncbiIdFound) {
//                                //organism.setNcbiTaxonomyId("");
//                                organismProperties.put(Organism.NCBI_TAXONOMY_ID_PROPERTY, "");
//                            }
//                            organismNodeId = inserter.createNode(organismProperties);
//
//                            //indexService.index(organism.getNode(), Organism.ORGANISM_SCIENTIFIC_NAME_INDEX, organism.getScientificName());
//                            indexService.index(organismNodeId, Organism.ORGANISM_SCIENTIFIC_NAME_INDEX, scName);
//                            indexService.index(organismNodeId, Organism.NCBI_TAXONOMY_ID_PROPERTY, organismProperties.get(Organism.NCBI_TAXONOMY_ID_PROPERTY));
//                            //indexService.optimize();
//
//                            Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
//                            List<Element> taxons = lineage.getChildren("taxon");
//
//                            Element firstTaxonElem = taxons.get(0);
//                            //Node tempNode = indexService.getSingleNode(Taxon.TAXON_NAME_INDEX, firstTaxonElem.getText());
//
//                            long firstTaxonId = indexService.getSingleNode(Taxon.TAXON_NAME_INDEX, firstTaxonElem.getText());
//
//                            //Taxon firstTaxon = null;
//                            //String firstTaxonId = null;
//                            if (firstTaxonId < 0) {
////                                firstTaxon = new Taxon(manager.createNode());
////                                firstTaxon.setName(firstTaxonElem.getText());
////                                indexService.index(firstTaxon.getNode(), Taxon.TAXON_NAME_INDEX, firstTaxon.getName());
////                                manager.getReferenceNode().createRelationshipTo(firstTaxon.getNode(), RelationshipTypes.MAIN_TAXON);
//
//                                String firstTaxonName = firstTaxonElem.getText();
//                                taxonProperties.put(Taxon.NAME_PROPERTY, firstTaxonElem.getText());
//                                firstTaxonId = inserter.createNode(taxonProperties);
//                                indexService.index(firstTaxonId, Taxon.TAXON_NAME_INDEX, firstTaxonName);
//                                //indexService.optimize();
//                                inserter.createRelationship(inserter.getReferenceNode(), firstTaxonId, new MainTaxonRel(null),null);
//
//                            } else {
//                                //firstTaxon = new Taxon(tempNode);
//                                //firstTaxonName = firstTaxonElem.getText();
//                            }
//
//                            //Taxon lastTaxon = firstTaxon;
//                            long lastTaxonId = firstTaxonId;
//                            for (int i = 1; i < taxons.size(); i++) {
//                                String taxonName = taxons.get(i).getText();
//                                //Node currentNode = indexService.getSingleNode(Taxon.TAXON_NAME_INDEX, taxonName);
//                                long currentTaxonId = indexService.getSingleNode(Taxon.TAXON_NAME_INDEX, taxonName);
//                                //Taxon currentTaxon = null;
//                                if (currentTaxonId < 0) {
////                                    currentTaxon = new Taxon(manager.createNode());
////                                    currentTaxon.setName(taxonName);
////                                    indexService.index(currentTaxon.getNode(), Taxon.TAXON_NAME_INDEX, currentTaxon.getName());
////                                    lastTaxon.getNode().createRelationshipTo(currentTaxon.getNode(), RelationshipTypes.TAXON_PARENT);
//
//                                    taxonProperties.put(Taxon.NAME_PROPERTY, taxonName);
//                                    currentTaxonId = inserter.createNode(taxonProperties);
//                                    indexService.index(currentTaxonId, Taxon.TAXON_NAME_INDEX, taxonName);
//                                    //indexService.optimize();
//                                    inserter.createRelationship(lastTaxonId, currentTaxonId, new TaxonParentRel(null), null);
//
//
//                                } else {
//                                    //currentTaxon = new Taxon(currentNode);
//                                }
//                                lastTaxonId = currentTaxonId;
//                            }
//
//                            //lastTaxon.getNode().createRelationshipTo(organism.getNode(), RelationshipTypes.TAXON_PARENT);
//                            inserter.createRelationship(lastTaxonId, organismNodeId, new TaxonParentRel(null), null);
//
//                        } else {
//                            //organism = new Organism(orgNode);
//                        }
//
//                        //currentProtein.getNode().createRelationshipTo(organism.getNode(), RelationshipTypes.PROTEIN_ORGANISM);
//                        inserter.createRelationship(currentProteinId, organismNodeId, new ProteinOrganismRel(null), null);
//
//                        contador++;
//                        if ((contador % limitForPrintingOut) == 0) {
//                            //txn.success();
//                            //System.out.println("Transaction commited successfully!");
//                            System.out.println(contador + " proteins inserted!!");
//                        }
//
//                    } catch (Exception e) {
//                        //e.printStackTrace();
////                        txn.failure();
////                        txn.finish();
//                    } finally {
////                        if (contador >= maxPerTxn) {
////                            contador = 0;
////                            txn.finish();
////                            transactionDone = true;
////                        }
//                    }
//
//
//                }
//            }
//
////            txn.success();
////            txn.finish();
////
////            manager.shutDown();
//
//            // shutdown, makes sure all changes are written to disk
//            inserter.shutdown();
//            indexService.shutdown();
//
//
//        } catch (Exception e) {
//            //e.printStackTrace();
//        }



    }
}
