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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bio4j.model.nodes.Person;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.citation.Patent;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Patent protein citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PatentNode extends BasicVertex implements Patent{

    public static final String NODE_TYPE = PatentNode.class.getCanonicalName();

    public static final String NUMBER_PROPERTY = "patent_number";
    public static final String DATE_PROPERTY = "patent_date";
    public static final String TITLE_PROPERTY = "patent_title";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "patent";


    public PatentNode(Vertex v){
        super(v);
    }


    @Override
    public String getNumber(){    return String.valueOf(vertex.getProperty(NUMBER_PROPERTY));}
    @Override
    public String getDate(){    return String.valueOf(vertex.getProperty(DATE_PROPERTY));}
    @Override
    public String getTitle(){    return String.valueOf(vertex.getProperty(TITLE_PROPERTY));}


    @Override
    public void setNumber(String value){  vertex.setProperty(NUMBER_PROPERTY, value);}
    @Override
    public void setDate(String value){  vertex.setProperty(DATE_PROPERTY, value);}
    @Override
    public void setTitle(String value){  vertex.setProperty(TITLE_PROPERTY, value);}


    /**
     * gets authors (if any) of the patent
     * @return 
     */
    @Override
    public List<Person> getAuthors(){
        List<Person> list = new ArrayList<Person>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, PatentAuthorRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }
        return list;
    }
    
    
    @Override
    public List<Protein> getProteinCitations(){
        List<Protein> list = new LinkedList<Protein>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, PatentProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }
    

    @Override
    public String toString(){
        return "number = " + getNumber() + "\n" +
                "date = " + getDate() + "\n" +
                "title = " + getTitle();
    }

}
