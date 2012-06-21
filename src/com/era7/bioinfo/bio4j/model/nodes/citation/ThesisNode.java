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

package com.era7.bioinfo.bio4j.model.nodes.citation;

import com.era7.bioinfo.bio4j.model.nodes.InstituteNode;
import com.era7.bioinfo.bio4j.model.nodes.PersonNode;
import com.era7.bioinfo.bio4j.model.relationships.citation.thesis.ThesisAuthorRel;
import com.era7.bioinfo.bio4j.model.relationships.citation.thesis.ThesisInstituteRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Thesis protein citations
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ThesisNode extends BasicEntity{

    public static final String THESIS_TITLE_FULL_TEXT_INDEX = "thesis_title_full_text_index";

    public static final String NODE_TYPE = ThesisNode.class.getCanonicalName();

    public static final String TITLE_PROPERTY = "title";
    public static final String DATE_PROPERTY = "date";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "thesis";


    public ThesisNode(Node n){
        super(n);
    }


    public String getTitle(){    return String.valueOf(node.getProperty(TITLE_PROPERTY));}
    public String getDate(){    return String.valueOf(node.getProperty(DATE_PROPERTY));}


    public void setTitle(String value){  node.setProperty(TITLE_PROPERTY, value);}
    public void setDate(String value){  node.setProperty(DATE_PROPERTY, value);}
    
    
    /**
     * Gets the thesis Institute
     * @return 
     */
    public InstituteNode getInstitute(){
        Iterator<Relationship> iterator = this.node.getRelationships(new ThesisInstituteRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new InstituteNode(iterator.next().getEndNode());
        }else{
            return null;
        }
    }
    
    /**
     * Gets the thesis author
     * @return 
     */
    public PersonNode getAuthor(){
        Iterator<Relationship> iterator = this.node.getRelationships(new ThesisAuthorRel(null), Direction.OUTGOING).iterator();
        if(iterator.hasNext()){
            return new PersonNode(iterator.next().getEndNode());
        }else{
            return null;
        }
    }


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ThesisNode){
            ThesisNode other = (ThesisNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "title = " + getTitle() + "\n" +
                "date = " + getDate();
    }

}

