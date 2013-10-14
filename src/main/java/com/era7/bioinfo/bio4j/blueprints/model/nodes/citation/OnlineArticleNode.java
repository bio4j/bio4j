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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ConsortiumNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleJournalRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.model.nodes.Consortium;
import com.era7.bioinfo.bio4j.model.nodes.Person;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineArticle;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineJournal;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The reference information for a online article citation includes the online journal,
 * the locator and the title, (plus author list information).
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OnlineArticleNode extends BasicVertex implements OnlineArticle{

    public static final String NODE_TYPE = OnlineArticleNode.class.getCanonicalName();


    /** Online article title **/
    public static final String TITLE_PROPERTY = "online_article_title";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "online journal article";


    public OnlineArticleNode(Vertex v){
        super(v);
    }


    @Override
    public String getTitle(){    return String.valueOf(vertex.getProperty(TITLE_PROPERTY));}

    @Override
    public void setTitle(String value){  vertex.setProperty(TITLE_PROPERTY, value);}
    
    
    /**
     * Gets the online journal where the online article was submitted
     * @return 
     */
    @Override
    public OnlineJournal getOnlineJournal(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, OnlineArticleJournalRel.NAME).iterator();
        if(iterator.hasNext()){
            return new OnlineJournalNode(iterator.next());
        }else{
            return null;
        }
    }
    
    /**
     * gets consortium authors (if any) of the online article
     * @return 
     */
    @Override
    public List<Consortium> getConsortiumAuthors(){
        List<Consortium> list = new LinkedList<Consortium>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, OnlineArticleAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            Vertex currentNode = iterator.next();
            if (currentNode.getProperty(BasicVertex.NODE_TYPE_PROPERTY).equals(ConsortiumNode.NODE_TYPE)) {
                list.add(new ConsortiumNode(currentNode));
            }           
        }
        return list;
    }
    /**
     * gets person authors (if any) of the online article
     * @return 
     */
    @Override
    public List<Person> getPersonAuthors(){
        List<Person> list = new LinkedList<Person>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, OnlineArticleAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            Vertex currentNode = iterator.next();
            if(currentNode.getProperty(BasicVertex.NODE_TYPE_PROPERTY).equals(PersonNode.NODE_TYPE)){
                list.add(new PersonNode(currentNode));
            } 
        }         
        return list;
    }
    
    @Override
    public List<Protein> getProteinCitations(){
        List<Protein> list = new LinkedList<Protein>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, OnlineArticleProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }

    @Override
    public String toString(){
        return "title = " + getTitle();
    }

}
