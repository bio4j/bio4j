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

import com.tinkerpop.blueprints.Vertex;

/**
 * Protein isoforms. Their information is retrieved from entries' binary interactions.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IsoformNode extends BasicNode{

    public static final String NODE_TYPE = IsoformNode.class.getCanonicalName();

    /** Isoform id **/
    public static final String ID_PROPERTY = "isoform_id";
    /** Isoform name **/
    public static final String NAME_PROPERTY = "isoform_name";
    /** Isoform note **/
    public static final String NOTE_PROPERTY = "isoform_note";
    /** Isoform sequence **/
    public static final String SEQUENCE_PROPERTY = "isoform_sequence";


    public IsoformNode(Vertex v){
        super(v);
    }


    public String getId(){  return String.valueOf(vertex.getProperty(ID_PROPERTY));}
    public String getNote(){    return String.valueOf(vertex.getProperty(NOTE_PROPERTY));}
    public String getName() {        return String.valueOf(vertex.getProperty(NAME_PROPERTY)); }
    public String getSequence() {        return String.valueOf(vertex.getProperty(SEQUENCE_PROPERTY));    }


    public void setId(String value){    vertex.setProperty(ID_PROPERTY, value);}
    public void setNote(String value){  vertex.setProperty(NOTE_PROPERTY, value);}
    public void setName(String value) {        vertex.setProperty(NAME_PROPERTY, value);    }
    public void setSequence(String value) {      vertex.setProperty(SEQUENCE_PROPERTY, value);   }


    @Override
    public String toString(){
        return "id = " + getId() + "\n" +
                "note = " + getNote();
    }

}
