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
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.onarticle.OnlineArticleAuthorRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.onarticle.OnlineArticleJournalRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.onarticle.OnlineArticleProteinCitationRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * The reference information for a online article citation includes the online journal,
 * the locator and the title, (plus author list information).
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class OnlineArticleNode extends BasicEntity{

    public static final String ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX = "online_article_title_full_text_index";

    public static final String NODE_TYPE = OnlineArticleNode.class.getCanonicalName();


    /** Online article title **/
    public static final String TITLE_PROPERTY = "online_article_title";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "online journal article";


    public OnlineArticleNode(Node n){
        super(n);
    }


    public String getTitle(){    return String.valueOf(node.getProperty(TITLE_PROPERTY));}

    public void setTitle(String value){  node.setProperty(TITLE_PROPERTY, value);}
    
    
    /**
     * Gets the online journal where the online article was submitted
     * @return 
     */
    public OnlineJournalNode getOnlineJournal(){
        Iterator<Relationship> iterator = this.node.getRelationships(new OnlineArticleJournalRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new OnlineJournalNode(iterator.next().getEndNode());
        }else{
            return null;
        }
    }
    
    /**
     * gets consortium authors (if any) of the online article
     * @return 
     */
    public List<ConsortiumNode> getConsortiumAuthors(){
        List<ConsortiumNode> list = new ArrayList<ConsortiumNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new OnlineArticleAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            Node currentNode = iterator.next().getEndNode();
            if (currentNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(ConsortiumNode.NODE_TYPE)) {
                list.add(new ConsortiumNode(currentNode));
            }           
        }
        return list;
    }
    /**
     * gets person authors (if any) of the online article
     * @return 
     */
    public List<PersonNode> getPersonAuthors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new OnlineArticleAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            Node currentNode = iterator.next().getEndNode();
            if(currentNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(PersonNode.NODE_TYPE)){
                list.add(new PersonNode(currentNode));
            } 
        }         
        return list;
    }
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = node.getRelationships(new OnlineArticleProteinCitationRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next().getEndNode()));
        }
        return list;
    }


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OnlineArticleNode){
            OnlineArticleNode other = (OnlineArticleNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "title = " + getTitle();
    }

}
