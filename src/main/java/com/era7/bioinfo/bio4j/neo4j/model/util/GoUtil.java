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
package com.era7.bioinfo.bio4j.neo4j.model.util;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.GoTermNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.go.IsAGoRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinGoRel;
import com.era7.lib.bioinfoxml.go.GOSlimXML;
import com.era7.lib.bioinfoxml.go.GoAnnotationXML;
import com.era7.lib.bioinfoxml.go.GoTermXML;
import com.era7.lib.bioinfoxml.go.SlimSetXML;
import com.era7.lib.bioinfoxml.uniprot.ProteinXML;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.RelationshipIndex;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GoUtil {

    private static final Logger logger = Logger.getLogger("GoUtil");

    static {
        try {
            FileHandler fh = new FileHandler("GoUtil.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (IOException ex) {
            Logger.getLogger(GoUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static GoAnnotationXML getGoAnnotation(ArrayList<ProteinXML> proteins,
            Bio4jManager manager) {

        GoAnnotationXML annotationXML = new GoAnnotationXML();
        
        HashMap<String, GoTermXML> goAnnotatorsMap = new HashMap<String, GoTermXML>();
        HashMap<String, Integer> goCountsMap = new HashMap<String, Integer>();

//        Transaction txn = manager.beginTransaction();

        try {

            //IndexService indexService = manager.getIndexService();
            Index<Node> proteinAccessionIndex = manager.getProteinAccessionIndex();

            ProteinGoRel proteinGoRel = new ProteinGoRel(null);
            Iterator<Relationship> relIterator = null;

            for (ProteinXML proteinXML : proteins) {

                IndexHits<Node> protHits = proteinAccessionIndex.get(ProteinNode.PROTEIN_ACCESSION_INDEX, proteinXML.getId());
                if (protHits.hasNext()) {
                    ProteinNode proteinNode = new ProteinNode(protHits.getSingle());
                    relIterator = proteinNode.getNode().getRelationships(proteinGoRel, Direction.OUTGOING).iterator();
                    while (relIterator.hasNext()) {

                        proteinGoRel = new ProteinGoRel(relIterator.next());
                        GoTermNode goTermNode = new GoTermNode(proteinGoRel.getRelationship().getEndNode());
                        String goId = goTermNode.getId();

                        GoTermXML goXml = new GoTermXML();
                        goXml.setId(goId);
                        goXml.setAspect(goTermNode.getNamespace());
                        goXml.setGoName(goTermNode.getName());
                        goXml.setEvidence(proteinGoRel.getEvidence());
                        proteinXML.addGoTerm(goXml, true);

                        Integer goCount = goCountsMap.get(goId);
                        if (goCount == null) {
                            goCountsMap.put(goId, 1);
                            goAnnotatorsMap.put(goId, new GoTermXML((Element) goXml.asJDomElement().clone()));
                        } else {
                            goCountsMap.put(goId, (goCount + 1));
                        }
                    }

                    proteinXML.detach();

                    annotationXML.addProteinAnnotation(proteinXML);
                }

            }

            Set<String> keySet = goAnnotatorsMap.keySet();
            for (String currentKey : keySet) {
                GoTermXML tempGo = goAnnotatorsMap.get(currentKey);
                tempGo.setAnnotationsCount(goCountsMap.get(currentKey));
                annotationXML.addAnnotatorGoTerm(tempGo);
            }


//            txn.success();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
//            txn.failure();
            annotationXML = null;
        } finally {
//            txn.finish();
        }


        return annotationXML;
    }

    /**
     * 
     * @param proteins
     * @param slimSetXML
     * @param manager
     * @param goAnnotationXML
     * @return GO Slim in xml format
     */
    public static GOSlimXML getGoSlim(ArrayList<ProteinXML> proteins,
            SlimSetXML slimSetXML,
            Bio4jManager manager,
            GoAnnotationXML goAnnotationXML) {

        GOSlimXML goSlimXML = new GOSlimXML();

        if (goAnnotationXML == null) {
            goAnnotationXML = GoUtil.getGoAnnotation(proteins, manager);
        }

        int goTermsLostNotIncludedInSlimSet = 0;

        Index<Node> goTermIdIndex = manager.getGoTermIdIndex();

        if (goAnnotationXML != null) {

            List<GoTermXML> goAnnotators = goAnnotationXML.getAnnotatorGoTerms();

            //IndexService indexService = manager.getIndexService();

            // in this hash map there is one entry for each annotator go term
            // the hash-set contains every slim-set go term including the annotator
            HashMap<String, HashSet<String>> goAnnotatorsIncludingSlimSetTermsMap = new HashMap<String, HashSet<String>>();


            //Here are the xml elements of the Go terms from the slim set termid --> term xml
            HashMap<String, GoTermXML> slimSetGos = new HashMap<String, GoTermXML>();

            //Here are the number of proteins annotated for each go term of the slim set termid --> number of proteins annotated
            HashMap<String, Integer> slimSetTermsAnnotationCounts = new HashMap<String, Integer>();

            //Now I extract the ids of the SlimSet
            List<Element> slimElements = slimSetXML.asJDomElement().getChildren(GoTermXML.TAG_NAME);
            for (Element slimElement : slimElements) {
                GoTermXML tempGo = new GoTermXML(slimElement);

                //--completing data of slimset go terms-----
                GoTermNode tempGoNode = new GoTermNode(manager.getGoTermIdIndex().get(GoTermNode.GO_TERM_ID_INDEX, tempGo.getId()).getSingle());
                tempGo.setAspect(tempGoNode.getNamespace());
                tempGo.setGoName(tempGoNode.getName());
                //------------------------

                slimSetGos.put(tempGo.getId(), tempGo);

                //initializing annotation counts map
                slimSetTermsAnnotationCounts.put(tempGo.getId(), 0);
            }
            //--------------------------------------------


//            logger.log(Level.INFO, "slimsetIds:");
//            for (String slimId : slimSetIds) {
//                logger.log(Level.INFO, ("slimId:" + slimId));
//            }


            //Now it is time for goAnnotatorsIncludingSlimSetTermsMap initialization
            for (GoTermXML goAnnotator : goAnnotators) {
                goAnnotatorsIncludingSlimSetTermsMap.put(goAnnotator.getId(), new HashSet<String>());
            }
            //------------------------------------------------------



            try {

                IsAGoRel goParentRel = new IsAGoRel(null);

                //Now I search the way up of every go Annotator and check if in the way I find
                //any of the terms included in the slim set.

                //logger.log(Level.INFO, "lalalala");

                int callCounter = 0;

                for (GoTermXML goAnnotator : goAnnotators) {
                    //this array includes the term own id and every ancestor id
                    HashSet<String> ancestorsIds = new HashSet<String>();

                    GoTermNode goTermNode = new GoTermNode(goTermIdIndex.get(GoTermNode.GO_TERM_ID_INDEX, goAnnotator.getId()).getSingle());

                    //logger.log(Level.INFO, goTermNode.toString());

                    fillUpAncestorIds(goTermNode, ancestorsIds, goParentRel, manager.getIsAGoRelIndex(), callCounter);

                    for (String ancestorId : ancestorsIds) {
                        //If the ancestor is included in the slim set, it means that this term
                        //from the slim set includes the goAnnotator 
                        if (slimSetGos.keySet().contains(ancestorId)) {
                            HashSet<String> hashSet = goAnnotatorsIncludingSlimSetTermsMap.get(goAnnotator.getId());
                            hashSet.add(ancestorId); //ancestorId is actually one of the slim-set terms ids.
                        }
                    }

                    callCounter++;
                }

                //So now I should have every goAnnotator with its corresponing slimSet terms

                List<Element> proteinList = goAnnotationXML.getProteinAnnotations().getChildren(ProteinXML.TAG_NAME);

                Element proteinsElem = new Element(GOSlimXML.PROTEINS_TAG_NAME);

                int sampleAnnotatedGeneNumber = 0;

                for (Element currentElem : proteinList) {

                    boolean annotated = false;

                    //getting the protein
                    ProteinXML currentProteinXML = new ProteinXML(currentElem);

                    //initializing inductors map                    
                    HashMap<String, String> currentProteinSlimTermInductors = new HashMap<String, String>();
                    //proteinSlimTermsAndInductorTermsMap.put(currentProteinXML.getId(), currentProteinSlimTermInductors);

                    //System.out.println("currentProteinXML.getId() = " + currentProteinXML.getId());

                    //--------now we access to its go annotations-------------
                    List<GoTermXML> proteinTerms = new ArrayList<GoTermXML>();
                    List<GoTermXML> bpTerms = currentProteinXML.getBiologicalProcessGoTerms();
                    List<GoTermXML> ccTerms = currentProteinXML.getCellularComponentGoTerms();
                    List<GoTermXML> mfTerms = currentProteinXML.getMolecularFunctionGoTerms();
                    if (bpTerms != null) {
                        proteinTerms.addAll(bpTerms);
                    }
                    if (ccTerms != null) {
                        proteinTerms.addAll(ccTerms);
                    }
                    if (mfTerms != null) {
                        proteinTerms.addAll(mfTerms);
                    }
                    //------------------------------------------------------
                    //creating the result xml protein
                    ProteinXML proteinResult = new ProteinXML();
                    proteinResult.setId(currentProteinXML.getId());
                    HashSet<String> proteinSlimTems = new HashSet<String>();
                    for (GoTermXML goTermXML : proteinTerms) {
                        HashSet<String> hashSet = goAnnotatorsIncludingSlimSetTermsMap.get(goTermXML.getId());

                        System.out.println("");

                        if (hashSet != null) {
                            if (hashSet.size() > 0) {
                                proteinSlimTems.addAll(hashSet);
                                for (String tempSlimTermId : hashSet) {
                                    currentProteinSlimTermInductors.put(tempSlimTermId, goTermXML.getId());
                                }
                                annotated = true;
                            } else {
                                //-----The go term annotation lost is stored-------
                                goSlimXML.addGoTermLostNotIncludedInSlimSet(new GoTermXML((Element) goTermXML.asJDomElement().clone()));
                                System.out.println("holaaa!" + goTermXML.getId());
                            }
                        }
                    }

                    //now we get the info from the slimset go terms
                    for (String string : proteinSlimTems) {
                        //logger.log(Level.INFO, ("string: " + string));
                        GoTermXML tempGoTerm = new GoTermXML((Element) slimSetGos.get(string).asJDomElement().clone());

                        //------Adding protein annotation term leading to slim set term -----------
                        String termInductorId = currentProteinSlimTermInductors.get(tempGoTerm.getId());
                        //look for inductor info                        
                        for (GoTermXML goTermXML : proteinTerms) {
                            //System.out.println("goTermXML = " + goTermXML);
                            if (goTermXML.getId().equals(termInductorId)) {
                                tempGoTerm.setProteinAnnotationLeadingToSlimTerm(new GoTermXML((Element) goTermXML.asJDomElement().clone()));
                                break;
                            }
                        }
                        //--------------------------------------------------------------------------

                        //logger.log(Level.INFO, ("tempGoTerm: " + tempGoTerm));
                        proteinResult.addGoTerm(tempGoTerm, true);

                        //updating annotation counts
                        slimSetTermsAnnotationCounts.put(string, slimSetTermsAnnotationCounts.get(string) + 1);
                    }

                    proteinsElem.addContent(proteinResult.asJDomElement());

                    if (annotated) {
                        sampleAnnotatedGeneNumber++;
                    }

                }

                //updating slimset annotation counts
                List<Element> slimSetElems = slimSetXML.asJDomElement().getChildren(GoTermXML.TAG_NAME);
                for (Element slimSetElem : slimSetElems) {
                    GoTermXML slimSetGo = new GoTermXML(slimSetElem);
                    slimSetGo.setAnnotationsCount(slimSetTermsAnnotationCounts.get(slimSetGo.getId()));
                }

                goSlimXML.asJDomElement().addContent(proteinsElem);
                slimSetXML.detach();
                goSlimXML.setSlimSet(slimSetXML);
                goSlimXML.setSampleGeneNumber(proteinList.size());
                goSlimXML.setSampleAnnotatedGeneNumber(sampleAnnotatedGeneNumber);
                //goSlimXML.set

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
                goSlimXML = null;
            }


        } else {
            goSlimXML = null;
        }


        return goSlimXML;
    }

    private static void fillUpAncestorIds(GoTermNode node,
            HashSet<String> ancestorsIds,
            IsAGoRel goParentRel,
            RelationshipIndex goParentRelIndex,
            int call) {

        ancestorsIds.add(node.getId());

        //logger.log(Level.INFO, ("fillUpAncestorIds (v2) --> " + node.getId() + " call: " + call));


        Iterator<Relationship> relIterator = goParentRelIndex.get(IsAGoRel.IS_A_REL_INDEX, String.valueOf(node.getNode().getId())).iterator();
        if (relIterator.hasNext()) {
            node = new GoTermNode(relIterator.next().getEndNode());

            fillUpAncestorIds(node, ancestorsIds, goParentRel, goParentRelIndex, call);
            while (relIterator.hasNext()) {
                node = new GoTermNode(relIterator.next().getEndNode());
                //logger.log(Level.INFO, ("double parent --> " + node.getId() + " call: " + call));
                fillUpAncestorIds(node, ancestorsIds, goParentRel, goParentRelIndex, call + 400000);
            }
        }


    }
}
