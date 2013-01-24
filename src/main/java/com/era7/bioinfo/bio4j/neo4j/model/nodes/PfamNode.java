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

import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinPfamRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Pfam family
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PfamNode extends BasicEntity{

    public static final String PFAM_ID_INDEX = "pfam_id_index";
    public static final String NODE_TYPE = PfamNode.class.getCanonicalName();

    /** Pfam family id **/
    public static final String ID_PROPERTY = "pfam_id";
    /** Pfam family name **/
    public static final String NAME_PROPERTY = "pfam_name";


    public PfamNode(Node n){
        super(n);
    }


    public String getId(){  return String.valueOf(node.getProperty(ID_PROPERTY));}
    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}


    public void setId(String value){    node.setProperty(ID_PROPERTY, value);}
    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PfamNode){
            PfamNode other = (PfamNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> proteins = new ArrayList<ProteinNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinPfamRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            ProteinNode protein = new ProteinNode(iterator.next().getStartNode());
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
