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
 * Expression of the gene product according to the cell stage and/or tissue or organism development
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class DevelopmentalStageCommentRel extends BasicCommentRel{

    public static final String RELATIONSHIP_NAME = "COMMENT_DEVELOPMENTAL_STAGE";
    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "developmental stage";

    public DevelopmentalStageCommentRel(Relationship rel){
        super(rel);
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
