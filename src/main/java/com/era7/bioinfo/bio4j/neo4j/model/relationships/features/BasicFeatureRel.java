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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.features;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.FeatureTypeNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * Basic feature relationship
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public abstract class BasicFeatureRel extends BasicRelationship{

    public static final String NAME = "BASIC_FEATURE";

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ID_PROPERTY = "id";
    public static final String STATUS_PROPERTY = "status";
    public static final String EVIDENCE_PROPERTY = "evidence";
    public static final String BEGIN_PROPERTY = "begin";
    public static final String END_PROPERTY = "end";
    public static final String ORIGINAL_PROPERTY = "original";
    public static final String VARIATION_PROPERTY = "variation";
    public static final String REF_PROPERTY = "ref";
    

    public BasicFeatureRel(Relationship rel){
        super(rel);
    }

    @Override
    public String name() {
        return NAME;
    }

    public String getDescription(){
        return String.valueOf(this.relationship.getProperty(DESCRIPTION_PROPERTY));
    }
    public String getStatus(){
        return String.valueOf(this.relationship.getProperty(STATUS_PROPERTY));
    }
    public String getEvidence(){
        return String.valueOf(this.relationship.getProperty(EVIDENCE_PROPERTY));
    }
    public String getId(){
        return String.valueOf(this.relationship.getProperty(ID_PROPERTY));
    }
    public String getOriginal(){
        return String.valueOf(this.relationship.getProperty(ORIGINAL_PROPERTY));
    }
    public String getVariation(){
        return String.valueOf(this.relationship.getProperty(VARIATION_PROPERTY));
    }
    public String getRef(){
        return String.valueOf(this.relationship.getProperty(REF_PROPERTY));
    }
    public String getBegin(){
        return String.valueOf(this.relationship.getProperty(BEGIN_PROPERTY));
    }
    public String getEnd(){
        return String.valueOf(this.relationship.getProperty(END_PROPERTY));
    }    

    public FeatureTypeNode getFeatureType(){
        return new FeatureTypeNode(relationship.getEndNode());
    }

    public void setDescription(String value){
        relationship.setProperty(DESCRIPTION_PROPERTY, value);
    }
    public void setId(String value){
        relationship.setProperty(ID_PROPERTY, value);
    }
    public void setEvidence(String value){
        relationship.setProperty(EVIDENCE_PROPERTY, value);
    }
    public void setStatus(String value){
        relationship.setProperty(STATUS_PROPERTY, value);
    }
    public void setRef(String value){
        relationship.setProperty(REF_PROPERTY,value);
    }
    public void setBegin(int value){
        relationship.setProperty(BEGIN_PROPERTY, String.valueOf(value));
    }
    public void setEnd(int value){
        relationship.setProperty(END_PROPERTY, String.valueOf(value));
    }
    public void setOriginal(String value){
        this.relationship.setProperty(ORIGINAL_PROPERTY, value);
    }
    public void setVariation(String value){
        this.relationship.setProperty(VARIATION_PROPERTY, value);
    }

    @Override
    public String toString(){
        return "description = " + getDescription() + "\n" +
                "status = " + getStatus() + "\n" +
                "evidence = " + getEvidence() + "\n" +
                "id = " + getId() + "\n" +
                "begin = " + getBegin() + "\n" +
                "end = " + getEnd() + "\n" +
                "ref = " + getRef() + "\n" +
                "original = " + getOriginal() + "\n" +
                "variation = " + getVariation();
    }

}
