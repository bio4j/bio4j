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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.InterproNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinInterproRel;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;
import java.util.Iterator;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;

/**
 *
 * Retrieves proteins with the interpro motif provided
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GetProteinsWithInterpro {

    /**
     * @param args Just two arguments (DB folder and interpro motif)
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The program expects two parameters: \n"
                    + "1. Bio4j DB folder\n"
                    + "2. Interpro motif\n");
        } else {

            String inputSt = args[1];

            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                
                //--------------------------------------------------------------
                //-----retrieving interpro motif by its ID---------
                
                InterproNode interpro = nodeRetriever.getInterproById(inputSt);
                
                //checking there's a intepro motif with the ID provided...
                if (interpro == null) {
                    System.out.println("There's no intepro motif with the ID " + inputSt);
                } else {
                    System.out.println("Interpro motif found: \n" + interpro);
                }
                //--------------------------------------------------------------
                
                //----getting proteins associated-----                
                Iterator<Relationship> iterator = interpro.getNode().getRelationships(new ProteinInterproRel(null), Direction.INCOMING).iterator();
                int counter = 0;
                System.out.println("Proteins associated to " + inputSt);
                while(iterator.hasNext() && counter <= 20){
                    System.out.println(new ProteinNode(iterator.next().getStartNode()).getAccession());
                    counter++;                    
                }
                if(counter >= 20){
                    System.out.println("These are the first 20 proteins found! ;)");
                }
               

            } catch (Exception e) {
                //deal somehow with the exception
            } finally {

                //---closing the manager----
                manager.shutDown();

            }

        }
    }
}
