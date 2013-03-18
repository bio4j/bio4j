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
package com.era7.bioinfo.bio4j.blueprints.model.nodes.citation;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicVertex;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleJournalRel;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.era7.bioinfo.bio4j.model.nodes.citation.Journal;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Journals where article citations are published
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class JournalNode extends BasicVertex implements Journal{

    public static final String NODE_TYPE = JournalNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "journal_name";


    public JournalNode(Vertex v){
        super(v);
    }


    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}


    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}
    
    
    @Override
    public List<Article> getArticles(){
        List<Article> list = new LinkedList<Article>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ArticleJournalRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ArticleNode(iterator.next()));
        }
        return list;
    }


    @Override
    public String toString(){
        return "name = " + getName();
    }

}
