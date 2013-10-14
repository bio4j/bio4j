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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.uo.UnpublishedObservationAuthorRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.uo.UnpublishedObservationProteinCitationRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Protein unpublished observation citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class UnpublishedObservationNode extends BasicEntity{

    public static final String DATE_PROPERTY = "unpublished_observation_date";

    public static final String NODE_TYPE = UnpublishedObservationNode.class.getCanonicalName();

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "unpublished observations";


    public UnpublishedObservationNode(Node n){
        super(n);
    }


    public String getDate(){    return String.valueOf(node.getProperty(DATE_PROPERTY));}

    public void setDate(String value){  node.setProperty(DATE_PROPERTY, value);}
    
    
    /**
     * Gets the author of the unpublished observation
     * @return 
     */
    public PersonNode getAuthor(){
        Iterator<Relationship> iterator = this.node.getRelationships(new UnpublishedObservationAuthorRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new PersonNode(iterator.next().getEndNode());
        }else{
            return null;
        }
    }
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = node.getRelationships(new UnpublishedObservationProteinCitationRel(null), Direction.OUTGOING).iterator();
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
        if(obj instanceof UnpublishedObservationNode){
            UnpublishedObservationNode other = (UnpublishedObservationNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "date = " + getDate();
    }

}
