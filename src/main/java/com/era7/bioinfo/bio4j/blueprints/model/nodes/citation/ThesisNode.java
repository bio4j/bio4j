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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.InstituteNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisInstituteRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisProteinCitationRel;
import com.era7.bioinfo.bio4j.model.nodes.citation.Thesis;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Thesis protein citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ThesisNode extends BasicVertex implements Thesis{

    public static final String NODE_TYPE = ThesisNode.class.getCanonicalName();

    public static final String TITLE_PROPERTY = "thesis_title";
    public static final String DATE_PROPERTY = "thesis_date";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "thesis";


    public ThesisNode(Vertex v){
        super(v);
    }


    @Override
    public String getTitle(){    return String.valueOf(vertex.getProperty(TITLE_PROPERTY));}
    @Override
    public String getDate(){    return String.valueOf(vertex.getProperty(DATE_PROPERTY));}


    @Override
    public void setTitle(String value){  vertex.setProperty(TITLE_PROPERTY, value);}
    @Override
    public void setDate(String value){  vertex.setProperty(DATE_PROPERTY, value);}
    
    
    /**
     * Gets the thesis Institute
     * @return 
     */
    public InstituteNode getInstitute(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ThesisInstituteRel.NAME).iterator();
        if(iterator.hasNext()){
            return new InstituteNode(iterator.next());
        }else{
            return null;
        }
    }
    
    /**
     * Gets the thesis author
     * @return 
     */
    public PersonNode getAuthor(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ThesisAuthorRel.NAME).iterator();
        if(iterator.hasNext()){
            return new PersonNode(iterator.next());
        }else{
            return null;
        }
    }
    
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ThesisProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }

    @Override
    public String toString(){
        return "title = " + getTitle() + "\n" +
                "date = " + getDate();
    }

}

