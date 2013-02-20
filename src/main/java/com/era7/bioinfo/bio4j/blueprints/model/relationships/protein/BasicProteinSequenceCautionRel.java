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

package com.era7.bioinfo.bio4j.blueprints.model.relationships.protein;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationship;
import com.tinkerpop.blueprints.Edge;

/**
 *
 * @author ppareja
 */
public class BasicProteinSequenceCautionRel extends BasicRelationship{

    public static final String NAME = "BASIC_PROTEIN_SEQUENCE_CAUTION";

    public static final String TEXT_PROPERTY = "protein_sequence_caution_text";
    public static final String STATUS_PROPERTY = "protein_sequence_caution_status";
    public static final String EVIDENCE_PROPERTY = "protein_sequence_caution_evidence";
    public static final String ID_PROPERTY = "protein_sequence_caution_id";
    public static final String POSITION_PROPERTY = "protein_sequence_caution_position";
    public static final String RESOURCE_PROPERTY = "protein_sequence_caution_resource";
    public static final String VERSION_PROPERTY = "protein_sequence_caution_version";


    public BasicProteinSequenceCautionRel(Edge e){
        super(e);
    }

    @Override
    public String getLabel() {
        return NAME;
    }

    public String getText(){
        return String.valueOf(edge.getProperty(TEXT_PROPERTY));
    }
    public String getStatus(){
        return String.valueOf(edge.getProperty(STATUS_PROPERTY));
    }
    public String getEvidence(){
        return String.valueOf(edge.getProperty(EVIDENCE_PROPERTY));
    }
    public String getId(){
        return String.valueOf(edge.getProperty(ID_PROPERTY));
    }
    public String getPosition(){
        return String.valueOf(edge.getProperty(POSITION_PROPERTY));
    }
    public String getResource(){
        return String.valueOf(edge.getProperty(RESOURCE_PROPERTY));
    }
    public String getVersion(){
        return String.valueOf(edge.getProperty(VERSION_PROPERTY));
    }

    public void setText(String value){
        edge.setProperty(TEXT_PROPERTY, value);
    }
    public void setStatus(String value){
        edge.setProperty(STATUS_PROPERTY, value);
    }
    public void setEvidence(String value){
        edge.setProperty(EVIDENCE_PROPERTY, value);
    }
    public void setId(String value){
        edge.setProperty(ID_PROPERTY, value);
    }
    public void setPosition(String value){
        edge.setProperty(POSITION_PROPERTY, value);
    }
    public void setResource(String value){
        edge.setProperty(RESOURCE_PROPERTY, value);
    }
    public void setVersion(String value){
        edge.setProperty(VERSION_PROPERTY, value);
    }

    @Override
    public String toString(){
        return "text = " + getText() + "\n" +
                "status = " + getStatus() + "\n" +
                "evidence = " + getEvidence() + "\n" +
                "id = " + getId() + "\n" +
                "position = " + getPosition() + "\n" +
                "resource = " + getResource() + "\n" +
                "version = " + getVersion();
    }

}