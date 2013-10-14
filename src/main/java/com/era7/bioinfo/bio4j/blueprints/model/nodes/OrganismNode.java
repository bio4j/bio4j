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

package com.era7.bioinfo.bio4j.blueprints.model.nodes;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinOrganismRel;
import com.era7.bioinfo.bio4j.model.nodes.Organism;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Uniprot taxonomy organism
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OrganismNode extends BasicVertex implements Organism{

    public static final String NODE_TYPE = OrganismNode.class.getCanonicalName();

    public static final String SCIENTIFIC_NAME_PROPERTY = "organism_scientific_name";
    public static final String COMMON_NAME_PROPERTY = "organism_common_name";
    public static final String SYNONYM_NAME_PROPERTY = "organism_synonym_name";
    public static final String NCBI_TAXONOMY_ID_PROPERTY = "organism_ncbi_taxonomy_id";


    public OrganismNode(Vertex v){
        super(v);
    }


    @Override
    public String getScientificName(){    return String.valueOf(vertex.getProperty(SCIENTIFIC_NAME_PROPERTY));}
    @Override
    public String getCommonName(){    return String.valueOf(vertex.getProperty(COMMON_NAME_PROPERTY));}
    @Override
    public String getSynonymName(){    return String.valueOf(vertex.getProperty(SYNONYM_NAME_PROPERTY));}
    @Override
    public String getNcbiTaxonomyId(){    return String.valueOf(vertex.getProperty(NCBI_TAXONOMY_ID_PROPERTY));}

    @Override
    public void setScientificName(String value){  vertex.setProperty(SCIENTIFIC_NAME_PROPERTY, value);}
    @Override
    public void setCommonName(String value){  vertex.setProperty(COMMON_NAME_PROPERTY, value);}
    @Override
    public void setSynonymName(String value){  vertex.setProperty(SYNONYM_NAME_PROPERTY, value);}
    @Override
    public void setNcbiTaxonomyId(String value){    vertex.setProperty(NCBI_TAXONOMY_ID_PROPERTY, value);}
    
    
    @Override
    public List<Protein> getAssociatedProteins(){
        List<Protein> proteins = new LinkedList<Protein>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinOrganismRel.NAME).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next());
            proteins.add(protein);                        
        }
        return proteins;  
    }
    
}
