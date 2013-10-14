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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.citation.book;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.citation.BookNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * Proteins one book references
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class BookProteinCitationRel extends BasicRelationship{

    public static final String NAME = "BOOK_PROTEIN_CITATION";

    public static final String TITLE_PROPERTY = "title";
    public static final String VOLUME_PROPERTY = "volume";
    public static final String FIRST_PROPERTY = "first";
    public static final String LAST_PROPERTY = "last";

    public BookProteinCitationRel(Relationship rel){
        super(rel);
    }

    public String getTitle(){    return String.valueOf(this.relationship.getProperty(TITLE_PROPERTY));}
    public String getVolume(){    return String.valueOf(this.relationship.getProperty(VOLUME_PROPERTY));}
    public String getFirst(){    return String.valueOf(this.relationship.getProperty(FIRST_PROPERTY));}
    public String getLast(){    return String.valueOf(this.relationship.getProperty(LAST_PROPERTY));}

    public void setTitle(String value){  this.relationship.setProperty(TITLE_PROPERTY, value);}
    public void setVolume(String value){  this.relationship.setProperty(VOLUME_PROPERTY, value);}
    public void setFirst(String value){  this.relationship.setProperty(FIRST_PROPERTY, value);}
    public void setLast(String value){  this.relationship.setProperty(LAST_PROPERTY, value);}

    public BookNode getBook(){
        return new BookNode(getStartNode());
    }
    
    public ProteinNode getProtein(){
        return new ProteinNode(getEndNode());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString(){
        return "title = " + getTitle() + "\n" +
                "volume = " + getVolume() + "\n" +
                "first = " + getFirst() + "\n" +
                "last = " + getLast();
    }

}