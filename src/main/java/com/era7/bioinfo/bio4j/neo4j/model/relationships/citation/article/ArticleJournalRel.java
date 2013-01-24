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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.article;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.ArticleNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.JournalNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * Article journal
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ArticleJournalRel extends BasicRelationship{

    public static final String NAME = "ARTICLE_JOURNAL";

    public static final String DATE_PROPERTY = "date";
    public static final String VOLUME_PROPERTY = "volume";
    public static final String FIRST_PROPERTY = "first";
    public static final String LAST_PROPERTY = "last";

    public ArticleJournalRel(Relationship rel){
        super(rel);
    }

    public String getDate(){    return String.valueOf(this.relationship.getProperty(DATE_PROPERTY));}
    public String getVolume(){    return String.valueOf(this.relationship.getProperty(VOLUME_PROPERTY));}
    public String getFirst(){    return String.valueOf(this.relationship.getProperty(FIRST_PROPERTY));}
    public String getLast(){    return String.valueOf(this.relationship.getProperty(LAST_PROPERTY));}

    public void setDate(String value){  this.relationship.setProperty(DATE_PROPERTY, value);}
    public void setVolume(String value){  this.relationship.setProperty(VOLUME_PROPERTY, value);}
    public void setFirst(String value){  this.relationship.setProperty(FIRST_PROPERTY, value);}
    public void setLast(String value){  this.relationship.setProperty(LAST_PROPERTY, value);}

    public ArticleNode getArticle(){
        return new ArticleNode(getStartNode());
    }
    
    public JournalNode getJournal(){
        return new JournalNode(getEndNode());
    }
    

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString(){
        return "date = " + getDate() + "\n" +
                "volume = " + getVolume() + "\n" +
                "first = " + getFirst() + "\n" +
                "last = " + getLast();
    }

}