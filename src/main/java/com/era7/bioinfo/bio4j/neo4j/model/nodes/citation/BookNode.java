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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.CityNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.book.*;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * The reference information for articles found in books or other types of publication includes
 * the book name, the volume number, the page range, the publisher, the city and the year.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class BookNode extends BasicEntity{

    public static final String BOOK_NAME_FULL_TEXT_INDEX = "book_name_full_text_index";

    public static final String NODE_TYPE = BookNode.class.getCanonicalName();

    /** Book name **/
    public static final String NAME_PROPERTY = "book_name";
    /** Year **/
    public static final String DATE_PROPERTY = "book_date";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "book";


    public BookNode(Node n){
        super(n);
    }


    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}
    public String getDate(){    return String.valueOf(node.getProperty(DATE_PROPERTY));}


    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}
    public void setDate(String value){  node.setProperty(DATE_PROPERTY, value);}
    
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = node.getRelationships(new BookProteinCitationRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next().getEndNode()));
        }
        return list;
    }
    
    /**
     * Gets the Book publisher
     * @return 
     */
    public PublisherNode getPublisher(){
        Iterator<Relationship> iterator = node.getRelationships(new BookPublisherRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new PublisherNode(iterator.next().getEndNode());
        }else{
            return null;
        }        
    }
    
    /**
     * Gets the city where the book was published
     * @return 
     */
    public CityNode getCity(){
        Iterator<Relationship> iterator = node.getRelationships(new BookCityRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new CityNode(iterator.next().getEndNode());
        }else{
            return null;
        }        
    }
    
    /**
     * gets the authors of the book
     * @return 
     */
    public List<PersonNode> getAuthors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new BookAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next().getEndNode()));
        }
        return list;
    }
    /**
     * gets the editors of the book
     * @return 
     */
    public List<PersonNode> getEditors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new BookEditorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next().getEndNode()));
        }
        return list;
    }


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BookNode){
            BookNode other = (BookNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "name = " + getName() + "\n" +
                "date = " + getDate();
    }

}