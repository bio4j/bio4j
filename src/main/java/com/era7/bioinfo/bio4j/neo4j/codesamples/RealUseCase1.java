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
package com.era7.bioinfo.bio4j.neo4j.codesamples;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.InterproNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.OrganismNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.TaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.CDSNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinInterproRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinOrganismRel;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;

/**
 *
 * Retrieving all nucleotide sequences of CDS corresponding to LysR family 
 * proteins of Uniprot (IPR000847 positive) belonging to 
 * enterobacteriaceae (taxonomy)
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class RealUseCase1 {

    public static void main(String[] args) {


        Bio4jManager manager = null;

        try {
            
            //--creating manager and node retriever----
            manager = new Bio4jManager(CommonData.DATABASE_FOLDER);
            NodeRetriever nodeRetriever = new NodeRetriever(manager);

            //First we get the taxon node we're interested in
            TaxonNode taxonNode = nodeRetriever.getTaxonByName("Enterobacteriaceae");
            
            System.out.println("taxonNode = " + taxonNode);

            //In this list we're gonna store the organism nodes
            ArrayList<OrganismNode> organisms = new ArrayList<OrganismNode>();

            System.out.println("Getting organisms...");
            
            //Now we get the organisms
            getAllSubOrganisms(taxonNode,organisms);
            
            //Here we'll store selected proteins
            ArrayList<ProteinNode> proteins = new ArrayList<ProteinNode>();
            
            //getting proteins for those organisms and selecting the ones
            //that have the interpro value = IPR000847
            ProteinOrganismRel proteinOrganismRel = new ProteinOrganismRel(null);
            ProteinInterproRel proteinInterproRel = new ProteinInterproRel(null);
            
            System.out.println("looping through organisms...");
            
            //----------looping through organisms checking every associated protein----
            for (OrganismNode organismNode : organisms) {
                
                System.out.println("organismNode = " + organismNode);
                
                Iterator<Relationship> iterator = organismNode.getNode().getRelationships(proteinOrganismRel, Direction.INCOMING).iterator();
                while(iterator.hasNext()){                    
                    ProteinNode tempProt = new ProteinNode(iterator.next().getStartNode());
                    
                    Iterator<Relationship> interProIterator = tempProt.getNode().getRelationships(proteinInterproRel, Direction.OUTGOING).iterator();
                    boolean interproFound = false;
                    while(interProIterator.hasNext() && !interproFound){
                        InterproNode interpro = new InterproNode(interProIterator.next().getEndNode());
                        if(interpro.getId().equals("IPR000847")){
                            interproFound = true;
                        }
                    }
                    
                    //---the protein is selected in case it has the interpro id---
                    if(interproFound){
                        proteins.add(tempProt);
                    }
                }
            }
            
            System.out.println("looping through proteins...");
            
            //At this point we should already have the proteins we want
            //now it's time to retrieve their CDS sequences
            for (ProteinNode proteinNode : proteins) {
                
                System.out.println("protein = " + proteinNode.getAccession());
                
                List<GenomeElementNode> genomeElements = proteinNode.getGenomeElements();
                
                for (GenomeElementNode genomeElementNode : genomeElements) {
                    
                    System.out.println("genomeElement = " + genomeElementNode.getVersion());
                    
                    List<CDSNode> cdsList = genomeElementNode.getCDS();
                    for (CDSNode cDSNode : cdsList) {
                        System.out.println("cDSNode = " + cDSNode);
                    }
                }
            }
            
            System.out.println("Done! :)");
            



        } catch (Exception e) {
            //deal somehow with the exception
            e.printStackTrace();
        } finally {

            //---closing the manager----
            manager.shutDown();

        }



    }

    private static void getAllSubOrganisms(TaxonNode taxonNode,
            ArrayList<OrganismNode> organisms) {

        System.out.println(taxonNode);

        organisms.addAll(taxonNode.getOrganisms());
        List<TaxonNode> children = taxonNode.getChildren();
        
        for (TaxonNode child : children) {
            getAllSubOrganisms(child, organisms);
        }

    }
}
