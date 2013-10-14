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

import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.book.BookPublisherRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Publishers regarding to book protein citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PublisherNode extends BasicEntity{

    public static final String PUBLISHER_NAME_INDEX = "publisher_name_index";

    public static final String NODE_TYPE = PublisherNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "publisher_name";


    public PublisherNode(Node n){
        super(n);
    }


    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}


    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}

    
    public List<BookNode> getBooks(){
        List<BookNode> list = new LinkedList<BookNode>();
        Iterator<Relationship> iterator = node.getRelationships(new BookPublisherRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new BookNode(iterator.next().getStartNode()));
        }
        return list;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PublisherNode){
            PublisherNode other = (PublisherNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "name = " + getName();
    }

}
