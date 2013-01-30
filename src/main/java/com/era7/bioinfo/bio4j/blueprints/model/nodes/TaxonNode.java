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

import com.era7.bioinfo.bio4j.blueprints.model.relationships.TaxonParentRel;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Uniprot taxonomy taxon
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class TaxonNode extends BasicNode{

    public static final String NODE_TYPE = TaxonNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "taxon_name";


    public TaxonNode(Vertex v){
        super(v);
    }


    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}


    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}

    /**
     * 
     * @return 
     */
    public TaxonNode getParent(){
        TaxonNode parent = null;
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, TaxonParentRel.NAME).iterator();
        if(iterator.hasNext()){
            parent = new TaxonNode(iterator.next());
        }
        
        return parent;
    }
    
    /**
     * 
     * @return 
     */
    public List<TaxonNode> getChildren(){
        List<TaxonNode> list = new ArrayList<TaxonNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, TaxonParentRel.NAME).iterator();
        
        while(iterator.hasNext()){
            Vertex tempNode = iterator.next();
            if(tempNode.getProperty(BasicNode.NODE_TYPE_PROPERTY).equals(TaxonNode.NODE_TYPE)){
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
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, TaxonParentRel.NAME).iterator();
        
        while(iterator.hasNext()){
            Vertex tempNode = iterator.next();            
            if(tempNode.getProperty(BasicNode.NODE_TYPE_PROPERTY).equals(OrganismNode.NODE_TYPE)){
                list.add(new OrganismNode(tempNode));
            }           
        }
        
        return list;
    }
    
    @Override
    public String toString(){
        return "name = " + getName();
    }

}
