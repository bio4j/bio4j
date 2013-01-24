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
package com.era7.bioinfo.bio4j.neo4j.programs;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.TaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.KeywordNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.InterproNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.OrganismNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.GoTermNode;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.ArticleNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.SubmissionNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.TaxonParentRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.go.IsAGoRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinGoRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinInterproRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinKeywordRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.IndexHits;

/**
 * Test program
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GetProteinData {

    private static final Logger logger = Logger.getLogger("GetProteinData");
    private static FileHandler fh;
    private static Bio4jManager manager = null;

    public static void main(String[] args) throws IOException {
        String name = args[0];

        // This block configures the logger with handler and formatter
        fh = new FileHandler("GetProteinData" + args[0].split("\\.")[0] + ".log", false);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);

        System.out.println("name = " + name);
        System.out.println("creating manager...");
        manager = new Bio4jManager(CommonData.DATABASE_FOLDER);

        //long number = ((EmbeddedGraphDatabase) manager.getGraphService()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Node.class);
        //System.out.println("nodes number = " + number);

        //number = ((EmbeddedGraphDatabase) manager.getGraphService()).getConfig().getGraphDbModule().getNodeManager().getNumberOfIdsInUse(Relationship.class);
        //.out.println("relationships number = " + number);

        //Transaction txn = manager.beginTransaction();


//        NodeRetriever nodeRetriever = new NodeRetriever(manager);
//        GenomeElementNode genomeElementNode = nodeRetriever.getGenomeElementByVersion(name);
//        System.out.println("\ngenomeElementNode = " + genomeElementNode);
//        
//        System.out.println("Number of CDS: " + genomeElementNode.getCDS().size());
//        System.out.println("Number of genes: " + genomeElementNode.getGenes().size());
//        System.out.println("Number of Mrnas: " + genomeElementNode.getMRnas().size());
//        System.out.println("Number of misc rnas: " + genomeElementNode.getMiscRnas().size());
//        System.out.println("Number of nc rnas: " + genomeElementNode.getNcRnas().size());
//        System.out.println("Number of Rrnas: " + genomeElementNode.getRRnas().size());
//        System.out.println("Number of Trnas: " + genomeElementNode.getTRnas().size());
//        System.out.println("Number of Tm rnas: " + genomeElementNode.getTmRnas().size());


        Node node = null;

        try {

            //            BufferedWriter interproBuffer = new BufferedWriter(new FileWriter("interproIPR000847.txt"));
//
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

//            System.out.println("Query entered: " + args[1]);
//            System.out.println("With full text index service");
//            IndexHits<Node> hits = fullTextIndexService.getNodes(ProteinNode.PROTEIN_FULL_NAME_FULL_TEXT_INDEX, args[1].toUpperCase());
//            System.out.println("results: " + hits.size());
//            System.out.println("With full text QUERY index service");
//            hits = fullTextQueryIndexService.getNodes(ProteinNode.PROTEIN_FULL_NAME_FULL_TEXT_INDEX, args[1].toUpperCase());
//            System.out.println("results: " + hits.size());
//            System.out.println("With full text QUERY index service (gene names)");
//            hits = fullTextQueryIndexService.getNodes(ProteinNode.PROTEIN_GENE_NAMES_FULL_TEXT_INDEX, args[1]);
//            System.out.println("results: " + hits.size());
//
//            for (Node node1 : hits) {
//                ProteinNode tempProt = new ProteinNode(node1);
//                System.out.println(tempProt.getAccession());
//            }

//            System.out.println("going through all biological process nodes...");
//            GoTermNode goNode = new GoTermNode(manager.getGoTermIdIndex().get(GoTermNode.GO_TERM_ID_INDEX, "GO:0003735").getSingle());
//            //System.out.println(bioProcNode.getNode().getRelationships(new GoParentRel(null)).iterator().hasNext());
//            //System.out.println("bioProcNodeNumber = " + (getChildrenNumber(bioProcNode) + 1));
//            System.out.println("goNode.getId() = " + goNode.getId());
//
//            RelationshipIndex indexRel = manager.getIsAGoRelIndex();
//            IndexHits<Relationship> hits = indexRel.get(IsAGoRel.IS_A_REL_INDEX, String.valueOf(goNode.getNode().getId()),null,null);
//
//            System.out.println("hits.size() = " + hits.size());
//
//            while(hits.hasNext() ){
//                System.out.println("hits.size() = " + hits.size());
//                GoTermNode goParentNode = new GoTermNode(hits.getSingle().getEndNode());
//                System.out.println("goParentNode.getId() = " + goParentNode.getId());
//                goNode = goParentNode;
//
//                hits = indexRel.get(IsAGoRel.IS_A_REL_INDEX, goNode.getId());
//            }
//            System.out.println("done!");


            IndexHits<Node> indexHits = manager.getProteinAccessionIndex().get(ProteinNode.PROTEIN_ACCESSION_INDEX, name);

            System.out.println("indexHits.size() = " + indexHits.size());
            while (indexHits.hasNext()) {
                node = indexHits.next();
                System.out.println("node = " + new ProteinNode(node));
            }


            if (node != null) {

                ProteinNode protein = new ProteinNode(node);

                //System.out.println("protein: " + protein);

                System.out.println("protein.getNode().getId() = " + protein.getNode().getId());


                System.out.println("gene names:");
                for (String string : protein.getGeneNames()) {
                    System.out.println(string);
                }
//                System.out.println("EMBL references:");
//                for (String string : protein.getEMBLreferences()) {
//                    System.out.println(string);
//                }

                System.out.println("Getting Uniref 90 information...");
                Iterator<Relationship> uniref90RepresentantIterator = protein.getNode().getRelationships(new UniRef90MemberRel(null), Direction.OUTGOING).iterator();
                if (uniref90RepresentantIterator.hasNext()) {
                    System.out.println("The protein is representant of a Uniref 90 cluster");
                    System.out.println("the members of this cluster are:");
                    while (uniref90RepresentantIterator.hasNext()) {
                        ProteinNode tempProt = new ProteinNode(uniref90RepresentantIterator.next().getEndNode());
                        System.out.println(tempProt.getAccession());
                    }
                } else {
                    Iterator<Relationship> uniref90memberIterator = protein.getNode().getRelationships(new UniRef90MemberRel(null), Direction.INCOMING).iterator();
                    if (uniref90memberIterator.hasNext()) {
                        System.out.println("The protein is member of the cluster: ");
                        ProteinNode tempProt = new ProteinNode(uniref90memberIterator.next().getStartNode());                        
                        System.out.println(tempProt.getAccession());
                    }
                }



                //System.out.println("Getting genome element...");
                //GenomeElementNode genomeElementNode = protein.getGenomeElement();
//                System.out.println(genomeElementNode);
//                System.out.println("Number of CDS: " + genomeElementNode.getCDS().size());
//                System.out.println("Number of genes: " + genomeElementNode.getGenes().size());
//                System.out.println("Number of Mrnas: " + genomeElementNode.getMRnas().size());
//                System.out.println("Number of misc rnas: " + genomeElementNode.getMiscRnas().size());
//                System.out.println("Number of nc rnas: " + genomeElementNode.getNcRnas().size());
//                System.out.println("Number of Rrnas: " + genomeElementNode.getRRnas().size());
//                System.out.println("Number of Trnas: " + genomeElementNode.getTRnas().size());
//                System.out.println("Number of Tm rnas: " + genomeElementNode.getTmRnas().size());

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

//                System.out.println("Getting subcellular locations...");
//                relIt = protein.getNode().getRelationships(new ProteinSubcellularLocationRel(null), Direction.OUTGOING).iterator();
//                while (relIt.hasNext()) {
//                    ProteinSubcellularLocationRel proteinSubcellularLocationRel = new ProteinSubcellularLocationRel(relIt.next());
//                    SubcellularLocationNode sub = new SubcellularLocationNode(proteinSubcellularLocationRel.getRelationship().getEndNode());
//                    System.out.println(proteinSubcellularLocationRel);
//                    ArrayList<String> subcelArray = new ArrayList<String>();
//                    subcelArray.add(sub.getName());
//                    //System.out.println(sub);
//                    Node lastNode = sub.getNode();
//                    while (lastNode != null) {
//                        Relationship parentRel = lastNode.getSingleRelationship(new SubcellularLocationParentRel(null), Direction.OUTGOING);
//                        if (parentRel != null) {
//                            sub = new SubcellularLocationNode(parentRel.getEndNode());
//                            subcelArray.add(sub.getName());
//                            lastNode = sub.getNode();
//                        } else {
//                            lastNode = null;
//                        }
//                    }
//
//                    for (int i = subcelArray.size() - 1; i >= 0; i--) {
//                        System.out.print(subcelArray.get(i) + " --> ");
//                    }
//                    System.out.println("");
//
//                }


//                System.out.println("Getting repeat features....");
//                relIt = protein.getNode().getRelationships(new RepeatFeatureRel(null), Direction.OUTGOING).iterator();
//                while (relIt.hasNext()) {
//                    RepeatFeatureRel featureRel = new RepeatFeatureRel(relIt.next());
//                    System.out.println(featureRel);
//                }


//                System.out.println("Getting some gos");
//                IndexHits<Node> goHits = manager.getGoTermIdIndex().get(GoTermNode.GO_TERM_ID_INDEX, "GO:0005634");
//                if(goHits.hasNext()){
//                    System.out.println("found!");
//                    System.out.println("goHits = " + new GoTermNode(goHits.getSingle()));
//                }else{
//                    System.out.println("no hits found....");
//                }

                System.out.println("Getting go annotations...");
                relIt = protein.getNode().getRelationships(new ProteinGoRel(null), Direction.OUTGOING).iterator();
                while (relIt.hasNext()) {
                    ProteinGoRel goRel = new ProteinGoRel(relIt.next());
                    String evidence = goRel.getEvidence();
                    GoTermNode term = new GoTermNode(goRel.getRelationship().getEndNode());
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

                System.out.println("Getting citations...");

                System.out.println("Article citations:");
                for (ArticleNode article : protein.getArticleCitations()) {
                    System.out.println(article);
                }
                System.out.println("Submission citations:");
                for (SubmissionNode submission : protein.getSubmissionCitations()) {
                    System.out.println(submission);
                }

                System.out.println("DONE!! :)");



            } else {
                System.out.println("node is null.... :(");
            }


            //txn.success();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            StackTraceElement[] trace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : trace) {
                logger.log(Level.SEVERE, stackTraceElement.toString());
            }
        } finally {
            try {
                //txn.finish();
                manager.shutDown();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }

        }

    }

    private static int getChildrenNumber(GoTermNode node) {
        int counter = 0;

        IndexHits<Relationship> hits = manager.getIsAGoRelIndex().get(IsAGoRel.IS_A_REL_INDEX, node.getId());

        System.out.println("hits.size() = " + hits.size());

        while (hits.hasNext()) {
            GoTermNode child = new GoTermNode(hits.next().getEndNode());
            counter++;
            counter += getChildrenNumber(child);
        }

        return counter;
    }
}
