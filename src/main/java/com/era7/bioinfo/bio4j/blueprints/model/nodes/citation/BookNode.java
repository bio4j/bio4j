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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.CityNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.book.*;
import com.era7.bioinfo.bio4j.model.nodes.citation.Book;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The reference information for articles found in books or other types of publication includes
 * the book name, the volume number, the page range, the publisher, the city and the year.
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class BookNode extends BasicVertex implements Book{

    public static final String NODE_TYPE = BookNode.class.getCanonicalName();

    /** Book name **/
    public static final String NAME_PROPERTY = "book_name";
    /** Year **/
    public static final String DATE_PROPERTY = "book_date";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "book";


    public BookNode(Vertex v){
        super(v);
    }


    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}
    @Override
    public String getDate(){    return String.valueOf(vertex.getProperty(DATE_PROPERTY));}


    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}
    @Override
    public void setDate(String value){  vertex.setProperty(DATE_PROPERTY, value);}
    
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, BookProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }
    
    /**
     * Gets the Book publisher
     * @return 
     */
    @Override
    public PublisherNode getPublisher(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, BookPublisherRel.NAME).iterator();
        if(iterator.hasNext()){
            return new PublisherNode(iterator.next());
        }else{
            return null;
        }        
    }
    
    /**
     * Gets the city where the book was published
     * @return 
     */
    @Override
    public CityNode getCity(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, BookCityRel.NAME).iterator();
        if(iterator.hasNext()){
            return new CityNode(iterator.next());
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
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, BookAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }
        return list;
    }
    /**
     * gets the editors of the book
     * @return 
     */
    public List<PersonNode> getEditors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, BookEditorRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }
        return list;
    }

    @Override
    public String toString(){
        return "name = " + getName() + "\n" +
                "date = " + getDate();
    }

}