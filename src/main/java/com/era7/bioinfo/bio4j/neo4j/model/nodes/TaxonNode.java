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

import com.era7.bioinfo.bio4j.neo4j.model.relationships.TaxonParentRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Uniprot taxonomy taxon
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class TaxonNode extends BasicEntity{

    public static final String TAXON_NAME_INDEX = "taxon_name_index";

    public static final String NODE_TYPE = TaxonNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "taxon_name";


    public TaxonNode(Node n){
        super(n);
    }


    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}


    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}

    /**
     * 
     * @return 
     */
    public TaxonNode getParent(){
        TaxonNode parent = null;
        
        Iterator<Relationship> iterator = this.getNode().getRelationships(new TaxonParentRel(null), Direction.INCOMING).iterator();
        if(iterator.hasNext()){
            parent = new TaxonNode(iterator.next().getStartNode());
        }
        
        return parent;
    }
    
    /**
     * 
     * @return 
     */
    public List<TaxonNode> getChildren(){
        List<TaxonNode> list = new ArrayList<TaxonNode>();
        
        Iterator<Relationship> iterator = this.getNode().getRelationships(new TaxonParentRel(null), Direction.OUTGOING).iterator();
        
        while(iterator.hasNext()){
            Node tempNode = iterator.next().getEndNode();
            if(tempNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(TaxonNode.NODE_TYPE)){
                list.add(new TaxonNode(tempNode));
            }           
        }
        
        return list;
    }
    
    /**
     * 
     * @return 
     */
    public List<OrganismNode> getOrganisms(){
        List<OrganismNode> list = new ArrayList<OrganismNode>();
        
        Iterator<Relationship> iterator = this.getNode().getRelationships(new TaxonParentRel(null), Direction.OUTGOING).iterator();
        
        while(iterator.hasNext()){
            Node tempNode = iterator.next().getEndNode();            
            if(tempNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(OrganismNode.NODE_TYPE)){
                list.add(new OrganismNode(tempNode));
            }           
        }
        
        return list;
    }
    

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaxonNode){
            TaxonNode other = (TaxonNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "name = " + getName();
    }

}
