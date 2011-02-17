/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.nodes.GoTermNode;
import com.era7.bioinfo.bio4jmodel.nodes.InterproNode;
import com.era7.bioinfo.bio4jmodel.nodes.KeywordNode;
import com.era7.bioinfo.bio4jmodel.nodes.OrganismNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.nodes.TaxonNode;
import com.era7.bioinfo.bio4jmodel.relationships.TaxonParentRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinGoRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinInterproRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinKeywordRel;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.SubcellularLocationNode;
import com.era7.bioinfo.bio4jmodel.relationships.SubcellularLocationParentRel;
import com.era7.bioinfo.bio4jmodel.relationships.features.RepeatFeatureRel;
import com.era7.bioinfo.bio4jmodel.relationships.protein.ProteinSubcellularLocationRel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author ppareja
 */
public class GetProteinData {

    public static void main(String[] args) throws IOException {
        String name = args[0];


        System.out.println("name = " + name);
        System.out.println("creating manager...");
        Neo4jManager manager = Neo4jManager.getNeo4JManager(CommonData.DATABASE_FOLDER);

        long number = ((EmbeddedGraphDatabase) manager.getGraphService()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Node.class);
        System.out.println("nodes number = " + number);

        number = ((EmbeddedGraphDatabase) manager.getGraphService()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Relationship.class);
        System.out.println("relationships number = " + number);

        System.out.println("creating index manager...");
        IndexService indexService = manager.getIndexService();
        System.out.println("getting node...");
        Transaction txn = manager.beginTransaction();


        Node node = null;
        try {

            BufferedWriter interproBuffer = new BufferedWriter(new FileWriter("interproIPR000847.txt"));

//            System.out.println("Getting proteins with Interpro motif ID:  IPR000847...");
//            int interproCounter = 0;
//            InterproNode interproNode = new InterproNode(indexService.getSingleNode(InterproNode.INTERPRO_ID_INDEX, "IPR000847"));
//            Iterator<Relationship> interproRelIterator = interproNode.getNode().getRelationships(new ProteinInterproRel(null), Direction.INCOMING).iterator();
//            while (interproRelIterator.hasNext()) {
//                ProteinNode tempProt = new ProteinNode(interproRelIterator.next().getStartNode());
//                interproCounter++;
//                if (interproCounter > 1000) {
//                    System.out.println("1000 more!");
//                    interproCounter = 0;
//                    txn.success();
//                    txn.finish();
//                    txn = manager.beginTransaction();
//                }
//                interproBuffer.write(tempProt.getAccession() + "\n");
//            }
//            interproBuffer.close();

            node = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, name);

            if (node != null) {

                ProteinNode protein = new ProteinNode(node);

                System.out.println("protein: " + protein);

                System.out.println("Getting keywords...");
                Iterator<Relationship> relIt = protein.getNode().getRelationships(new ProteinKeywordRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    KeywordNode keyword = new KeywordNode(relIt.next().getEndNode());
                    System.out.println("keyword.getId() = " + keyword.getId());
                    System.out.println("keyword.getName() = " + keyword.getName());
                }

                System.out.println("Getting interpro...");
                relIt = protein.getNode().getRelationships(new ProteinInterproRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    InterproNode interpro = new InterproNode(relIt.next().getEndNode());
                    System.out.println(interpro);
                }

                System.out.println("Getting subcellular locations...");
                relIt = protein.getNode().getRelationships(new ProteinSubcellularLocationRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    ProteinSubcellularLocationRel proteinSubcellularLocationRel = new ProteinSubcellularLocationRel(relIt.next());
                    SubcellularLocationNode sub = new SubcellularLocationNode(proteinSubcellularLocationRel.getEndNode());
                    System.out.println(proteinSubcellularLocationRel);
                    ArrayList<String> subcelArray = new ArrayList<String>();
                    subcelArray.add(sub.getName());
                    //System.out.println(sub);
                    Node lastNode = sub.getNode();
                    while (lastNode != null) {
                        Relationship parentRel = lastNode.getSingleRelationship(new SubcellularLocationParentRel(null), Direction.OUTGOING);
                        if (parentRel != null) {
                            sub = new SubcellularLocationNode(parentRel.getEndNode());
                            subcelArray.add(sub.getName());
                            lastNode = sub.getNode();
                        } else {
                            lastNode = null;
                        }
                    }

                    for (int i = subcelArray.size() - 1; i >= 0; i--) {
                        System.out.print(subcelArray.get(i) + " --> ");
                    }
                    System.out.println("");

                }


                System.out.println("Getting repeat features....");
                relIt = protein.getNode().getRelationships(new RepeatFeatureRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    RepeatFeatureRel featureRel = new RepeatFeatureRel(relIt.next());
                    System.out.println(featureRel);
                }

                System.out.println("Getting go ontology...");
                relIt = protein.getNode().getRelationships(new ProteinGoRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    ProteinGoRel goRel = new ProteinGoRel(relIt.next());
                    String evidence = goRel.getEvidence();
                    GoTermNode term = new GoTermNode(goRel.getEndNode());
                    System.out.println(term);
                    System.out.println("evidence = " + evidence);
                }

                OrganismNode organism = protein.getOrganism();
                System.out.println("organism.getScientificName() = " + organism.getScientificName());
                System.out.println("organism.getCommonName() = " + organism.getCommonName());
                System.out.println("organism.getSynonymName() = " + organism.getSynonymName());
                System.out.println("organism.getNcbiTaxonomyId() = " + organism.getNcbiTaxonomyId());

                System.out.println("Protein dataset: " + protein.getDataset().getName());

                System.out.println("Getting lineage...");
                Node currentNode = organism.getNode();
                Iterator<Relationship> iterator = null;
                iterator = currentNode.getRelationships(new TaxonParentRel(null), Direction.INCOMING).iterator();
                while (iterator.hasNext()) {
                    Relationship rel = iterator.next();
                    TaxonNode taxon = new TaxonNode(rel.getStartNode());
                    System.out.print(taxon.getName() + " --> ");
                    currentNode = taxon.getNode();
                    iterator = currentNode.getRelationships(new TaxonParentRel(null), Direction.INCOMING).iterator();
                }


            } else {
                System.out.println("node is null.... :(");
            }


            txn.success();

        } catch (Exception e) {
            e.printStackTrace();
            txn.failure();
        } finally {
            txn.finish();
            indexService.shutdown();
            manager.shutDown();
        }






    }
}
