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

package com.era7.bioinfo.bio4j.neo4j.model.nodes.refseq.rna;

import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import org.neo4j.graphdb.Node;

/**
 * Misc RNA
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public abstract class RNANode extends BasicEntity{

    public static final String NOTE_PROPERTY = "note";
    public static final String POSITIONS_PROPERTY = "positions";


    public RNANode(Node n){
        super(n);
    }


    public String getPositions(){   return String.valueOf(node.getProperty(POSITIONS_PROPERTY));}
    public String getNote(){   return String.valueOf(node.getProperty(NOTE_PROPERTY));}


    public void setPositions(String value){ node.setProperty(POSITIONS_PROPERTY, value);}
    public void setNote(String value){ node.setProperty(NOTE_PROPERTY, value);}
    
    
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RNANode){
            RNANode other = (RNANode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

}