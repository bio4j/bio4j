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
package com.era7.bioinfo.bio4j.codesamples;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.util.Bio4jManager;
import com.era7.bioinfo.bio4jmodel.util.NodeRetriever;

/**
 *
 * Retrieving a protein by its accession and accessing some of its data
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class RetrieveProteinSample {  
    /**
     * @param args Just one argument (protein accession)
     */
    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("The program expects one parameter: \n" +
                                "1. Protein accesion\n");
        }else{
            
            String accessionSt = args[0];
            
            //--creating manager and node retriever----
            Bio4jManager manager = new Bio4jManager(CommonData.DATABASE_FOLDER);
            NodeRetriever nodeRetriever = new NodeRetriever(manager);
            
            //---retrieving protein node----
            ProteinNode protein = nodeRetriever.getProteinNodeByAccession(accessionSt);
            
            //---checking there's a protein with the accession provided----
            if(protein == null){
                System.out.println("Error: there's no protein with the accession " + accessionSt);
            }else{                
                System.out.println("Protein data: \n" + protein);                
            }
        }
    }
    
}
