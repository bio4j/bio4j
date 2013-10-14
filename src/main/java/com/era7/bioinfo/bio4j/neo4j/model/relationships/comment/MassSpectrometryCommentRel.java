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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.comment;

import org.neo4j.graphdb.Relationship;

/**
 * Information derived from mass spectrometry experiments
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class MassSpectrometryCommentRel extends BasicCommentRel{

    public static final String RELATIONSHIP_NAME = "COMMENT_MASS_SPECTROMETRY";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "mass spectrometry";

    public static final String BEGIN_PROPERTY = "begin";
    public static final String END_PROPERTY = "end";
    public static final String MASS_PROPERTY = "mass";
    public static final String METHOD_PROPERTY = "method";

    public MassSpectrometryCommentRel(Relationship rel){
        super(rel);
    }

    public String getBegin(){
        return String.valueOf(this.relationship.getProperty(BEGIN_PROPERTY));
    }
    public String getEnd(){
        return String.valueOf(this.relationship.getProperty(END_PROPERTY));
    }
    public String getMass(){
        return String.valueOf(this.relationship.getProperty(MASS_PROPERTY));
    }
    public String getMethod(){
        return String.valueOf(this.relationship.getProperty(METHOD_PROPERTY));
    }

    public void setBegin(String value){
        this.relationship.setProperty(BEGIN_PROPERTY, value);
    }
    public void setEnd(String value){
        this.relationship.setProperty(END_PROPERTY, value);
    }
    public void setMass(String value){
        this.relationship.setProperty(MASS_PROPERTY, value);
    }
    public void setMethod(String value){
        this.relationship.setProperty(METHOD_PROPERTY, value);
    }

    @Override
    public String name() {
        return RELATIONSHIP_NAME;
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
