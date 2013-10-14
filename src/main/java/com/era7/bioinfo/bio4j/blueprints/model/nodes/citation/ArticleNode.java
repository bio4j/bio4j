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
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleJournalRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.model.nodes.Consortium;
import com.era7.bioinfo.bio4j.model.nodes.Person;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * The reference information for a journal citation includes the journal abbreviation, the volume number,
 * the page range and the year of publication.<p/>
 * Journal names are abbreviated according to the conventions used by the National Library of Medicine (NLM)
 * and are based on the existing ISO and ANSI standards.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ArticleNode extends BasicVertex implements Article{

    public static final String NODE_TYPE = ArticleNode.class.getCanonicalName();

    /** Article title */
    public static final String TITLE_PROPERTY = "article_title";
    /** Article PubMed Id (if known)*/
    public static final String PUBMED_ID_PROPERTY = "pubmed_id";
    /** Article MedLine Id (if known)*/
    public static final String MEDLINE_ID_PROPERTY = "medline_id";
    /** Article DOI Id (if known)*/
    public static final String DOI_ID_PROPERTY = "doi_id";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "journal article";


    public ArticleNode(Vertex v){
        super(v);
    }


    @Override
    public String getTitle(){    return String.valueOf(vertex.getProperty(TITLE_PROPERTY));}
    @Override
    public String getPubmedId(){    return String.valueOf(vertex.getProperty(PUBMED_ID_PROPERTY));}
    @Override
    public String getMedlineId(){    return String.valueOf(vertex.getProperty(MEDLINE_ID_PROPERTY));}
    @Override
    public String getDoiId(){    return String.valueOf(vertex.getProperty(DOI_ID_PROPERTY));}


    @Override
    public void setTitle(String value){  vertex.setProperty(TITLE_PROPERTY, value);}
    @Override
    public void setPubmedId(String value){  vertex.setProperty(PUBMED_ID_PROPERTY, value);}
    @Override
    public void setMedlineId(String value){  vertex.setProperty(MEDLINE_ID_PROPERTY, value);}
    @Override
    public void setDoiId(String value){  vertex.setProperty(DOI_ID_PROPERTY, value);}


    
    @Override
    public List<Protein> getProteinCitations(){
        List<Protein> list = new LinkedList<Protein>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ArticleProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }
    /**
     * gets the article journal
     * @return 
     */
    @Override
    public JournalNode getJournal(){
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ArticleJournalRel.NAME).iterator();
        if(iterator.hasNext()){
            return new JournalNode(iterator.next());
        }else{
            return null;
        }
    }
    /**
     * gets consortium authors (if any) of the article
     * @return 
     */
    @Override
    public List<Consortium> getConsortiumAuthors(){
        List<Consortium> list = new LinkedList<Consortium>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ArticleAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            Vertex currentNode = iterator.next();
            if(currentNode.getProperty(BasicVertex.NODE_TYPE_PROPERTY).equals(ConsortiumNode.NODE_TYPE)){
                list.add(new ConsortiumNode(currentNode));
            } 
        }
        return list;
    }
    /**
     * gets person authors (if any) of the article
     * @return 
     */
    @Override
    public List<Person> getPersonAuthors(){
        List<Person> list = new LinkedList<Person>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ArticleAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            Vertex currentNode = iterator.next();
            if(currentNode.getProperty(BasicVertex.NODE_TYPE_PROPERTY).equals(PersonNode.NODE_TYPE)){
                list.add(new PersonNode(currentNode));
            } 
        }
        return list;
    }

    @Override
    public String toString(){
        return "title = " + getTitle() + "\n" +
                "pubmed id = " + getPubmedId() + "\n" +
                "medline id = " + getMedlineId() + "\n" +
                "doi id = " + getDoiId();
    }

}