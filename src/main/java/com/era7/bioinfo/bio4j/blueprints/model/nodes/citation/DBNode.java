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
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionDbRel;
import com.era7.bioinfo.bio4j.model.nodes.citation.DB;
import com.era7.bioinfo.bio4j.model.nodes.citation.Submission;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Databases where submission protein citations are submitted to
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class DBNode extends BasicVertex implements DB{

    public static final String NODE_TYPE = DBNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "db_name";


    public DBNode(Vertex v){
        super(v);
    }


    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}


    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}

    
    @Override
    public List<Submission> getAssociatedSubmissions(){
        List<Submission> list = new LinkedList<Submission>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, SubmissionDbRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new SubmissionNode(iterator.next()));
        }        
        return list;
    }


    @Override
    public String toString(){
        return "name = " + getName();
    }

}
