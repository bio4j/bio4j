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

package com.era7.bioinfo.bio4j.neo4j.model.nodes;

import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.ProteinEnzymaticActivityRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 * Enzyme node
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class EnzymeNode extends BasicEntity{

    public static final String ENZYME_ID_INDEX = "enzyme_id_index";

    public static final String NODE_TYPE = EnzymeNode.class.getCanonicalName();

    /** Enzyme id (EZ number)**/
    public static final String ID_PROPERTY = "enzyme_id";
    public static final String OFFICIAL_NAME_PROPERTY = "enzyme_official_name";
    public static final String ALTERNATE_NAMES_PROPERTY = "enzyme_alternate_names";
    public static final String CATALYTIC_ACTIVITY_PROPERTY = "enzyme_catalytic_activity";
    public static final String COFACTORS_PROPERTY = "enzyme_cofactors";
    public static final String COMMENTS_PROPERTY = "enzyme_comments";
    public static final String PROSITE_CROSS_REFERENCES_PROPERTY = "enzyme_prosite_cross_references";


    public EnzymeNode(Node n){
        super(n);
    }


    public String getId(){  return String.valueOf(node.getProperty(ID_PROPERTY));}
    public String getOfficialName(){  return String.valueOf(node.getProperty(OFFICIAL_NAME_PROPERTY));}
    public String[] getAlternateNames(){  return (String[])node.getProperty(ALTERNATE_NAMES_PROPERTY);}
    public String getCatalyticActivity(){   return String.valueOf(node.getProperty(CATALYTIC_ACTIVITY_PROPERTY));}
    public String[] getCofactors(){   return (String[])node.getProperty(COFACTORS_PROPERTY);}
    public String getComments(){   return String.valueOf(node.getProperty(COMMENTS_PROPERTY));}
    public String[] getPrositeCrossReferences(){   return (String[])node.getProperty(PROSITE_CROSS_REFERENCES_PROPERTY);}

    public void setId(String value){    node.setProperty(ID_PROPERTY, value);}
    public void setOfficialName(String value){    node.setProperty(OFFICIAL_NAME_PROPERTY, value);}
    public void setAlternateNames(String[] value){    node.setProperty(ALTERNATE_NAMES_PROPERTY, value);}
    public void setCatalyticActivity(String value){ node.setProperty(CATALYTIC_ACTIVITY_PROPERTY, value);}
    public void setCofactors(String[] value){    node.setProperty(COFACTORS_PROPERTY, value);}
    public void setComments(String value){    node.setProperty(COMMENTS_PROPERTY, value);}
    public void setPrositeCrossReferences(String[] value){    node.setProperty(PROSITE_CROSS_REFERENCES_PROPERTY, value);}

    
    public List<ProteinNode> getAssociatedProteins(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        Iterator<Relationship> iterator = node.getRelationships(new ProteinEnzymaticActivityRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next().getStartNode()));
        }
        return list;
    }
    

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EnzymeNode){
            EnzymeNode other = (EnzymeNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
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
