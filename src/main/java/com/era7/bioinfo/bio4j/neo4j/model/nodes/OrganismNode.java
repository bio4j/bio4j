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

package com.era7.bioinfo.bio4j.neo4j.model.nodes;

import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinOrganismRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Uniprot taxonomy organism
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OrganismNode extends BasicEntity{

    public static final String ORGANISM_SCIENTIFIC_NAME_INDEX = "organism_scientific_name_index";
    public static final String ORGANISM_NCBI_TAXONOMY_ID_INDEX = "organism_ncbi_taxonomy_id_index";

    public static final String NODE_TYPE = OrganismNode.class.getCanonicalName();

    public static final String SCIENTIFIC_NAME_PROPERTY = "organism_scientific_name";
    public static final String COMMON_NAME_PROPERTY = "organism_common_name";
    public static final String SYNONYM_NAME_PROPERTY = "organism_synonym_name";
    public static final String NCBI_TAXONOMY_ID_PROPERTY = "organism_ncbi_taxonomy_id";


    public OrganismNode(Node n){
        super(n);
    }


    public String getScientificName(){    return String.valueOf(node.getProperty(SCIENTIFIC_NAME_PROPERTY));}
    public String getCommonName(){    return String.valueOf(node.getProperty(COMMON_NAME_PROPERTY));}
    public String getSynonymName(){    return String.valueOf(node.getProperty(SYNONYM_NAME_PROPERTY));}
    public String getNcbiTaxonomyId(){    return String.valueOf(node.getProperty(NCBI_TAXONOMY_ID_PROPERTY));}

    public void setScientificName(String value){  node.setProperty(SCIENTIFIC_NAME_PROPERTY, value);}
    public void setCommonName(String value){  node.setProperty(COMMON_NAME_PROPERTY, value);}
    public void setSynonymName(String value){  node.setProperty(SYNONYM_NAME_PROPERTY, value);}
    public void setNcbiTaxonomyId(String value){    node.setProperty(NCBI_TAXONOMY_ID_PROPERTY, value);}
    
    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new LinkedList<ProteinNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinOrganismRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next().getStartNode());
            proteins.add(protein);                        
        }
        return proteins;  
    }
    
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OrganismNode){
            OrganismNode other = (OrganismNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

}
