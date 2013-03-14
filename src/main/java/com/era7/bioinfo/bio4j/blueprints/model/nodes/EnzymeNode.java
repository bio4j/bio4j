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

package com.era7.bioinfo.bio4j.blueprints.model.nodes;

import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinEnzymaticActivityRel;
import com.era7.bioinfo.bio4j.model.nodes.Enzyme;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Enzyme node
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class EnzymeNode extends BasicVertex implements Enzyme{

    public static final String NODE_TYPE = EnzymeNode.class.getCanonicalName();

    /** Enzyme id (EZ number)**/
    public static final String ID_PROPERTY = "enzyme_id";
    public static final String OFFICIAL_NAME_PROPERTY = "enzyme_official_name";
    public static final String ALTERNATE_NAMES_PROPERTY = "enzyme_alternate_names";
    public static final String CATALYTIC_ACTIVITY_PROPERTY = "enzyme_catalytic_activity";
    public static final String COFACTORS_PROPERTY = "enzyme_cofactors";
    public static final String COMMENTS_PROPERTY = "enzyme_comments";
    public static final String PROSITE_CROSS_REFERENCES_PROPERTY = "enzyme_prosite_cross_references";


    public EnzymeNode(Vertex v){
        super(v);
    }


    @Override
    public String getId(){  return String.valueOf(vertex.getProperty(ID_PROPERTY));}
    @Override
    public String getOfficialName(){  return String.valueOf(vertex.getProperty(OFFICIAL_NAME_PROPERTY));}
    @Override
    public String[] getAlternateNames(){  return (String[])vertex.getProperty(ALTERNATE_NAMES_PROPERTY);}
    @Override
    public String getCatalyticActivity(){   return String.valueOf(vertex.getProperty(CATALYTIC_ACTIVITY_PROPERTY));}
    @Override
    public String[] getCofactors(){   return (String[])vertex.getProperty(COFACTORS_PROPERTY);}
    @Override
    public String getComments(){   return String.valueOf(vertex.getProperty(COMMENTS_PROPERTY));}
    @Override
    public String[] getPrositeCrossReferences(){   return (String[])vertex.getProperty(PROSITE_CROSS_REFERENCES_PROPERTY);}

    @Override
    public void setId(String value){    vertex.setProperty(ID_PROPERTY, value);}
    @Override
    public void setOfficialName(String value){    vertex.setProperty(OFFICIAL_NAME_PROPERTY, value);}
    @Override
    public void setAlternateNames(String[] value){    vertex.setProperty(ALTERNATE_NAMES_PROPERTY, value);}
    @Override
    public void setCatalyticActivity(String value){ vertex.setProperty(CATALYTIC_ACTIVITY_PROPERTY, value);}
    @Override
    public void setCofactors(String[] value){    vertex.setProperty(COFACTORS_PROPERTY, value);}
    @Override
    public void setComments(String value){    vertex.setProperty(COMMENTS_PROPERTY, value);}
    @Override
    public void setPrositeCrossReferences(String[] value){    vertex.setProperty(PROSITE_CROSS_REFERENCES_PROPERTY, value);}

    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ProteinEnzymaticActivityRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }
        return list;
    }
    

    @Override
    public String toString(){
        String alternateNames = "";
        for (String altName : getAlternateNames()) {
            alternateNames += altName + ",";
        }
        alternateNames = alternateNames.substring(0, alternateNames.length() - 1);
        String cofactors = "";
        for (String cofactor : getCofactors()) {
            cofactors += cofactor + ",";
        }
        cofactors = cofactors.substring(0, cofactors.length() - 1);
        String prositeRefs = "";
        for (String prositeRef : getPrositeCrossReferences()) {
            prositeRefs += prositeRef + ",";
        }
        prositeRefs = prositeRefs.substring(0, prositeRefs.length() - 1);
        
        return "ID = " + getId() + "\n" +
                "Official name = " + getOfficialName() + "\n" +
                "Alternate names = " + alternateNames + "\n" +
                "Catalytic activity = " + getCatalyticActivity() + "\n" +
                "Cofactors = " + cofactors + "\n" +
                "Comments = " + getComments() + "\n" +
                "Prosite cross-references" + prositeRefs;
    }

}
