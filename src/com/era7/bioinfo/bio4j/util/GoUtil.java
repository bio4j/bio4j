/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.util;

import com.era7.bioinfo.bio4jmodel.nodes.GoTermNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.relationships.GoParentRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinGoRel;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import com.era7.lib.bioinfoxml.ProteinXML;
import com.era7.lib.bioinfoxml.go.GOSlimXML;
import com.era7.lib.bioinfoxml.go.GoAnnotationXML;
import com.era7.lib.bioinfoxml.go.GoTermXML;
import com.era7.lib.bioinfoxml.go.SlimSetXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexService;

/**
 *
 * @author ppareja
 */
public class GoUtil {

    public static GoAnnotationXML getGoAnnotation(ArrayList<ProteinXML> proteins,
            Neo4jManager manager) {

        GoAnnotationXML annotationXML = new GoAnnotationXML();


        HashMap<String, GoTermXML> goAnnotatorsMap = new HashMap<String, GoTermXML>();
        HashMap<String, Integer> goCountsMap = new HashMap<String, Integer>();

        Transaction txn = manager.beginTransaction();

        try {

            IndexService indexService = manager.getIndexService();

            ProteinGoRel proteinGoRel = new ProteinGoRel(null);
            Iterator<Relationship> relIterator = null;

            for (ProteinXML proteinXML : proteins) {
                
                ProteinNode proteinNode = new ProteinNode(indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, proteinXML.getId()));
                relIterator = proteinNode.getNode().getRelationships(proteinGoRel, Direction.OUTGOING).iterator();
                while (relIterator.hasNext()) {

                    proteinGoRel = new ProteinGoRel(relIterator.next());
                    GoTermNode goTermNode = new GoTermNode(proteinGoRel.getEndNode());
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

            Set<String> keySet = goAnnotatorsMap.keySet();
            for (String currentKey : keySet) {
                GoTermXML tempGo = goAnnotatorsMap.get(currentKey);
                tempGo.setAnnotationsCount(goCountsMap.get(currentKey));
                annotationXML.addAnnotatorGoTerm(tempGo);
            }


            txn.success();

        } catch (Exception e) {
            txn.failure();
            annotationXML = null;
        } finally {
            txn.finish();
        }


        return annotationXML;
    }

    public static GOSlimXML getGoSlim(ArrayList<ProteinXML> proteins,
            SlimSetXML slimSetXML,
            Neo4jManager manager) {

        GOSlimXML goSlimXML = new GOSlimXML();

        GoAnnotationXML goAnnotationXML = GoUtil.getGoAnnotation(proteins, manager);

        if (goAnnotationXML != null) {
            List<GoTermXML> goAnnotators = goAnnotationXML.getAnnotatorGoTerms();

            IndexService indexService = manager.getIndexService();

            // in this hash map there is one entry for each annotator go term
            // the hash-set contains every slim-set go term including the annotator
            HashMap<String, HashSet<String>> goAnnotatorsIncludingSlimSetTermsMap = new HashMap<String, HashSet<String>>();

            //Here is every id included in the slim set
            HashSet<String> slimSetIds = new HashSet<String>();
            
            //Now I extract the ids of the SlimSet
            List<Element> slimElements = slimSetXML.asJDomElement().getChildren(GoTermXML.TAG_NAME);
            for (Element slimElement : slimElements) {
                GoTermXML tempGo = new GoTermXML(slimElement);
                slimSetIds.add(tempGo.getId());
            }
            //--------------------------------------------

            //Now it is time for goAnnotatorsIncludingSlimSetTermsMap initialization
            for (GoTermXML goAnnotator : goAnnotators) {
                goAnnotatorsIncludingSlimSetTermsMap.put(goAnnotator.getId(), new HashSet<String>());
            }
            //------------------------------------------------------

            Transaction txn = manager.beginTransaction();

            try {

                GoParentRel goParentRel = new GoParentRel(null);

                //Now I search the way up of every go Annotator and check if in the way I find
                //any of the terms included in the slim set.
                for (GoTermXML goAnnotator : goAnnotators) {
                    //this array includes the term own id and every ancestor id
                    ArrayList<String> ancestorsIds = new ArrayList<String>();

                    GoTermNode goTermNode = new GoTermNode(indexService.getSingleNode(GoTermNode.GO_TERM_ID_INDEX, goAnnotator.getId()));
                    //now I look up for the term ancestors
                    GoTermNode parent = goTermNode;
                    while (parent != null) {
                        ancestorsIds.add(parent.getId());

                        Node nodeParent = parent.getNode().getSingleRelationship(goParentRel, Direction.OUTGOING).getEndNode();
                        if(nodeParent != null){
                            parent = new GoTermNode(nodeParent);
                        }else{
                            parent = null;
                        }
                    }

                    for (String ancestorId : ancestorsIds) {
                        //If the ancestor is included in the slim set, it means that this term
                        //from the slim set includes the goAnnotator 
                        if(slimSetIds.contains(ancestorId)){
                            HashSet<String> hashSet = goAnnotatorsIncludingSlimSetTermsMap.get(goAnnotator.getId());
                            hashSet.add(ancestorId); //ancestorId is actually one of the slim-set terms ids.
                        }
                    }
                }


                //So now I should have every goAnnotator with its corresponing slimSet terms
                


                txn.success();
            } catch (Exception e) {
                e.printStackTrace();
                txn.failure();
                goSlimXML = null;
            } finally {
                txn.finish();
            }


        } else {
            goSlimXML = null;
        }


        return goSlimXML;
    }
}
