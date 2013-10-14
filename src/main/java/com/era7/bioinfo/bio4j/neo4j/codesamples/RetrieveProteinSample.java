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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;
import java.util.List;

/**
 *
 * Retrieving a protein by its accession and accessing some of its data
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class RetrieveProteinSample {

    /**
     * @param args Just two arguments (DB folder & protein accession)
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The program expects two parameters: \n"
                    + "1. Bio4j DB folder\n"
                    + "2. Protein accesion\n");
        } else {

            String inputSt = args[1];

            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                
                //--------------------------------------------------------------
                //-----retrieving protein node by its Uniprot accession---------
                
                ProteinNode protein = nodeRetriever.getProteinNodeByAccession(inputSt);
                
                //checking there's a protein with the accession provided...
                if (protein == null) {
                    System.out.println("There's no protein with the accession " + inputSt);
                } else {
                    System.out.println("Protein data: \n" + protein);
                }
                //--------------------------------------------------------------
                
                //--------------------------------------------------------------
                //---retrieving proteins by their fullname (full-text index)----
                
                List<ProteinNode> proteinList = nodeRetriever.getProteinsByFullName(inputSt);
                
                if(proteinList.size() > 0){
                    System.out.println("There were " + proteinList.size()
                            + " hits found for proteins with fullname like: " + inputSt);
                    for (ProteinNode proteinNode : proteinList) {
                        System.out.println("Accession: " + proteinNode.getAccession() + 
                                            "\nFullname: " + proteinNode.getFullName());
                    }                    
                }else{
                    System.out.println("There were no proteins found with fullname like: " + inputSt);
                }                
                //--------------------------------------------------------------
                
                //--------------------------------------------------------------
                //---retrieving proteins by their gene names (full-text index)----
                proteinList = nodeRetriever.getProteinsByGeneNames(inputSt);
                                                       
                if(proteinList.size() > 0){
                    System.out.println("There were " + proteinList.size()
                            + " hits found for proteins with gene names like: " + inputSt);
                    for (ProteinNode proteinNode : proteinList) {
                        System.out.println("Accession: " + proteinNode.getAccession());
                        System.out.println("Gene names:");
                        
                        for (String geneName : proteinNode.getGeneNames()) {
                            System.out.println(geneName);
                        }
                         
                    }                    
                }else{
                    System.out.println("There were no proteins found with gene names like: " + inputSt);
                }                   
                //--------------------------------------------------------------
            

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                //---closing the manager----
                manager.shutDown();

            }

        }
    }
}
