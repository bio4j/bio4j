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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationAuthorRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationProteinCitationRel;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Protein unpublished observation citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class UnpublishedObservationNode extends BasicNode{

    public static final String DATE_PROPERTY = "unpublished_observation_date";

    public static final String NODE_TYPE = UnpublishedObservationNode.class.getCanonicalName();

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "unpublished observations";


    public UnpublishedObservationNode(Vertex v){
        super(v);
    }


    public String getDate(){    return String.valueOf(vertex.getProperty(DATE_PROPERTY));}

    public void setDate(String value){  vertex.setProperty(DATE_PROPERTY, value);}
    
    
    /**
     * Gets the author of the unpublished observation
     * @return 
     */
    public PersonNode getAuthor(){
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, UnpublishedObservationAuthorRel.NAME).iterator();
        if(iterator.hasNext()){
            return new PersonNode(iterator.next());
        }else{
            return null;
        }
    }
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, UnpublishedObservationProteinCitationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }

    @Override
    public String toString(){
        return "date = " + getDate();
    }

}
