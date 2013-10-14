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
package com.era7.bioinfo.bio4j.neo4j.codesamples;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.EnzymeNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinEnzymaticActivityRel;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class BiodieselProductionSample {
    
    /**
     * @param args Just one argument (protein accession)
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("The program expects two parameters: \n"
                    + "1. Bio4j DB folder\n"
                    + "2. Enzyme IDs file\n"
                    + "3. Output file name");
        } else {

            File enzymeFile = new File(args[1]);
            File outFile = new File(args[2]);

            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                
                LinkedList<EnzymeNode> enzymeList = new LinkedList<>();
                List<String> enzymeIDList = new ArrayList<>();

                System.out.println("Reading input file...");
                
                BufferedReader reader = new BufferedReader(new FileReader(enzymeFile));
                String line;
                while((line = reader.readLine()) != null){
                    EnzymeNode enzymeNode = nodeRetriever.getEnzymeById(line);
                    if(enzymeNode != null){
                        enzymeList.add(enzymeNode);
                        enzymeIDList.add(line);
                    }else{
                        System.out.println("There was no Enzyme found for ID: " + line);
                    }                    
                }                
                reader.close();
                
                System.out.println("Done!");
                
                Map<String, ProteinNode> proteinMap = new HashMap<>();                
                
                for (EnzymeNode enzymeNode : enzymeList) {
                    System.out.println("Retrieving proteins for enzyme: " + enzymeNode.getId());
                    Iterator<Relationship> iterator = enzymeNode.getNode().getRelationships(Direction.INCOMING, new ProteinEnzymaticActivityRel(null)).iterator();
                    while(iterator.hasNext()){
                        ProteinNode proteinNode = new ProteinNode(iterator.next().getStartNode());
                        ProteinNode tempProtein = proteinMap.get(proteinNode.getAccession());
                        if(tempProtein == null){
                            proteinMap.put(proteinNode.getAccession(), proteinNode);
                        }
                    }
                }
                
                System.out.println("There were " + proteinMap.size() + " proteins found");
                
                System.out.println("Filtering proteins that don't have a RefSeq GenomeElement associated...");
                int proteinsFilteredCounter = 0;
                
                for (String string : proteinMap.keySet()) {
                    ProteinNode tempProtein = proteinMap.get(string);
                    if(tempProtein.getGenomeElements().isEmpty()){
                        proteinMap.remove(string);
                        proteinsFilteredCounter++;
                    }
                }
                
                System.out.println("Done!");
                System.out.println("There were " + proteinsFilteredCounter + " proteins filtered...");
                
                System.out.println("Now we filtered those proteins that have less than 4 enzymes associated from the list provided...");
                proteinsFilteredCounter = 0;
                for (String string : proteinMap.keySet()) {
                    ProteinNode tempProtein = proteinMap.get(string);
                    List<EnzymeNode> tempEnzymes = tempProtein.getProteinEnzymaticActivity();
                    int tempCounter = 0;
                    boolean filterPassed = false;
                    for(int i=0; i<tempEnzymes.size() && !filterPassed; i++){
                        EnzymeNode enzymeNode = tempEnzymes.get(i);
                        if(enzymeIDList.contains(enzymeNode.getId())){
                            tempCounter++;
                            if(tempCounter >= 4){
                                filterPassed = true;
                            }
                        }
                    }
                    if(!filterPassed){
                        proteinsFilteredCounter++;
                        proteinMap.remove(string);
                    }                    
                }
                
                System.out.println("Done!");
                System.out.println("There were " + proteinsFilteredCounter + " proteins filtered...");
                
                System.out.println("Time to retrieve an filter by organisms...");
                proteinsFilteredCounter = 0;
                for (String string : proteinMap.keySet()) {
                    ProteinNode tempProtein = proteinMap.get(string);
                    NCBITaxonNode taxon = nodeRetriever.getNCBITaxonByTaxId(tempProtein.getOrganism().getNcbiTaxonomyId());
                    boolean bacteriaFound = false;                    
                    while(!bacteriaFound && taxon != null){
                        if(taxon.getScientificName().equals("Bacteria")){
                            bacteriaFound = true;
                        }else{
                            taxon = taxon.getParent();
                        }                        
                    }
                    if(!bacteriaFound){
                        proteinsFilteredCounter++;
                        proteinMap.remove(string);
                    }                   
                }
                
                System.out.println("Done!");
                System.out.println("There were " + proteinsFilteredCounter + " proteins filtered...");
                
                System.out.println("Writing output file...");
                
                BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
                for (String string : proteinMap.keySet()) {
                    writer.write(string + "\n");
                }
                writer.close();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //---closing the manager----
                manager.shutDown();
            }

        }
    }
    
}
