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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.onarticle;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.OnlineArticleNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.OnlineJournalNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * Online journal where an online article is published
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OnlineArticleJournalRel extends BasicRelationship{

    public static final String NAME = "ONLINE_ARTICLE_JOURNAL";

    public static final String LOCATOR_PROPERTY = "locator";

    public OnlineArticleJournalRel(Relationship rel){
        super(rel);
    }


    public String getLocator(){    return String.valueOf(this.relationship.getProperty(LOCATOR_PROPERTY));}

    public void setLocator(String value){  this.relationship.setProperty(LOCATOR_PROPERTY, value);}
    
    public OnlineArticleNode getOnlineArticle(){
        return new OnlineArticleNode(getStartNode());
    }
    
    public OnlineJournalNode getOnlineJournal(){
        return new OnlineJournalNode(getEndNode());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString(){
        return "locator = " + getLocator();
    }

}