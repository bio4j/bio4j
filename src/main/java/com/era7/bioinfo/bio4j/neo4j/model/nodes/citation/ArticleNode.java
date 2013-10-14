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
package com.era7.bioinfo.bio4j.neo4j.model.nodes.citation;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ConsortiumNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.article.ArticleAuthorRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.article.ArticleJournalRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.article.ArticleProteinCitationRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * The reference information for a journal citation includes the journal abbreviation, the volume number,
 * the page range and the year of publication.<p/>
 * Journal names are abbreviated according to the conventions used by the National Library of Medicine (NLM)
 * and are based on the existing ISO and ANSI standards.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ArticleNode extends BasicEntity{

    public static final String ARTICLE_TITLE_FULL_TEXT_INDEX = "article_title_full_text_index";

    public static final String NODE_TYPE = ArticleNode.class.getCanonicalName();

    public static final String ARTICLE_PUBMED_ID_INDEX = "article_pubmed_id_index";
    public static final String ARTICLE_MEDLINE_ID_INDEX = "article_medline_id_index";
    public static final String ARTICLE_DOI_ID_INDEX = "article_doi_id_index";

    /** Article title */
    public static final String TITLE_PROPERTY = "article_title";
    /** Article PubMed Id (if known)*/
    public static final String PUBMED_ID_PROPERTY = "pubmed_id";
    /** Article MedLine Id (if known)*/
    public static final String MEDLINE_ID_PROPERTY = "medline_id";
    /** Article DOI Id (if known)*/
    public static final String DOI_ID_PROPERTY = "doi_id";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "journal article";


    public ArticleNode(Node n){
        super(n);
    }


    public String getTitle(){    return String.valueOf(node.getProperty(TITLE_PROPERTY));}
    public String getPubmedId(){    return String.valueOf(node.getProperty(PUBMED_ID_PROPERTY));}
    public String getMedlineId(){    return String.valueOf(node.getProperty(MEDLINE_ID_PROPERTY));}
    public String getDoiId(){    return String.valueOf(node.getProperty(DOI_ID_PROPERTY));}


    public void setTitle(String value){  node.setProperty(TITLE_PROPERTY, value);}
    public void setPubmedId(String value){  node.setProperty(PUBMED_ID_PROPERTY, value);}
    public void setMedlineId(String value){  node.setProperty(MEDLINE_ID_PROPERTY, value);}
    public void setDoiId(String value){  node.setProperty(DOI_ID_PROPERTY, value);}


    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new ArticleProteinCitationRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * gets the article journal
     * @return 
     */
    public JournalNode getJournal(){
        Iterator<Relationship> iterator = this.node.getRelationships(new ArticleJournalRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new JournalNode(iterator.next().getEndNode());
        }else{
            return null;
        }
    }
    /**
     * gets consortium authors (if any) of the article
     * @return 
     */
    public List<ConsortiumNode> getConsortiumAuthors(){
        List<ConsortiumNode> list = new ArrayList<ConsortiumNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new ArticleAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            Node currentNode = iterator.next().getEndNode();
            if(currentNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(ConsortiumNode.NODE_TYPE)){
                list.add(new ConsortiumNode(currentNode));
            } 
        }
        return list;
    }
    /**
     * gets person authors (if any) of the article
     * @return 
     */
    public List<PersonNode> getPersonAuthors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new ArticleAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            Node currentNode = iterator.next().getEndNode();
            if(currentNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(PersonNode.NODE_TYPE)){
                list.add(new PersonNode(currentNode));
            } 
        }
        return list;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ArticleNode){
            ArticleNode other = (ArticleNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "title = " + getTitle() + "\n" +
                "pubmed id = " + getPubmedId() + "\n" +
                "medline id = " + getMedlineId() + "\n" +
                "doi id = " + getDoiId();
    }

}