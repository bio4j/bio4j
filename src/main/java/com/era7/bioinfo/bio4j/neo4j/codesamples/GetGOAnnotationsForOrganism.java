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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.OrganismNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.bioinfo.bio4j.neo4j.model.util.GoUtil;
import com.era7.bioinfo.bio4j.neo4j.model.util.NodeRetriever;
import com.era7.lib.bioinfoxml.go.GoAnnotationXML;
import com.era7.lib.bioinfoxml.uniprot.ProteinXML;
import com.era7.lib.era7xmlapi.util.XMLUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GetGOAnnotationsForOrganism {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("The program expects the following parameters: \n"
                    + "1. Bio4j DB folder\n"
                    + "2. Scientific name (Uniprot taxonomy)\n"
                    + "3. Output XML filename");
        } else {


            Bio4jManager manager = null;

            try {

                //--creating manager and node retriever----
                System.out.println("Creating manager...");
                manager = new Bio4jManager(args[0]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                File outFile = new File(args[2]);
                BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

                System.out.println("Getting organism...");
                OrganismNode organism = nodeRetriever.getOrganismByScientificName(args[1]);
                
                System.out.println("Organism found, ID: " + organism.getNcbiTaxonomyId());

                ArrayList<ProteinXML> proteins = new ArrayList<ProteinXML>();

                System.out.println("Getting proteins...");
                for (ProteinNode proteinNode : organism.getAssociatedProteins()) {
                    ProteinXML proteinXML = new ProteinXML();
                    proteinXML.setId(proteinNode.getAccession());
                    proteins.add(proteinXML);
                }

                System.out.println("Looking for GO annotations...");
                GoAnnotationXML goAnnotationXML = GoUtil.getGoAnnotation(proteins, manager);

                writer.write(XMLUtil.prettyPrintXML(goAnnotationXML.toString(), 3));

                writer.close();
                
                System.out.println("Done! :)");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                //---closing the manager----
                manager.shutDown();

            }

        }
    }
}
