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

package com.era7.bioinfo.bio4j.neo4j.model.nodes.reactome;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinReactomeRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Gene ontology term
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ReactomeTermNode extends BasicEntity{

    public static final String REACTOME_TERM_ID_INDEX = "reactome_term_id_index";
    public static final String NODE_TYPE = ReactomeTermNode.class.getCanonicalName();

    /** Reactome Term id **/
    public static final String ID_PROPERTY = "reactome_term_id";
    /** Reactome term pathway name **/
    public static final String PATHWAY_NAME_PROPERTY = "reactome_term_pathway_name";


    public ReactomeTermNode(Node n){
        super(n);
    }


    public String getId(){  return String.valueOf(node.getProperty(ID_PROPERTY));}
    public String getPathwayName(){    return String.valueOf(node.getProperty(PATHWAY_NAME_PROPERTY));}


    public void setId(String value){    node.setProperty(ID_PROPERTY, value);}
    public void setPathwayName(String value){  node.setProperty(PATHWAY_NAME_PROPERTY, value);}
  
    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new LinkedList<ProteinNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinReactomeRel(null), Direction.INCOMING).iterator();
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
        if(obj instanceof ReactomeTermNode){
            ReactomeTermNode other = (ReactomeTermNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "id = " + getId() +
                "\npathway name = " + getPathwayName();
    }

}
