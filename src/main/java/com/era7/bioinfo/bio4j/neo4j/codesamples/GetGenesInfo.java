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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;

/**
 *
 * Retrieves basic gene information for a protein
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GetGenesInfo {

    /**
     * @param args Just two arguments (DB folder and protein accession)
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("The program expects two parameters: \n"
                    + "1. Bio4j DB folder\n"
                    + "2. Protein accession\n");
        } else {

            String inputSt = args[1];

            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);

                //--------------------------------------------------------------
                //-----retrieving protein by its accession---------

                ProteinNode protein = nodeRetriever.getProteinNodeByAccession(inputSt);

                //checking there's a protein with the accession provided...
                if (protein == null) {
                    System.out.println("There's no protein with the accession " + inputSt);
                } else {
                    System.out.println("Protein found: \n" + inputSt);
                    
                    //----getting gene names-----                
                    String[] geneNames = protein.getGeneNames();
                    System.out.println("Gene names: ");
                    for (String string : geneNames) {
                        System.out.println(string + ",");
                    }
                }
                //--------------------------------------------------------------


            } catch (Exception e) {
                //deal somehow with the exception
            } finally {

                //---closing the manager----
                manager.shutDown();

            }

        }
    }
}
