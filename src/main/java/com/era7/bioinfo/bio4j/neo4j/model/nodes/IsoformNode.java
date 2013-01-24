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

import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import org.neo4j.graphdb.Node;

/**
 * Protein isoforms. Their information is retrieved from entries' binary interactions.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IsoformNode extends BasicEntity{

    public static final String ISOFORM_ID_INDEX = "isoform_id_index";
    public static final String NODE_TYPE = IsoformNode.class.getCanonicalName();

    /** Isoform id **/
    public static final String ID_PROPERTY = "isoform_id";
    /** Isoform name **/
    public static final String NAME_PROPERTY = "isoform_name";
    /** Isoform note **/
    public static final String NOTE_PROPERTY = "isoform_note";
    /** Isoform sequence **/
    public static final String SEQUENCE_PROPERTY = "isoform_sequence";


    public IsoformNode(Node n){
        super(n);
    }


    public String getId(){  return String.valueOf(node.getProperty(ID_PROPERTY));}
    public String getNote(){    return String.valueOf(node.getProperty(NOTE_PROPERTY));}
    public String getName() {        return String.valueOf(node.getProperty(NAME_PROPERTY)); }
    public String getSequence() {        return String.valueOf(node.getProperty(SEQUENCE_PROPERTY));    }


    public void setId(String value){    node.setProperty(ID_PROPERTY, value);}
    public void setNote(String value){  node.setProperty(NOTE_PROPERTY, value);}
    public void setName(String value) {        node.setProperty(NAME_PROPERTY, value);    }
    public void setSequence(String value) {      node.setProperty(SEQUENCE_PROPERTY, value);   }


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IsoformNode){
            IsoformNode other = (IsoformNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "id = " + getId() + "\n" +
                "note = " + getNote();
    }

}
