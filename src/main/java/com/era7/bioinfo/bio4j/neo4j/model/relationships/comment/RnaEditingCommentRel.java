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
 * Amino acid variation(s) due to RNA editing
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class RnaEditingCommentRel extends BasicCommentRel{

    public static final String RELATIONSHIP_NAME = "COMMENT_RNA_EDITING";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "RNA editing";

    public static final String POSITION_PROPERTY = "position";

    public RnaEditingCommentRel(Relationship rel){
        super(rel);
    }

    public String getPosition(){
        return String.valueOf(this.relationship.getProperty(POSITION_PROPERTY));
    }
    public void setPosition(String value){
        this.relationship.setProperty(POSITION_PROPERTY, value);
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
