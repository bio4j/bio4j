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

package com.era7.bioinfo.bio4j.blueprints.model.relationships.comment;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationshipBlueprints;
import com.era7.bioinfo.bio4j.model.relationships.comment.BasicComment;
import com.tinkerpop.blueprints.Edge;

/**
 *
 * @author ppareja
 */
public class BasicCommentRel extends BasicRelationshipBlueprints implements BasicComment{

    public static final String NAME = "BASIC_COMMENT";

    public static final String TEXT_PROPERTY = "comment_text";
    public static final String STATUS_PROPERTY = "comment_status";
    public static final String EVIDENCE_PROPERTY = "comment_evidence";


    public BasicCommentRel(Edge e){
        super(e);
    }

    @Override
    public String getType() {
        return NAME;
    }

    @Override
    public String getText(){
        return String.valueOf(edge.getProperty(TEXT_PROPERTY));
    }
    @Override
    public String getStatus(){
        return String.valueOf(edge.getProperty(STATUS_PROPERTY));
    }
    @Override
    public String getEvidence(){
        return String.valueOf(edge.getProperty(EVIDENCE_PROPERTY));
    }

    @Override
    public void setText(String value){
        edge.setProperty(TEXT_PROPERTY, value);
    }    
    @Override
    public void setStatus(String value){
        edge.setProperty(STATUS_PROPERTY, value);
    }
    @Override
    public void setEvidence(String value){
        edge.setProperty(EVIDENCE_PROPERTY, value);
    }

    @Override
    public String toString(){
        return "text = " + getText() + "\n" +
                "status = " + getStatus();
    }

}

