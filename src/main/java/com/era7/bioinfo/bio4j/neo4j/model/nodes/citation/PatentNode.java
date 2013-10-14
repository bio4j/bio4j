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
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.patent.PatentAuthorRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Patent protein citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PatentNode extends BasicEntity{

    public static final String PATENT_NUMBER_INDEX = "patent_number_index";

    public static final String NODE_TYPE = PatentNode.class.getCanonicalName();

    public static final String NUMBER_PROPERTY = "patent_number";
    public static final String DATE_PROPERTY = "patent_date";
    public static final String TITLE_PROPERTY = "patent_title";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "patent";


    public PatentNode(Node n){
        super(n);
    }


    public String getNumber(){    return String.valueOf(node.getProperty(NUMBER_PROPERTY));}
    public String getDate(){    return String.valueOf(node.getProperty(DATE_PROPERTY));}
    public String getTitle(){    return String.valueOf(node.getProperty(TITLE_PROPERTY));}


    public void setNumber(String value){  node.setProperty(NUMBER_PROPERTY, value);}
    public void setDate(String value){  node.setProperty(DATE_PROPERTY, value);}
    public void setTitle(String value){  node.setProperty(TITLE_PROPERTY, value);}


    /**
     * gets authors (if any) of the patent
     * @return 
     */
    public List<PersonNode> getAuthors(){
        List<PersonNode> list = new ArrayList<PersonNode>();
        Iterator<Relationship> iterator = this.node.getRelationships(new PatentAuthorRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next().getEndNode()));
        }
        return list;
    }
    
    
    public List<ProteinNode> getProteinCitations(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = node.getRelationships(new PatentProteinCitationRel(null), Direction.OUTGOING).iterator();
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
        if(obj instanceof PatentNode){
            PatentNode other = (PatentNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "number = " + getNumber() + "\n" +
                "date = " + getDate() + "\n" +
                "title = " + getTitle();
    }

}
