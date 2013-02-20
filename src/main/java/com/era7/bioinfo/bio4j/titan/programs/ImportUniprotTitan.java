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
package com.era7.bioinfo.bio4j.titan.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.InstituteCountryRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.IsoformEventGeneratorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.SubcellularLocationParentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.TaxonParentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleJournalRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.book.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleJournalRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionDbRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisInstituteRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.titan.model.ProteinNode;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.titan.model.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfoxml.bio4j.UniprotDataXML;
import com.era7.lib.era7xmlapi.model.XMLElement;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.TransactionalGraph;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom.Element;

/**
 * This class deals with the main part of Bio4j importing process.
 * ImportGeneOntology importation must have been performed prior to this step.
 * Features, comments, GeneOntology annotations and all information directly
 * related to entries are imported in this step, (except protein interactions
 * and isoform sequences).
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportUniprotTitan implements Executable {

    private static final Logger logger = Logger.getLogger("ImportUniprotTitan");
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
                    + "1. Uniprot xml filename \n"
                    + "2. Bio4j DB folder \n"
                    + "3. Config XML file");
        } else {

            long initTime = System.nanoTime();

            File inFile = new File(args[0]);
            File configFile = new File(args[2]);

            String currentAccessionId = "";

            BufferedWriter enzymeIdsNotFoundBuff = null;
            BufferedWriter statsBuff = null;

            int proteinCounter = 0;
            int limitForPrintingOut = 10000;

            //------------------ init DB handlers------------------------
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", args[1]);
            conf.setProperty("storage.backend", "local");

            Bio4jManager manager = new Bio4jManager(conf);
            NodeRetriever nodeRetriever = new NodeRetriever(manager);
            TitanGraph graph = manager.getGraph();

            try {

                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportUniprotTitan" + args[0].split("\\.")[0] + ".log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                System.out.println("Reading conf file...");
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String line;
                StringBuilder stBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stBuilder.append(line);
                }
                reader.close();

                UniprotDataXML uniprotDataXML = new UniprotDataXML(stBuilder.toString());

                //---creating writer for enzymes not found file-----
                enzymeIdsNotFoundBuff = new BufferedWriter(new FileWriter(new File("EnzymeIdsNotFound.log")));

                //---creating writer for stats file-----
                statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniprotTitanStats_" + inFile.getName().split("\\.")[0] + ".txt")));

                reader = new BufferedReader(new FileReader(inFile));
                StringBuilder entryStBuilder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            entryStBuilder.append(line);
                            line = reader.readLine();
                        }

                        ProteinNode currentProteinNode = new ProteinNode(manager.createNode(ProteinNode.NODE_TYPE));

                        //linea final del organism
                        entryStBuilder.append(line);
                        //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                        XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                        entryStBuilder.delete(0, entryStBuilder.length());

                        String modifiedDateSt = entryXMLElem.asJDomElement().getAttributeValue(CommonData.ENTRY_MODIFIED_DATE_ATTRIBUTE);

                        String accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);
                        String nameSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_NAME_TAG_NAME);
                        String fullNameSt = getProteinFullName(entryXMLElem.asJDomElement().getChild(CommonData.PROTEIN_TAG_NAME));
                        String shortNameSt = getProteinShortName(entryXMLElem.asJDomElement().getChild(CommonData.PROTEIN_TAG_NAME));

                        if (shortNameSt == null) {
                            shortNameSt = "";
                        }
                        if (fullNameSt == null) {
                            fullNameSt = "";
                        }

                        currentAccessionId = accessionSt;

                        List<String> alternativeAccessions = new LinkedList<String>();
                        //-----------alternative accessions-------------
                        List<Element> altAccessionsList = entryXMLElem.asJDomElement().getChildren(CommonData.ENTRY_ACCESSION_TAG_NAME);
                        for (int i = 1; i < altAccessionsList.size(); i++) {
                            alternativeAccessions.add(altAccessionsList.get(i).getText());
                        }

                        currentProteinNode.setAlternativeAccessions(alternativeAccessions.toArray(new String[alternativeAccessions.size()]));

                        //-----db references-------------
                        String pirIdSt = "";
                        String keggIdSt = "";
                        String ensemblIdSt = "";
                        String uniGeneIdSt = "";
                        String arrayExpressIdSt = "";

                        List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(CommonData.DB_REFERENCE_TAG_NAME);
                        ArrayList<String> emblCrossReferences = new ArrayList<String>();
                        ArrayList<String> refseqReferences = new ArrayList<String>();
                        ArrayList<String> enzymeDBReferences = new ArrayList<String>();
                        ArrayList<String> ensemblPlantsReferences = new ArrayList<String>();
                        HashMap<String, String> reactomeReferences = new HashMap<String, String>();

                        for (Element dbReferenceElem : dbReferenceList) {
                            String refId = dbReferenceElem.getAttributeValue("id");
                            if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("Ensembl")) {
                                ensemblIdSt = refId;
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("PIR")) {
                                pirIdSt = refId;
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("UniGene")) {
                                uniGeneIdSt = refId;
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("KEGG")) {
                                keggIdSt = refId;
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("EMBL")) {
                                emblCrossReferences.add(refId);
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("EC")) {
                                enzymeDBReferences.add(refId);
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("ArrayExpress")) {
                                arrayExpressIdSt = refId;
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("RefSeq")) {
                                //refseqReferences.add(refId);
                                List<Element> children = dbReferenceElem.getChildren("property");
                                for (Element propertyElem : children) {
                                    if (propertyElem.getAttributeValue("type").equals("nucleotide sequence ID")) {
                                        refseqReferences.add(propertyElem.getAttributeValue("value"));
                                    }
                                }
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("Reactome")) {
                                Element propertyElem = dbReferenceElem.getChild("property");
                                String pathwayName = "";
                                if (propertyElem.getAttributeValue("type").equals("pathway name")) {
                                    pathwayName = propertyElem.getAttributeValue("value");
                                }
                                reactomeReferences.put(refId, pathwayName);
                            } else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("EnsemblPlants")) {
                                ensemblPlantsReferences.add(refId);
                            }

                        }

                        Element sequenceElem = entryXMLElem.asJDomElement().getChild(CommonData.ENTRY_SEQUENCE_TAG_NAME);
                        String sequenceSt = sequenceElem.getText();
                        int seqLength = Integer.parseInt(sequenceElem.getAttributeValue(CommonData.SEQUENCE_LENGTH_ATTRIBUTE));
                        float seqMass = Float.parseFloat(sequenceElem.getAttributeValue(CommonData.SEQUENCE_MASS_ATTRIBUTE));

                        currentProteinNode.setModifiedDate(modifiedDateSt);
                        currentProteinNode.setAccession(accessionSt);
                        currentProteinNode.setName(nameSt);
                        currentProteinNode.setFullName(fullNameSt);
                        currentProteinNode.setShortName(shortNameSt);
                        currentProteinNode.setSequence(sequenceSt);
                        currentProteinNode.setLength(seqLength);
                        currentProteinNode.setMass(seqMass);
                        currentProteinNode.setArrayExpressId(arrayExpressIdSt);
                        currentProteinNode.setPIRId(pirIdSt);
                        currentProteinNode.setKeggId(keggIdSt);
                        currentProteinNode.setEMBLreferences(convertToStringArray(emblCrossReferences));
                        currentProteinNode.setEnsemblPlantsReferences(convertToStringArray(ensemblPlantsReferences));
                        currentProteinNode.setUniGeneId(uniGeneIdSt);
                        currentProteinNode.setEnsemblId(ensemblIdSt);

                        //---------------gene-names-------------------
                        Element geneElement = entryXMLElem.asJDomElement().getChild(CommonData.GENE_TAG_NAME);
                        ArrayList<String> geneNames = new ArrayList<String>();
                        if (geneElement != null) {
                            List<Element> genesList = geneElement.getChildren(CommonData.GENE_NAME_TAG_NAME);
                            for (Element geneNameElem : genesList) {
                                geneNames.add(geneNameElem.getText());
                            }
                        }
                        currentProteinNode.setGeneNames(convertToStringArray(geneNames));
                        //-----------------------------------------


                        //--------------refseq associations----------------
                        if (uniprotDataXML.getRefseq()) {
                            for (String refseqReferenceSt : refseqReferences) {

                                GenomeElementNode genomeElementNode = nodeRetriever.getGenomeElementByVersion(refseqReferenceSt);

                                if (genomeElementNode != null) {
                                    graph.addEdge(null, currentProteinNode.getNode(), genomeElementNode.getNode(), ProteinGenomeElementRel.NAME);
                                } else {
                                    logger.log(Level.INFO, ("GenomeElem not found for: " + currentAccessionId + " , " + refseqReferenceSt));
                                }

                            }
                        }

                        //--------------reactome associations----------------
                        if (uniprotDataXML.getReactome()) {
                            for (String reactomeId : reactomeReferences.keySet()) {

                                ReactomeTermNode reactomeTermNode = nodeRetriever.getReactomeTermById(reactomeId);

                                if (reactomeTermNode == null) {
                                    reactomeTermNode = new ReactomeTermNode(manager.createNode(ReactomeTermNode.NODE_TYPE));
                                    reactomeTermNode.setId(reactomeId);
                                    reactomeTermNode.setPathwayName(reactomeReferences.get(reactomeId));
                                }

                                graph.addEdge(null, currentProteinNode.getNode(), reactomeTermNode.getNode(), ProteinReactomeRel.NAME);
                            }
                        }
                        //-------------------------------------------------------

                        //---------------enzyme db associations----------------------
                        if (uniprotDataXML.getEnzymeDb()) {
                            for (String enzymeDBRef : enzymeDBReferences) {

                                EnzymeNode enzymeNode = nodeRetriever.getEnzymeById(enzymeDBRef);

                                if (enzymeNode != null) {
                                    graph.addEdge(null, currentProteinNode.getNode(), enzymeNode.getNode(), ProteinEnzymaticActivityRel.NAME);
                                } else {
                                    enzymeIdsNotFoundBuff.write("Enzyme term: " + enzymeDBRef + " not found.\t" + currentAccessionId);
                                }
                            }
                        }
                        //------------------------------------------------------------


                        //-----comments import---
                        if (uniprotDataXML.getComments()) {
                            importProteinComments(entryXMLElem, graph, manager, nodeRetriever, currentProteinNode, sequenceSt, uniprotDataXML);
                        }

                        //-----features import----
                        if (uniprotDataXML.getFeatures()) {
                            importProteinFeatures(entryXMLElem, graph, manager, nodeRetriever, currentProteinNode);
                        }

                        //--------------------------------datasets--------------------------------------------------
                        String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(CommonData.ENTRY_DATASET_ATTRIBUTE);

                        DatasetNode datasetNode = nodeRetriever.getDatasetByName(proteinDataSetSt);

                        if (datasetNode == null) {
                            datasetNode = new DatasetNode(manager.createNode(DatasetNode.NODE_TYPE));
                            datasetNode.setName(proteinDataSetSt);
                        }
                        graph.addEdge(null, currentProteinNode.getNode(), datasetNode.getNode(), ProteinDatasetRel.NAME);
                        //---------------------------------------------------------------------------------------------


                        if (uniprotDataXML.getCitations()) {
                            importProteinCitations(entryXMLElem,
                                    graph,
                                    manager,
                                    nodeRetriever,
                                    currentProteinNode,
                                    uniprotDataXML);
                        }


                        //-------------------------------keywords------------------------------------------------------
                        if (uniprotDataXML.getKeywords()) {
                            List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(CommonData.KEYWORD_TAG_NAME);
                            for (Element keywordElem : keywordsList) {
                                String keywordId = keywordElem.getAttributeValue(CommonData.KEYWORD_ID_ATTRIBUTE);
                                String keywordName = keywordElem.getText();

                                KeywordNode keywordNode = nodeRetriever.getKeywordById(keywordId);

                                if (keywordNode == null) {

                                    keywordNode = new KeywordNode(manager.createNode(KeywordNode.NAME_PROPERTY));
                                    keywordNode.setId(keywordId);
                                    keywordNode.setName(keywordName);

                                }
                                graph.addEdge(null, currentProteinNode.getNode(), currentProteinNode.getNode(), ProteinKeywordRel.NAME);
                            }
                        }
                        //---------------------------------------------------------------------------------------


                        for (Element dbReferenceElem : dbReferenceList) {

                            //-------------------------------INTERPRO------------------------------------------------------  
                            if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.INTERPRO_DB_REFERENCE_TYPE)) {

                                if (uniprotDataXML.getInterpro()) {
                                    String interproId = dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_ID_ATTRIBUTE);

                                    InterproNode interproNode = nodeRetriever.getInterproById(interproId);

                                    if (interproNode == null) {
                                        String interproEntryNameSt = "";
                                        List<Element> properties = dbReferenceElem.getChildren(CommonData.DB_REFERENCE_PROPERTY_TAG_NAME);
                                        for (Element prop : properties) {
                                            if (prop.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.INTERPRO_ENTRY_NAME)) {
                                                interproEntryNameSt = prop.getAttributeValue(CommonData.DB_REFERENCE_VALUE_ATTRIBUTE);
                                                break;
                                            }
                                        }

                                        interproNode = new InterproNode(manager.createNode(InterproNode.NODE_TYPE));
                                        interproNode.setId(interproId);
                                        interproNode.setName(interproEntryNameSt);

                                    }

                                    graph.addEdge(null, currentProteinNode.getNode(), interproNode.getNode(), ProteinInterproRel.NAME);
                                }

                            } //-------------------------------PFAM------------------------------------------------------  
                            else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("Pfam")) {

                                if (uniprotDataXML.getPfam()) {
                                    String pfamId = dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_ID_ATTRIBUTE);

                                    PfamNode pfamNode = nodeRetriever.getPfamById(pfamId);

                                    if (pfamNode == null) {
                                        String pfamEntryNameSt = "";
                                        List<Element> properties = dbReferenceElem.getChildren(CommonData.DB_REFERENCE_PROPERTY_TAG_NAME);
                                        for (Element prop : properties) {
                                            if (prop.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals("entry name")) {
                                                pfamEntryNameSt = prop.getAttributeValue(CommonData.DB_REFERENCE_VALUE_ATTRIBUTE);
                                                break;
                                            }
                                        }
                                        pfamNode = new PfamNode(manager.createNode(PfamNode.NODE_TYPE));
                                        pfamNode.setId(pfamId);
                                        pfamNode.setName(pfamEntryNameSt);
                                    }

                                    graph.addEdge(null, currentProteinNode.getNode(), pfamNode.getNode(), ProteinPfamRel.NAME);
                                }


                            } //-------------------GO -----------------------------
                            else if (dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).toUpperCase().equals(CommonData.GO_DB_REFERENCE_TYPE)) {

                                if (uniprotDataXML.getGeneOntology()) {
                                    String goId = dbReferenceElem.getAttributeValue(CommonData.DB_REFERENCE_ID_ATTRIBUTE);
                                    String evidenceSt = "";
                                    List<Element> props = dbReferenceElem.getChildren(CommonData.DB_REFERENCE_PROPERTY_TAG_NAME);
                                    for (Element element : props) {
                                        if (element.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE).equals(CommonData.EVIDENCE_TYPE_ATTRIBUTE)) {
                                            evidenceSt = element.getAttributeValue("value");
                                            if (evidenceSt == null) {
                                                evidenceSt = "";
                                            }
                                            break;
                                        }
                                    }
                                    GoTermNode goTermNode = nodeRetriever.getGoTermById(goId);
                                    ProteinGoRel proteinGoRel = new ProteinGoRel(graph.addEdge(null, currentProteinNode.getNode(), goTermNode.getNode(), ProteinGoRel.NAME));
                                    proteinGoRel.setEvidence(evidenceSt);
                                }

                            }

                        }
                        //---------------------------------------------------------------------------------------

                        //---------------------------------------------------------------------------------------
                        //--------------------------------organism-----------------------------------------------

                        String scName, commName, synName;
                        scName = "";
                        commName = "";
                        synName = "";

                        Element organismElem = entryXMLElem.asJDomElement().getChild(CommonData.ORGANISM_TAG_NAME);

                        List<Element> organismNames = organismElem.getChildren(CommonData.ORGANISM_NAME_TAG_NAME);
                        for (Element element : organismNames) {
                            String type = element.getAttributeValue(CommonData.ORGANISM_NAME_TYPE_ATTRIBUTE);
                            if (type.equals(CommonData.ORGANISM_SCIENTIFIC_NAME_TYPE)) {
                                scName = element.getText();
                            } else if (type.equals(CommonData.ORGANISM_COMMON_NAME_TYPE)) {
                                commName = element.getText();
                            } else if (type.equals(CommonData.ORGANISM_SYNONYM_NAME_TYPE)) {
                                synName = element.getText();
                            }
                        }

                        OrganismNode organismNode = nodeRetriever.getOrganismByScientificName(scName);

                        if (organismNode == null) {

                            organismNode = new OrganismNode(manager.createNode(OrganismNode.NODE_TYPE));

                            organismNode.setCommonName(commName);
                            organismNode.setScientificName(scName);
                            organismNode.setSynonymName(synName);

                            List<Element> organismDbRefElems = organismElem.getChildren(CommonData.DB_REFERENCE_TAG_NAME);
                            if (organismDbRefElems != null) {
                                for (Element dbRefElem : organismDbRefElems) {
                                    String t = dbRefElem.getAttributeValue("type");
                                    if (t.equals("NCBI Taxonomy")) {
                                        organismNode.setNcbiTaxonomyId(dbRefElem.getAttributeValue("id"));
                                        break;
                                    }
                                }
                            }

                            Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
                            List<Element> taxons = lineage.getChildren("taxon");

                            Element firstTaxonElem = taxons.get(0);

                            TaxonNode firstTaxon = nodeRetriever.getTaxonByName(firstTaxonElem.getText());

                            if (firstTaxon == null) {

                                String firstTaxonName = firstTaxonElem.getText();
                                firstTaxon = new TaxonNode(manager.createNode(TaxonNode.NODE_TYPE));
                                firstTaxon.setName(firstTaxonName);

                            }

                            TaxonNode lastTaxon = firstTaxon;

                            for (int i = 1; i < taxons.size(); i++) {

                                String taxonName = taxons.get(i).getText();
                                TaxonNode currentTaxon = nodeRetriever.getTaxonByName(taxonName);

                                if (currentTaxon == null) {

                                    currentTaxon = new TaxonNode(manager.createNode(TaxonNode.NODE_TYPE));
                                    currentTaxon.setName(taxonName);

                                    graph.addEdge(null, lastTaxon.getNode(), currentTaxon.getNode(), TaxonParentRel.NAME);

                                }
                                lastTaxon = currentTaxon;
                            }

                            graph.addEdge(null, lastTaxon.getNode(), organismNode.getNode(), TaxonParentRel.NAME);

                        }


                        //---------------------------------------------------------------------------------------
                        //---------------------------------------------------------------------------------------

                        graph.addEdge(null, currentProteinNode.getNode(), organismNode.getNode(), ProteinOrganismRel.NAME);

                        proteinCounter++;
                        if ((proteinCounter % limitForPrintingOut) == 0) {
                            String countProteinsSt = proteinCounter + " proteins inserted!!";
                            logger.log(Level.INFO, countProteinsSt);
                            
                            manager.getGraph().stopTransaction(TransactionalGraph.Conclusion.SUCCESS);
                        }

                    }
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, ("Exception retrieving protein " + currentAccessionId));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                try {
                    //------closing writers-------
                    enzymeIdsNotFoundBuff.close();

                    // shutdown, makes sure all changes are written to disk
                    manager.shutDown();

                    // closing logger file handler
                    fh.close();

                    //-----------------writing stats file---------------------
                    long elapsedTime = System.nanoTime() - initTime;
                    long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
                    long hours = elapsedSeconds / 3600;
                    long minutes = (elapsedSeconds % 3600) / 60;
                    long seconds = (elapsedSeconds % 3600) % 60;

                    statsBuff.write("Statistics for program ImportUniprot:\nInput file: " + inFile.getName()
                            + "\nThere were " + proteinCounter + " proteins inserted.\n"
                            + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

                    //---closing stats writer---
                    statsBuff.close();


                } catch (IOException ex) {
                    Logger.getLogger(ImportUniprotTitan.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    private static void importProteinFeatures(XMLElement entryXMLElem,
            TitanGraph graph,
            Bio4jManager manager,
            NodeRetriever nodeRetriever,
            ProteinNode currentProteinNode) {


        //--------------------------------features----------------------------------------------------
        List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(CommonData.FEATURE_TAG_NAME);

        for (Element featureElem : featuresList) {

            String featureTypeSt = featureElem.getAttributeValue(CommonData.FEATURE_TYPE_ATTRIBUTE);

            FeatureTypeNode featureTypeNode = nodeRetriever.getFeatureTypeByName(featureTypeSt);

            if (featureTypeNode == null) {
                featureTypeNode = new FeatureTypeNode(manager.createNode(FeatureTypeNode.NODE_TYPE));
                featureTypeNode.setName(featureTypeSt);
            }

            String featureDescSt = featureElem.getAttributeValue(CommonData.FEATURE_DESCRIPTION_ATTRIBUTE);
            if (featureDescSt == null) {
                featureDescSt = "";
            }
            String featureIdSt = featureElem.getAttributeValue(CommonData.FEATURE_ID_ATTRIBUTE);
            if (featureIdSt == null) {
                featureIdSt = "";
            }
            String featureStatusSt = featureElem.getAttributeValue(CommonData.STATUS_ATTRIBUTE);
            if (featureStatusSt == null) {
                featureStatusSt = "";
            }
            String featureEvidenceSt = featureElem.getAttributeValue(CommonData.EVIDENCE_ATTRIBUTE);
            if (featureEvidenceSt == null) {
                featureEvidenceSt = "";
            }

            Element locationElem = featureElem.getChild(CommonData.FEATURE_LOCATION_TAG_NAME);
            Element positionElem = locationElem.getChild(CommonData.FEATURE_POSITION_TAG_NAME);
            String beginFeatureSt;
            String endFeatureSt;
            if (positionElem != null) {
                beginFeatureSt = positionElem.getAttributeValue(CommonData.FEATURE_POSITION_POSITION_ATTRIBUTE);
                endFeatureSt = beginFeatureSt;
            } else {
                beginFeatureSt = locationElem.getChild(CommonData.FEATURE_LOCATION_BEGIN_TAG_NAME).getAttributeValue(CommonData.FEATURE_LOCATION_POSITION_ATTRIBUTE);
                endFeatureSt = locationElem.getChild(CommonData.FEATURE_LOCATION_END_TAG_NAME).getAttributeValue(CommonData.FEATURE_LOCATION_POSITION_ATTRIBUTE);
            }

            if (beginFeatureSt == null) {
                beginFeatureSt = "";
            }
            if (endFeatureSt == null) {
                endFeatureSt = "";
            }

            String originalSt = featureElem.getChildText(CommonData.FEATURE_ORIGINAL_TAG_NAME);
            String variationSt = featureElem.getChildText(CommonData.FEATURE_VARIATION_TAG_NAME);
            if (originalSt == null) {
                originalSt = "";
            }
            if (variationSt == null) {
                variationSt = "";
            }
            String featureRefSt = featureElem.getAttributeValue(CommonData.FEATURE_REF_ATTRIBUTE);
            if (featureRefSt == null) {
                featureRefSt = "";
            }

            BasicFeatureRel basicFeatureRel = null;

            if (featureTypeSt.equals(ActiveSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new ActiveSiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), ActiveSiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(BindingSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new BindingSiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), BindingSiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(CrossLinkFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new CrossLinkFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), CrossLinkFeatureRel.NAME));
            } else if (featureTypeSt.equals(GlycosylationSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new GlycosylationSiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), GlycosylationSiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(InitiatorMethionineFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new InitiatorMethionineFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), InitiatorMethionineFeatureRel.NAME));
            } else if (featureTypeSt.equals(LipidMoietyBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new LipidMoietyBindingRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), LipidMoietyBindingRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(MetalIonBindingSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new MetalIonBindingSiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), MetalIonBindingSiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(ModifiedResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new ModifiedResidueFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), ModifiedResidueFeatureRel.NAME));
            } else if (featureTypeSt.equals(NonStandardAminoAcidFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new NonStandardAminoAcidFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), NonStandardAminoAcidFeatureRel.NAME));
            } else if (featureTypeSt.equals(NonTerminalResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new NonTerminalResidueFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), NonTerminalResidueFeatureRel.NAME));
            } else if (featureTypeSt.equals(PeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new PeptideFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), PeptideFeatureRel.NAME));
            } else if (featureTypeSt.equals(UnsureResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new UnsureResidueFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), UnsureResidueFeatureRel.NAME));
            } else if (featureTypeSt.equals(MutagenesisSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new MutagenesisSiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), MutagenesisSiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(SequenceVariantFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new SequenceVariantFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), SequenceVariantFeatureRel.NAME));
            } else if (featureTypeSt.equals(CalciumBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new CalciumBindingRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), CalciumBindingRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(ChainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new ChainFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), ChainFeatureRel.NAME));
            } else if (featureTypeSt.equals(CoiledCoilRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new CoiledCoilRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), CoiledCoilRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(CompositionallyBiasedRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new CompositionallyBiasedRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), CompositionallyBiasedRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(DisulfideBondFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new DisulfideBondFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), DisulfideBondFeatureRel.NAME));
            } else if (featureTypeSt.equals(DnaBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new DnaBindingRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), DnaBindingRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(DomainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new DomainFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), DomainFeatureRel.NAME));
            } else if (featureTypeSt.equals(HelixFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new HelixFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), HelixFeatureRel.NAME));
            } else if (featureTypeSt.equals(IntramembraneRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new IntramembraneRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), IntramembraneRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(NonConsecutiveResiduesFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new NonConsecutiveResiduesFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), NonConsecutiveResiduesFeatureRel.NAME));
            } else if (featureTypeSt.equals(NucleotidePhosphateBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new NucleotidePhosphateBindingRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), NucleotidePhosphateBindingRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(PropeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new PropeptideFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), PropeptideFeatureRel.NAME));
            } else if (featureTypeSt.equals(RegionOfInterestFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new RegionOfInterestFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), RegionOfInterestFeatureRel.NAME));
            } else if (featureTypeSt.equals(RepeatFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new RepeatFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), RepeatFeatureRel.NAME));
            } else if (featureTypeSt.equals(ShortSequenceMotifFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new ShortSequenceMotifFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), ShortSequenceMotifFeatureRel.NAME));
            } else if (featureTypeSt.equals(SignalPeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new SignalPeptideFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), SignalPeptideFeatureRel.NAME));
            } else if (featureTypeSt.equals(SpliceVariantFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new SpliceVariantFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), SpliceVariantFeatureRel.NAME));
            } else if (featureTypeSt.equals(StrandFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new StrandFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), StrandFeatureRel.NAME));
            } else if (featureTypeSt.equals(TopologicalDomainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new TopologicalDomainFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), TopologicalDomainFeatureRel.NAME));
            } else if (featureTypeSt.equals(TransitPeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new TransitPeptideFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), TransitPeptideFeatureRel.NAME));
            } else if (featureTypeSt.equals(TransmembraneRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new TransmembraneRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), TransmembraneRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(ZincFingerRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new ZincFingerRegionFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), ZincFingerRegionFeatureRel.NAME));
            } else if (featureTypeSt.equals(SiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new SiteFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), SiteFeatureRel.NAME));
            } else if (featureTypeSt.equals(TurnFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicFeatureRel = new TurnFeatureRel(graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), TurnFeatureRel.NAME));
            }

            basicFeatureRel.setDescription(featureDescSt);
            basicFeatureRel.setId(featureIdSt);
            basicFeatureRel.setEvidence(featureEvidenceSt);
            basicFeatureRel.setStatus(featureStatusSt);
            basicFeatureRel.setBegin(beginFeatureSt);
            basicFeatureRel.setEnd(endFeatureSt);
            basicFeatureRel.setOriginal(originalSt);
            basicFeatureRel.setVariation(variationSt);
            basicFeatureRel.setRef(featureRefSt);

            graph.addEdge(null, currentProteinNode.getNode(), featureTypeNode.getNode(), SequenceConflictFeatureRel.NAME);

        }

    }

    private static void importProteinComments(XMLElement entryXMLElem,
            TitanGraph graph,
            Bio4jManager manager,
            NodeRetriever nodeRetriever,
            ProteinNode currentProteinNode,
            String proteinSequence,
            UniprotDataXML uniprotDataXML) {

        List<Element> comments = entryXMLElem.asJDomElement().getChildren(CommonData.COMMENT_TAG_NAME);

        for (Element commentElem : comments) {

            String commentTypeSt = commentElem.getAttributeValue(CommonData.COMMENT_TYPE_ATTRIBUTE);

            Element textElem = commentElem.getChild("text");
            String commentTextSt = "";
            String commentStatusSt = "";
            String commentEvidenceSt = "";
            if (textElem != null) {
                commentTextSt = textElem.getText();
                commentStatusSt = textElem.getAttributeValue("status");
                if (commentStatusSt == null) {
                    commentStatusSt = "";
                }
                commentEvidenceSt = textElem.getAttributeValue("evidence");
                if (commentEvidenceSt == null) {
                    commentEvidenceSt = "";
                }
            }

            //-----------------COMMENT TYPE NODE RETRIEVING/CREATION---------------------- 
            CommentTypeNode commentTypeNode = nodeRetriever.getCommentTypeByName(commentTypeSt);

            if (commentTypeNode == null) {
                commentTypeNode = new CommentTypeNode(manager.createNode(CommentTypeNode.NODE_TYPE));
                commentTypeNode.setName(commentTypeSt);
            }

            BasicCommentRel basicCommentRel = null;
            boolean updateCommentProps = true;

            //-----toxic dose----------------
            if (commentTypeSt.equals(ToxicDoseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new ToxicDoseCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), ToxicDoseCommentRel.NAME));
            } //-----caution---------
            else if (commentTypeSt.equals(CautionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new CofactorCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), CofactorCommentRel.NAME));
            } //-----cofactor---------
            else if (commentTypeSt.equals(CofactorCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new CofactorCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), CofactorCommentRel.NAME));
            } //-----disease---------
            else if (commentTypeSt.equals(DiseaseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new DiseaseCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), DiseaseCommentRel.NAME));
            } //-----online information---------
            else if (commentTypeSt.equals(OnlineInformationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                String nameSt = commentElem.getAttributeValue("name");
                if (nameSt == null) {
                    nameSt = "";
                }
                String linkSt = "";
                Element linkElem = commentElem.getChild("link");
                if (linkElem != null) {
                    String uriSt = linkElem.getAttributeValue("uri");
                    if (uriSt != null) {
                        linkSt = uriSt;
                    }
                }

                OnlineInformationCommentRel onlineInformationCommentRel = new OnlineInformationCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), OnlineInformationCommentRel.NAME));
                onlineInformationCommentRel.setName(nameSt);
                onlineInformationCommentRel.setLink(linkSt);
                basicCommentRel = onlineInformationCommentRel;

            } //-----tissue specificity---------
            else if (commentTypeSt.equals(TissueSpecificityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new TissueSpecificityCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), TissueSpecificityCommentRel.NAME));
            } //----------function----------------
            else if (commentTypeSt.equals(FunctionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new FunctionCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), FunctionCommentRel.NAME));
            } //----------biotechnology----------------
            else if (commentTypeSt.equals(BiotechnologyCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new BiotechnologyCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), BiotechnologyCommentRel.NAME));
            } //----------subunit----------------
            else if (commentTypeSt.equals(SubunitCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new SubunitCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), SubunitCommentRel.NAME));
            } //----------polymorphism----------------
            else if (commentTypeSt.equals(PolymorphismCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new PolymorphismCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), PolymorphismCommentRel.NAME));
            } //----------domain----------------
            else if (commentTypeSt.equals(DomainCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new DomainCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), DomainCommentRel.NAME));
            } //----------post transactional modification----------------
            else if (commentTypeSt.equals(PostTranslationalModificationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new PostTranslationalModificationCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), PostTranslationalModificationCommentRel.NAME));
            } //----------catalytic activity----------------
            else if (commentTypeSt.equals(CatalyticActivityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new CatalyticActivityCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), CatalyticActivityCommentRel.NAME));
            } //----------disruption phenotype----------------
            else if (commentTypeSt.equals(DisruptionPhenotypeCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new DisruptionPhenotypeCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), DisruptionPhenotypeCommentRel.NAME));
            } //----------biophysicochemical properties----------------
            else if (commentTypeSt.equals(BioPhysicoChemicalPropertiesCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                String phDependenceSt = commentElem.getChildText("phDependence");
                String temperatureDependenceSt = commentElem.getChildText("temperatureDependence");
                if (phDependenceSt == null) {
                    phDependenceSt = "";
                }
                if (temperatureDependenceSt == null) {
                    temperatureDependenceSt = "";
                }
                String absorptionMaxSt = "";
                String absorptionTextSt = "";
                Element absorptionElem = commentElem.getChild("absorption");
                if (absorptionElem != null) {
                    absorptionMaxSt = absorptionElem.getChildText("max");
                    absorptionTextSt = absorptionElem.getChildText("text");
                    if (absorptionMaxSt == null) {
                        absorptionMaxSt = "";
                    }
                    if (absorptionTextSt == null) {
                        absorptionTextSt = "";
                    }
                }
                String kineticsSt = "";
                Element kineticsElem = commentElem.getChild("kinetics");
                if (kineticsElem != null) {
                    kineticsSt = new XMLElement(kineticsElem).toString();
                }
                String redoxPotentialSt = "";
                String redoxPotentialEvidenceSt = "";
                Element redoxPotentialElem = commentElem.getChild("redoxPotential");
                if (redoxPotentialElem != null) {
                    redoxPotentialSt = redoxPotentialElem.getText();
                    redoxPotentialEvidenceSt = redoxPotentialElem.getAttributeValue("evidence");
                    if (redoxPotentialSt == null) {
                        redoxPotentialSt = "";
                    }
                    if (redoxPotentialEvidenceSt == null) {
                        redoxPotentialEvidenceSt = "";
                    }
                }

                BioPhysicoChemicalPropertiesCommentRel bioPhysicoChemicalPropertiesCommentRel = new BioPhysicoChemicalPropertiesCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), BioPhysicoChemicalPropertiesCommentRel.NAME));
                bioPhysicoChemicalPropertiesCommentRel.setTemperatureDependence(temperatureDependenceSt);
                bioPhysicoChemicalPropertiesCommentRel.setPhDependence(phDependenceSt);
                bioPhysicoChemicalPropertiesCommentRel.setKineticsXml(kineticsSt);
                bioPhysicoChemicalPropertiesCommentRel.setAbsorptionMax(absorptionMaxSt);
                bioPhysicoChemicalPropertiesCommentRel.setAbsorptionText(absorptionTextSt);
                bioPhysicoChemicalPropertiesCommentRel.setRedoxPotentialEvidence(redoxPotentialEvidenceSt);
                bioPhysicoChemicalPropertiesCommentRel.setRedoxPotential(redoxPotentialSt);
                basicCommentRel = bioPhysicoChemicalPropertiesCommentRel;

            } //----------allergen----------------
            else if (commentTypeSt.equals(AllergenCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new AllergenCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), AllergenCommentRel.NAME));
            } //----------pathway----------------
            else if (commentTypeSt.equals(PathwayCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new PathwayCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), PathwayCommentRel.NAME));
            } //----------induction----------------
            else if (commentTypeSt.equals(InductionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new InductionCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), InductionCommentRel.NAME));
            } //----- subcellular location---------
            else if (commentTypeSt.equals(ProteinSubcellularLocationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                if (uniprotDataXML.getSubcellularLocations()) {
                    List<Element> subcLocations = commentElem.getChildren(CommonData.SUBCELLULAR_LOCATION_TAG_NAME);

                    for (Element subcLocation : subcLocations) {

                        List<Element> locations = subcLocation.getChildren(CommonData.LOCATION_TAG_NAME);
                        Element firstLocation = locations.get(0);

                        SubcellularLocationNode firstLocationNode = nodeRetriever.getSubcellularLocationByName(firstLocation.getTextTrim());

                        SubcellularLocationNode lastLocationNode = firstLocationNode;

                        if (lastLocationNode == null) {
                            lastLocationNode = new SubcellularLocationNode(manager.createNode(SubcellularLocationNode.NODE_TYPE));
                            lastLocationNode.setName(firstLocation.getTextTrim());
                        }

                        for (int i = 1; i < locations.size(); i++) {

                            SubcellularLocationNode tempLocationNode = nodeRetriever.getSubcellularLocationByName(locations.get(i).getTextTrim());
                            if (tempLocationNode == null) {
                                tempLocationNode = new SubcellularLocationNode(manager.createNode(SubcellularLocationNode.NODE_TYPE));
                                tempLocationNode.setName(locations.get(i).getTextTrim());
                            }

                            graph.addEdge(null, tempLocationNode.getNode(), lastLocationNode.getNode(), SubcellularLocationParentRel.NAME);
                            lastLocationNode = tempLocationNode;
                        }
                        Element lastLocation = locations.get(locations.size() - 1);
                        String evidenceSt = lastLocation.getAttributeValue(CommonData.EVIDENCE_ATTRIBUTE);
                        String statusSt = lastLocation.getAttributeValue(CommonData.STATUS_ATTRIBUTE);
                        String topologyStatusSt = "";
                        String topologySt = "";
                        Element topologyElem = subcLocation.getChild("topology");
                        if (topologyElem != null) {
                            topologySt = topologyElem.getText();
                            topologyStatusSt = topologyElem.getAttributeValue("status");
                        }
                        if (topologyStatusSt == null) {
                            topologyStatusSt = "";
                        }
                        if (topologySt == null) {
                            topologySt = "";
                        }
                        if (evidenceSt == null) {
                            evidenceSt = "";
                        }
                        if (statusSt == null) {
                            statusSt = "";
                        }
                        ProteinSubcellularLocationRel proteinSubcellularLocationRel = new ProteinSubcellularLocationRel(graph.addEdge(null, currentProteinNode.getNode(), lastLocationNode.getNode(), ProteinSubcellularLocationRel.NAME));
                        proteinSubcellularLocationRel.setEvidence(evidenceSt);
                        proteinSubcellularLocationRel.setStatus(statusSt);
                        proteinSubcellularLocationRel.setTopology(topologySt);
                        proteinSubcellularLocationRel.setTopologyStatus(topologyStatusSt);

                    }
                }

                updateCommentProps = false;

            } //----- alternative products---------
            else if (commentTypeSt.equals(CommonData.COMMENT_ALTERNATIVE_PRODUCTS_TYPE)) {

                if (uniprotDataXML.getIsoforms()) {
                    List<Element> eventList = commentElem.getChildren("event");
                    List<Element> isoformList = commentElem.getChildren("isoform");

                    for (Element isoformElem : isoformList) {
                        String isoformIdSt = isoformElem.getChildText("id");
                        String isoformNoteSt = isoformElem.getChildText("note");
                        String isoformNameSt = isoformElem.getChildText("name");
                        String isoformSeqSt = "";
                        Element isoSeqElem = isoformElem.getChild("sequence");
                        if (isoSeqElem != null) {
                            String isoSeqTypeSt = isoSeqElem.getAttributeValue("type");
                            if (isoSeqTypeSt.equals("displayed")) {
                                isoformSeqSt = proteinSequence;
                            }
                        }
                        if (isoformNoteSt == null) {
                            isoformNoteSt = "";
                        }
                        if (isoformNameSt == null) {
                            isoformNameSt = "";
                        }

                        //--------------------------------------------------------
                        IsoformNode isoformNode = nodeRetriever.getIsoformById(isoformIdSt);
                        if (isoformNode == null) {
                            isoformNode = new IsoformNode(manager.createNode(IsoformNode.NODE_TYPE));
                            isoformNode.setId(isoformIdSt);
                            isoformNode.setNote(isoformNoteSt);
                            isoformNode.setName(isoformNameSt);
                            isoformNode.setSequence(isoformSeqSt);
                        }

                        for (Element eventElem : eventList) {

                            String eventTypeSt = eventElem.getAttributeValue("type");
                            if (eventTypeSt.equals(AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                graph.addEdge(null, isoformNode.getNode(), nodeRetriever.getAlternativeProductInitiationNode().getNode(), IsoformEventGeneratorRel.NAME);
                            } else if (eventTypeSt.equals(AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                graph.addEdge(null, isoformNode.getNode(), nodeRetriever.getAlternativeProductPromoterNode().getNode(), IsoformEventGeneratorRel.NAME);
                            } else if (eventTypeSt.equals(AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                graph.addEdge(null, isoformNode.getNode(), nodeRetriever.getAlternativeProductRibosomalFrameshiftingNode().getNode(), IsoformEventGeneratorRel.NAME);
                            } else if (eventTypeSt.equals(AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                graph.addEdge(null, isoformNode.getNode(), nodeRetriever.getAlternativeProductSplicingNode().getNode(), IsoformEventGeneratorRel.NAME);
                            }
                        }

                        //protein isoform relationship
                        graph.addEdge(null, currentProteinNode.getNode(), isoformNode.getNode(), ProteinIsoformRel.NAME);

                    }
                }

                updateCommentProps = false;

            } //----- sequence caution---------
            else if (commentTypeSt.equals(CommonData.COMMENT_SEQUENCE_CAUTION_TYPE)) {

                Element conflictElem = commentElem.getChild("conflict");
                if (conflictElem != null) {

                    String conflictTypeSt = conflictElem.getAttributeValue("type");
                    String resourceSt = "";
                    String idSt = "";
                    String versionSt = "";

                    ArrayList<String> positionsList = new ArrayList<String>();

                    Element sequenceElem = conflictElem.getChild("sequence");
                    if (sequenceElem != null) {
                        resourceSt = sequenceElem.getAttributeValue("resource");
                        if (resourceSt == null) {
                            resourceSt = "";
                        }
                        idSt = sequenceElem.getAttributeValue("id");
                        if (idSt == null) {
                            idSt = "";
                        }
                        versionSt = sequenceElem.getAttributeValue("version");
                        if (versionSt == null) {
                            versionSt = "";
                        }
                    }

                    Element locationElem = commentElem.getChild("location");
                    if (locationElem != null) {
                        Element positionElem = locationElem.getChild("position");
                        if (positionElem != null) {
                            String tempPos = positionElem.getAttributeValue("position");
                            if (tempPos != null) {
                                positionsList.add(tempPos);
                            }
                        }
                    }


                    BasicProteinSequenceCautionRel basicProteinSequenceCautionRel = null;

                    if (conflictTypeSt.equals(ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinErroneousGeneModelPredictionRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousGeneModelPredictionNode().getNode(), ProteinErroneousGeneModelPredictionRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinErroneousGeneModelPredictionRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousGeneModelPredictionNode().getNode(), ProteinErroneousGeneModelPredictionRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinErroneousInitiationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousInitiationNode().getNode(), ProteinErroneousInitiationRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinErroneousInitiationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousInitiationNode().getNode(), ProteinErroneousInitiationRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinErroneousTranslationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousTranslationNode().getNode(), ProteinErroneousTranslationRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinErroneousTranslationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousTranslationNode().getNode(), ProteinErroneousTranslationRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    } else if (conflictTypeSt.equals(ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinErroneousTerminationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousTerminationNode().getNode(), ProteinErroneousTerminationRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinErroneousTerminationRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionErroneousTerminationNode().getNode(), ProteinErroneousTerminationRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    } else if (conflictTypeSt.equals(ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinFrameshiftRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionFrameshiftNode().getNode(), ProteinFrameshiftRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinFrameshiftRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionFrameshiftNode().getNode(), ProteinFrameshiftRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    } else if (conflictTypeSt.equals(ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                        if (positionsList.size() > 0) {
                            for (String tempPosition : positionsList) {
                                basicProteinSequenceCautionRel = new ProteinMiscellaneousDiscrepancyRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionMiscellaneousDiscrepancyNode().getNode(), ProteinMiscellaneousDiscrepancyRel.NAME));
                                basicProteinSequenceCautionRel.setPosition(tempPosition);
                                basicProteinSequenceCautionRel.setResource(resourceSt);
                                basicProteinSequenceCautionRel.setId(idSt);
                                basicProteinSequenceCautionRel.setVersion(versionSt);
                                basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                                basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                                basicProteinSequenceCautionRel.setText(commentTextSt);
                            }
                        } else {
                            basicProteinSequenceCautionRel = new ProteinMiscellaneousDiscrepancyRel(graph.addEdge(null, currentProteinNode.getNode(), nodeRetriever.getSequenceCautionMiscellaneousDiscrepancyNode().getNode(), ProteinMiscellaneousDiscrepancyRel.NAME));
                            basicProteinSequenceCautionRel.setPosition("");
                        }

                    }

                    basicProteinSequenceCautionRel.setResource(resourceSt);
                    basicProteinSequenceCautionRel.setId(idSt);
                    basicProteinSequenceCautionRel.setVersion(versionSt);
                    basicProteinSequenceCautionRel.setEvidence(commentEvidenceSt);
                    basicProteinSequenceCautionRel.setStatus(commentStatusSt);
                    basicProteinSequenceCautionRel.setText(commentTextSt);

                }

                updateCommentProps = false;

            } //----------developmental stage----------------
            else if (commentTypeSt.equals(DevelopmentalStageCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new DevelopmentalStageCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), DevelopmentalStageCommentRel.NAME));
            } //----------miscellaneous----------------
            else if (commentTypeSt.equals(MiscellaneousCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new MiscellaneousCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), MiscellaneousCommentRel.NAME));
            } //----------similarity----------------
            else if (commentTypeSt.equals(SimilarityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new SimilarityCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), SimilarityCommentRel.NAME));
            } //----------RNA editing----------------
            else if (commentTypeSt.equals(RnaEditingCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                List<Element> locationsList = commentElem.getChildren("location");
                for (Element tempLoc : locationsList) {
                    String positionSt = tempLoc.getChild("position").getAttributeValue("position");
                    RnaEditingCommentRel rnaEditingCommentRel = new RnaEditingCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), RnaEditingCommentRel.NAME));
                    rnaEditingCommentRel.setPosition(positionSt);
                    rnaEditingCommentRel.setStatus(commentStatusSt);
                    rnaEditingCommentRel.setEvidence(commentEvidenceSt);
                    rnaEditingCommentRel.setText(commentTextSt);
                }

                updateCommentProps = false;

            } //----------pharmaceutical----------------
            else if (commentTypeSt.equals(PharmaceuticalCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new PharmaceuticalCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), PharmaceuticalCommentRel.NAME));
            } //----------enzyme regulation----------------
            else if (commentTypeSt.equals(EnzymeRegulationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                basicCommentRel = new EnzymeRegulationCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), EnzymeRegulationCommentRel.NAME));
            } //----------mass spectrometry----------------
            else if (commentTypeSt.equals(MassSpectrometryCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                String methodSt = commentElem.getAttributeValue("method");
                String massSt = commentElem.getAttributeValue("mass");
                if (methodSt == null) {
                    methodSt = "";
                }
                if (massSt == null) {
                    massSt = "";
                }
                String beginSt = "";
                String endSt = "";
                Element locationElem = commentElem.getChild("location");
                if (locationElem != null) {
                    Element beginElem = commentElem.getChild("begin");
                    Element endElem = commentElem.getChild("end");
                    if (beginElem != null) {
                        beginSt = beginElem.getAttributeValue("position");
                    }
                    if (endElem != null) {
                        endSt = endElem.getAttributeValue("position");
                    }
                }

                MassSpectrometryCommentRel massSpectrometryCommentRel = new MassSpectrometryCommentRel(graph.addEdge(null, currentProteinNode.getNode(), commentTypeNode.getNode(), MassSpectrometryCommentRel.NAME));
                massSpectrometryCommentRel.setMethod(methodSt);
                massSpectrometryCommentRel.setMass(massSt);
                massSpectrometryCommentRel.setBegin(beginSt);
                massSpectrometryCommentRel.setEnd(endSt);
                basicCommentRel = massSpectrometryCommentRel;
            }

            if (updateCommentProps) {
                basicCommentRel.setText(commentTextSt);
                basicCommentRel.setStatus(commentStatusSt);
                basicCommentRel.setEvidence(commentEvidenceSt);
            }

        }


    }

    private static String getProteinFullName(Element proteinElement) {
        if (proteinElement == null) {
            return "";
        } else {
            Element recElem = proteinElement.getChild(CommonData.PROTEIN_RECOMMENDED_NAME_TAG_NAME);
            if (recElem == null) {
                return "";
            } else {
                return recElem.getChildText(CommonData.PROTEIN_FULL_NAME_TAG_NAME);
            }
        }
    }

    private static String getProteinShortName(Element proteinElement) {
        if (proteinElement == null) {
            return "";
        } else {
            Element recElem = proteinElement.getChild(CommonData.PROTEIN_RECOMMENDED_NAME_TAG_NAME);
            if (recElem == null) {
                return "";
            } else {
                return recElem.getChildText(CommonData.PROTEIN_SHORT_NAME_TAG_NAME);
            }
        }
    }

    private static void importProteinCitations(XMLElement entryXMLElem,
            TitanGraph graph,
            Bio4jManager manager,
            NodeRetriever nodeRetriever,
            ProteinNode currentProteinNode,
            UniprotDataXML uniprotDataXML) {


        List<Element> referenceList = entryXMLElem.asJDomElement().getChildren(CommonData.REFERENCE_TAG_NAME);

        for (Element reference : referenceList) {
            List<Element> citationsList = reference.getChildren(CommonData.CITATION_TAG_NAME);
            for (Element citation : citationsList) {

                String citationType = citation.getAttributeValue(CommonData.DB_REFERENCE_TYPE_ATTRIBUTE);

                List<PersonNode> authorsPersonNodes = new ArrayList<PersonNode>();
                List<ConsortiumNode> authorsConsortiumNodes = new ArrayList<ConsortiumNode>();

                List<Element> authorPersonElems = citation.getChild("authorList").getChildren("person");
                List<Element> authorConsortiumElems = citation.getChild("authorList").getChildren("consortium");

                for (Element person : authorPersonElems) {

                    List<PersonNode> personList = nodeRetriever.getPeopleByName(person.getAttributeValue("name"));
                    PersonNode personNode = null;
                    if (!personList.isEmpty()) {
                        personNode = personList.get(0);
                    }
                    if (personNode == null) {
                        personNode = new PersonNode(manager.createNode(PersonNode.NODE_TYPE));
                        personNode.setName(person.getAttributeValue("name"));
                    }
                    authorsPersonNodes.add(personNode);
                }

                for (Element consortium : authorConsortiumElems) {
                    long consortiumId = -1;

                    ConsortiumNode consortiumNode = nodeRetriever.getConsortiumByName(consortium.getAttributeValue("name"));

                    if (consortiumNode == null) {
                        consortiumNode = new ConsortiumNode(manager.createNode(ConsortiumNode.NODE_TYPE));
                        consortiumNode.setName(consortium.getAttributeValue("name"));
                    }
                    authorsConsortiumNodes.add(consortiumNode);
                }

                //----------------------------------------------------------------------------
                //-----------------------------THESIS-----------------------------------------
                if (citationType.equals(ThesisNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getThesis()) {
                        String dateSt = citation.getAttributeValue("date");
                        String titleSt = citation.getChildText("title");
                        if (dateSt == null) {
                            dateSt = "";
                        }
                        if (titleSt == null) {
                            titleSt = "";
                        }

                        List<ThesisNode> thesisList = nodeRetriever.getThesisByTitle(titleSt);
                        ThesisNode thesisNode;

                        if (!thesisList.isEmpty()) {
                            thesisNode = thesisList.get(0);
                        } else {

                            thesisNode = new ThesisNode(manager.createNode(ThesisNode.NODE_TYPE));
                            thesisNode.setDate(dateSt);
                            thesisNode.setTitle(titleSt);

                            //---authors association-----
                            for (PersonNode personNode : authorsPersonNodes) {
                                graph.addEdge(null, thesisNode.getNode(), personNode.getNode(), ThesisAuthorRel.NAME);
                            }

                            //-----------institute & country-----------------------------
                            String instituteSt = citation.getAttributeValue("institute");
                            String countrySt = citation.getAttributeValue("country");

                            if (instituteSt != null) {

                                InstituteNode instituteNode = nodeRetriever.getInstituteByName(instituteSt);

                                if (instituteNode == null) {
                                    instituteNode = new InstituteNode(manager.createNode(InstituteNode.NODE_TYPE));
                                    instituteNode.setName(instituteSt);
                                }
                                if (countrySt != null) {

                                    CountryNode countryNode = nodeRetriever.getCountryNodeByName(countrySt);

                                    if (countryNode == null) {
                                        countryNode = new CountryNode(manager.createNode(CountryNode.NODE_TYPE));
                                        countryNode.setName(countrySt);
                                    }
                                    graph.addEdge(null, instituteNode.getNode(), countryNode.getNode(), InstituteCountryRel.NAME);
                                }
                                graph.addEdge(null, thesisNode.getNode(), instituteNode.getNode(), ThesisInstituteRel.NAME);
                            }
                        }

                        //--protein citation relationship
                        graph.addEdge(null, thesisNode.getNode(), currentProteinNode.getNode(), ThesisProteinCitationRel.NAME);

                    }

                    //----------------------------------------------------------------------------
                    //-----------------------------PATENT-----------------------------------------
                } else if (citationType.equals(PatentNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getPatents()) {
                        String numberSt = citation.getAttributeValue("number");
                        String dateSt = citation.getAttributeValue("date");
                        String titleSt = citation.getChildText("title");
                        if (dateSt == null) {
                            dateSt = "";
                        }
                        if (titleSt == null) {
                            titleSt = "";
                        }
                        if (numberSt == null) {
                            numberSt = "";
                        }

                        if (!numberSt.equals("")) {

                            PatentNode patentNode = nodeRetriever.getPatentByNumber(numberSt);

                            if (patentNode == null) {

                                patentNode = new PatentNode(manager.createNode(PatentNode.NODE_TYPE));
                                patentNode.setNumber(numberSt);
                                patentNode.setDate(dateSt);
                                patentNode.setTitle(titleSt);

                                //---authors association-----
                                for (PersonNode personNode : authorsPersonNodes) {
                                    graph.addEdge(null, patentNode.getNode(), personNode.getNode(), PatentAuthorRel.NAME);
                                }
                            }

                            //--protein citation relationship
                            graph.addEdge(null, patentNode.getNode(), currentProteinNode.getNode(), PatentProteinCitationRel.NAME);
                        }
                    }

                    //----------------------------------------------------------------------------
                    //-----------------------------SUBMISSION-----------------------------------------
                } else if (citationType.equals(SubmissionNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getSubmissions()) {
                        String dateSt = citation.getAttributeValue("date");
                        String titleSt = citation.getChildText("title");
                        String dbSt = citation.getAttributeValue("db");
                        if (dateSt == null) {
                            dateSt = "";
                        }
                        if (titleSt == null) {
                            titleSt = "";
                        }

                        SubmissionNode submissionNode = nodeRetriever.getSubmissionByTitle(titleSt);

                        if (submissionNode == null || titleSt.equals("")) {
                            submissionNode = new SubmissionNode(manager.createNode(SubmissionNode.NODE_TYPE));
                            submissionNode.setDate(dateSt);
                            submissionNode.setTitle(titleSt);
                        }

                        //---authors association-----
                        for (PersonNode personNode : authorsPersonNodes) {
                            graph.addEdge(null, submissionNode.getNode(), personNode.getNode(), SubmissionAuthorRel.NAME);
                        }
                        //---authors consortium association-----
                        for (ConsortiumNode consortiumNode : authorsConsortiumNodes) {
                            graph.addEdge(null, submissionNode.getNode(), consortiumNode.getNode(), SubmissionAuthorRel.NAME);
                        }

                        if (dbSt != null) {
                            DBNode dBNode = nodeRetriever.getDBByName(dbSt);

                            if (dBNode == null) {
                                dBNode = new DBNode(manager.createNode(DBNode.NODE_TYPE));
                                dBNode.setName(dbSt);
                            }
                            //-----submission db relationship-----
                            graph.addEdge(null, submissionNode.getNode(), dBNode.getNode(), SubmissionDbRel.NAME);
                        }

                        //--protein citation relationship
                        graph.addEdge(null, submissionNode.getNode(), currentProteinNode.getNode(), SubmissionProteinCitationRel.NAME);

                    }

                    //----------------------------------------------------------------------------
                    //-----------------------------BOOK-----------------------------------------
                } else if (citationType.equals(BookNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getBooks()) {
                        String nameSt = citation.getAttributeValue("name");
                        String dateSt = citation.getAttributeValue("date");
                        String titleSt = citation.getChildText("title");
                        String publisherSt = citation.getAttributeValue("publisher");
                        String firstSt = citation.getAttributeValue("first");
                        String lastSt = citation.getAttributeValue("last");
                        String citySt = citation.getAttributeValue("city");
                        String volumeSt = citation.getAttributeValue("volume");
                        if (nameSt == null) {
                            nameSt = "";
                        }
                        if (dateSt == null) {
                            dateSt = "";
                        }
                        if (titleSt == null) {
                            titleSt = "";
                        }
                        if (publisherSt == null) {
                            publisherSt = "";
                        }
                        if (firstSt == null) {
                            firstSt = "";
                        }
                        if (lastSt == null) {
                            lastSt = "";
                        }
                        if (citySt == null) {
                            citySt = "";
                        }
                        if (volumeSt == null) {
                            volumeSt = "";
                        }

                        List<BookNode> bookNodeList = nodeRetriever.getBooksByName(nameSt);
                        BookNode bookNode;

                        if (bookNodeList.isEmpty()) {

                            bookNode = new BookNode(manager.createNode(BookNode.NODE_TYPE));
                            bookNode.setName(nameSt);
                            bookNode.setDate(dateSt);

                            //---authors association-----
                            for (PersonNode personNode : authorsPersonNodes) {
                                graph.addEdge(null, bookNode.getNode(), personNode.getNode(), BookAuthorRel.NAME);
                            }

                            //---editor association-----
                            Element editorListElem = citation.getChild("editorList");
                            if (editorListElem != null) {
                                List<Element> editorsElems = editorListElem.getChildren("person");
                                for (Element person : editorsElems) {

                                    List<PersonNode> editorNodes = nodeRetriever.getPeopleByName(person.getAttributeValue("name"));
                                    PersonNode editorNode;
                                    if (editorNodes.isEmpty()) {
                                        editorNode = new PersonNode(manager.createNode(PersonNode.NODE_TYPE));
                                        editorNode.setName(person.getAttributeValue("name"));
                                    } else {
                                        editorNode = editorNodes.get(0);
                                    }
                                    //editor association
                                    graph.addEdge(null, bookNode.getNode(), editorNode.getNode(), BookEditorRel.NAME);
                                }
                            }


                            //----publisher--
                            if (!publisherSt.equals("")) {

                                PublisherNode publisherNode = nodeRetriever.getPublisherByName(publisherSt);

                                if (publisherNode == null) {
                                    publisherNode = new PublisherNode(manager.createNode(PublisherNode.NODE_TYPE));
                                    publisherNode.setName(publisherSt);
                                }
                                graph.addEdge(null, bookNode.getNode(), publisherNode.getNode(), BookPublisherRel.NAME);
                            }

                            //-----city-----
                            if (!citySt.equals("")) {
                                CityNode cityNode = nodeRetriever.getCityNodeByName(citySt);
                                if (cityNode == null) {
                                    cityNode = new CityNode(manager.createNode(CityNode.NODE_TYPE));
                                    cityNode.setName(citySt);
                                }
                                graph.addEdge(null, bookNode.getNode(), cityNode.getNode(), BookCityRel.NAME);
                            }
                        } else {
                            bookNode = bookNodeList.get(0);
                        }

                        BookProteinCitationRel bookProteinCitationRel = new BookProteinCitationRel(graph.addEdge(null, bookNode.getNode(), currentProteinNode.getNode(), BookProteinCitationRel.NAME));
                        bookProteinCitationRel.setFirst(firstSt);
                        bookProteinCitationRel.setLast(lastSt);
                        bookProteinCitationRel.setVolume(volumeSt);
                        bookProteinCitationRel.setTitle(titleSt);
                    }

                    //----------------------------------------------------------------------------
                    //-----------------------------ONLINE ARTICLE-----------------------------------------
                } else if (citationType.equals(OnlineArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getOnlineArticles()) {
                        String locatorSt = citation.getChildText("locator");
                        String nameSt = citation.getAttributeValue("name");
                        String titleSt = citation.getChildText("title");

                        if (titleSt == null) {
                            titleSt = "";
                        }
                        if (nameSt == null) {
                            nameSt = "";
                        }
                        if (locatorSt == null) {
                            locatorSt = "";
                        }

                        List<OnlineArticleNode> onlineArticleNodeList = nodeRetriever.getOnlineArticlesByTitle(titleSt);
                        OnlineArticleNode onlineArticleNode;

                        if (onlineArticleNodeList.isEmpty() || titleSt.equals("")) {

                            onlineArticleNode = new OnlineArticleNode(manager.createNode(OnlineArticleNode.NODE_TYPE));
                            onlineArticleNode.setTitle(titleSt);

                            //---authors person association-----
                            for (PersonNode personNode : authorsPersonNodes) {
                                graph.addEdge(null, onlineArticleNode.getNode(), personNode.getNode(), OnlineArticleAuthorRel.NAME);
                            }
                            //---authors consortium association-----
                            for (ConsortiumNode consortiumNode : authorsConsortiumNodes) {
                                graph.addEdge(null, onlineArticleNode.getNode(), consortiumNode.getNode(), OnlineArticleAuthorRel.NAME);
                            }

                            //------online journal-----------
                            if (!nameSt.equals("")) {

                                OnlineJournalNode onlineJournalNode = nodeRetriever.getOnlineJournalByName(nameSt);

                                if (onlineJournalNode == null) {
                                    onlineJournalNode = new OnlineJournalNode(manager.createNode(OnlineJournalNode.NODE_TYPE));
                                    onlineJournalNode.setName(nameSt);
                                }

                                OnlineArticleJournalRel onlineArticleJournalRel = new OnlineArticleJournalRel(graph.addEdge(null, onlineArticleNode.getNode(), onlineJournalNode.getNode(), OnlineArticleJournalRel.NAME));
                                onlineArticleJournalRel.setLocator(locatorSt);
                            }
                            //----------------------------
                        } else {
                            onlineArticleNode = onlineArticleNodeList.get(0);
                        }

                        //protein citation
                        graph.addEdge(null, onlineArticleNode.getNode(), currentProteinNode.getNode(), OnlineArticleProteinCitationRel.NAME);
                    }

                    //----------------------------------------------------------------------------
                    //-----------------------------ARTICLE-----------------------------------------
                } else if (citationType.equals(ArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getArticles()) {
                        String journalNameSt = citation.getAttributeValue("name");
                        String dateSt = citation.getAttributeValue("date");
                        String titleSt = citation.getChildText("title");
                        String firstSt = citation.getAttributeValue("first");
                        String lastSt = citation.getAttributeValue("last");
                        String volumeSt = citation.getAttributeValue("volume");
                        String doiSt = "";
                        String medlineSt = "";
                        String pubmedSt = "";

                        if (journalNameSt == null) {
                            journalNameSt = "";
                        }
                        if (dateSt == null) {
                            dateSt = "";
                        }
                        if (firstSt == null) {
                            firstSt = "";
                        }
                        if (lastSt == null) {
                            lastSt = "";
                        }
                        if (volumeSt == null) {
                            volumeSt = "";
                        }
                        if (titleSt == null) {
                            titleSt = "";
                        }

                        List<Element> dbReferences = citation.getChildren("dbReference");
                        for (Element tempDbRef : dbReferences) {
                            if (tempDbRef.getAttributeValue("type").equals("DOI")) {
                                doiSt = tempDbRef.getAttributeValue("id");
                            } else if (tempDbRef.getAttributeValue("type").equals("MEDLINE")) {
                                medlineSt = tempDbRef.getAttributeValue("id");
                            } else if (tempDbRef.getAttributeValue("type").equals("PubMed")) {
                                pubmedSt = tempDbRef.getAttributeValue("id");
                            }
                        }


                        List<ArticleNode> articleNodeList = nodeRetriever.getArticlesByTitle(titleSt);
                        ArticleNode articleNode;

                        if (articleNodeList.isEmpty() || titleSt.equals("")) {

                            articleNode = new ArticleNode(manager.createNode(ArticleNode.NODE_TYPE));
                            articleNode.setTitle(titleSt);
                            articleNode.setDoiId(doiSt);
                            articleNode.setMedlineId(medlineSt);
                            articleNode.setPubmedId(pubmedSt);

                            //---authors person association-----
                            for (PersonNode personNode : authorsPersonNodes) {
                                graph.addEdge(null, articleNode.getNode(), personNode.getNode(), ArticleAuthorRel.NAME);
                            }
                            //---authors consortium association-----
                            for (ConsortiumNode consortiumNode : authorsConsortiumNodes) {
                                graph.addEdge(null, articleNode.getNode(), consortiumNode.getNode(), ArticleAuthorRel.NAME);
                            }

                            //------journal-----------
                            if (!journalNameSt.equals("")) {

                                JournalNode journalNode = nodeRetriever.getJournalByName(journalNameSt);

                                if (journalNode == null) {
                                    journalNode = new JournalNode(manager.createNode(JournalNode.NODE_TYPE));
                                    journalNode.setName(journalNameSt);
                                }

                                ArticleJournalRel articleJournalRel = new ArticleJournalRel(graph.addEdge(null, articleNode.getNode(), journalNode.getNode(), ArticleJournalRel.NAME));

                                articleJournalRel.setDate(dateSt);
                                articleJournalRel.setFirst(firstSt);
                                articleJournalRel.setLast(lastSt);
                                articleJournalRel.setVolume(volumeSt);
                            }
                            //----------------------------
                        } else {
                            articleNode = articleNodeList.get(0);
                        }
                        //protein citation
                        graph.addEdge(null, articleNode.getNode(), currentProteinNode.getNode(), ArticleProteinCitationRel.NAME);

                    }

                    //----------------------------------------------------------------------------
                    //----------------------UNPUBLISHED OBSERVATIONS-----------------------------------------
                } else if (citationType.equals(UnpublishedObservationNode.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {

                    if (uniprotDataXML.getUnpublishedObservations()) {
                        String dateSt = citation.getAttributeValue("date");
                        if (dateSt == null) {
                            dateSt = "";
                        }

                        UnpublishedObservationNode unpublishedObservationNode = new UnpublishedObservationNode(manager.createNode(UnpublishedObservationNode.NODE_TYPE));
                        unpublishedObservationNode.setDate(dateSt);

                        //---authors person association-----
                        for (PersonNode personNode : authorsPersonNodes) {
                            graph.addEdge(null, unpublishedObservationNode.getNode(), personNode.getNode(), UnpublishedObservationAuthorRel.NAME);
                        }

                        graph.addEdge(null, unpublishedObservationNode.getNode(), currentProteinNode.getNode(), UnpublishedObservationProteinCitationRel.NAME);
                    }

                }
            }
        }


    }

    private static String[] convertToStringArray(List<String> list) {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
