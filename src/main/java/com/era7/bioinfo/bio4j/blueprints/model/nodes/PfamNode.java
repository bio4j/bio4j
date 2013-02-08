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

import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinPfamRel;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Pfam family
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PfamNode extends BasicVertex{

    public static final String NODE_TYPE = PfamNode.class.getCanonicalName();

    /** Pfam family id **/
    public static final String ID_PROPERTY = "pfam_id";
    /** Pfam family name **/
    public static final String NAME_PROPERTY = "pfam_name";


    public PfamNode(Vertex v){
        super(v);
    }


    public String getId(){  return String.valueOf(vertex.getProperty(ID_PROPERTY));}
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}


    public void setId(String value){    vertex.setProperty(ID_PROPERTY, value);}
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}


    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new ArrayList<ProteinNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinPfamRel.NAME).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next());
            proteins.add(protein);                        
        }
        return proteins;  
    }

    @Override
    public String toString(){
        return "id = " + getId() + "\n" +
                "name = " + getName();
    }

}
