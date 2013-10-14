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

package com.era7.bioinfo.bio4j.neo4j.model.relationships.protein;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinIsoformInteractionRel extends BasicRelationship{

    public static final String NAME = "PROTEIN_ISOFORM_INTERACTION";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "interaction";

    public static final String ORGANISMS_DIFFER_PROPERTY = "organisms_differ";
    public static final String EXPERIMENTS_PROPERTY = "experiments";
    public static final String INTACT_ID_1_PROPERTY = "intact_id_1";
    public static final String INTACT_ID_2_PROPERTY = "intact_id_2";

    public ProteinIsoformInteractionRel(Relationship rel){
        super(rel);
    }

    public String getOrganismsDiffer(){    return String.valueOf(this.relationship.getProperty(ORGANISMS_DIFFER_PROPERTY));}
    public String getExperiments(){  return String.valueOf(this.relationship.getProperty(EXPERIMENTS_PROPERTY));}
    public String getIntactId2(){    return String.valueOf(this.relationship.getProperty(INTACT_ID_2_PROPERTY));}
    public String getIntactId1(){  return String.valueOf(this.relationship.getProperty(INTACT_ID_1_PROPERTY));}

    public void setOrganismsDiffer(String value){  this.relationship.setProperty(ORGANISMS_DIFFER_PROPERTY, value);}
    public void setExperiments(String value){    this.relationship.setProperty(EXPERIMENTS_PROPERTY, value);}
    public void setIntactId2(String value){  this.relationship.setProperty(INTACT_ID_2_PROPERTY, value);}
    public void setIntactId1(String value){    this.relationship.setProperty(INTACT_ID_1_PROPERTY, value);}

    public ProteinNode getProtein(){
        return new ProteinNode(getStartNode());
    }
    
    public IsoformNode getIsoformNode(){
        return new IsoformNode(getEndNode());
    }
    
    
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString(){
        return "organisms differ = " + getOrganismsDiffer() + "\n" +
                "experiments = " + getExperiments() + "\n" +
                "intactId 1 = " + getIntactId1() + "\n" +
                "intactId 2 = " + getIntactId2();
    }

}