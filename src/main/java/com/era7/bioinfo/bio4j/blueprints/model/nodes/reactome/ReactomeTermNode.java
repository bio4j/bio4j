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

package com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicVertex;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinReactomeRel;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTerm;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Gene ontology term
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ReactomeTermNode extends BasicVertex implements ReactomeTerm{

    public static final String NODE_TYPE = ReactomeTermNode.class.getCanonicalName();

    /** Reactome Term id **/
    public static final String ID_PROPERTY = "reactome_term_id";
    /** Reactome term pathway name **/
    public static final String PATHWAY_NAME_PROPERTY = "reactome_term_pathway_name";


    public ReactomeTermNode(Vertex v){
        super(v);
    }


    @Override
    public String getId(){  return String.valueOf(vertex.getProperty(ID_PROPERTY));}
    @Override
    public String getPathwayName(){    return String.valueOf(vertex.getProperty(PATHWAY_NAME_PROPERTY));}


    @Override
    public void setId(String value){    vertex.setProperty(ID_PROPERTY, value);}
    @Override
    public void setPathwayName(String value){  vertex.setProperty(PATHWAY_NAME_PROPERTY, value);}
  
    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new LinkedList<ProteinNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinReactomeRel.NAME).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next());
            proteins.add(protein);                        
        }
        return proteins;  
    }
      
    @Override
    public String toString(){
        return "id = " + getId() +
                "\npathway name = " + getPathwayName();
    }

}
