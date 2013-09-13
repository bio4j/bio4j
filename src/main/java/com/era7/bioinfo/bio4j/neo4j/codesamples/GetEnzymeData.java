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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.EnzymeNode;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;

/**
 *
 * Gets basic data for the Enzyme ID provided
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GetEnzymeData {

    /**
     * @param args Just one argument (protein accession)
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The program expects two parameters: \n"
                    + "2. Bio4j DB folder\n"
                    + "1. Enzyme ID\n");
        } else {

            String inputSt = args[1];

            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);

                //--------------------------------------------------------------
                //-----retrieving enzyme node by its id---------

                EnzymeNode enzymeNode = nodeRetriever.getEnzymeById(inputSt);

                if (enzymeNode != null) {
                    System.out.println("enzymeNode = " + enzymeNode);
                    System.out.println("Official name: " + enzymeNode.getOfficialName());
                    System.out.println("Catalytic activity: " + enzymeNode.getCatalyticActivity());
                    System.out.println("Alternate names:");
                    for (String altName : enzymeNode.getAlternateNames()) {
                        System.out.println(altName);
                    }
                    System.out.println("Comments:");
                    System.out.println(enzymeNode.getComments());

                    System.out.println("Prosite cross-references:");
                    for (String prositeRef : enzymeNode.getPrositeCrossReferences()) {
                        System.out.println(prositeRef);
                    }
                    System.out.println("Cofactors:");
                    for (String cofactor : enzymeNode.getCofactors()) {
                        System.out.println(cofactor);
                    }
                }else{
                    System.out.println("Enzyme not found... :(");
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //---closing the manager----
                manager.shutDown();
            }

        }
    }
}
