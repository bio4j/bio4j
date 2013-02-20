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

package com.era7.bioinfo.bio4j.blueprints.model.relationships.protein;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.IsoformNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationship;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinIsoformInteractionRel extends BasicRelationship{

    public static final String NAME = "PROTEIN_ISOFORM_INTERACTION";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "interaction";

    public static final String ORGANISMS_DIFFER_PROPERTY = "protein_isoform_interaction_organisms_differ";
    public static final String EXPERIMENTS_PROPERTY = "protein_isoform_interaction_experiments";
    public static final String INTACT_ID_1_PROPERTY = "protein_isoform_interaction_intact_id_1";
    public static final String INTACT_ID_2_PROPERTY = "protein_isoform_interaction_intact_id_2";

    public ProteinIsoformInteractionRel(Edge e){
        super(e);
    }

    public String getOrganismsDiffer(){    return String.valueOf(edge.getProperty(ORGANISMS_DIFFER_PROPERTY));}
    public String getExperiments(){  return String.valueOf(edge.getProperty(EXPERIMENTS_PROPERTY));}
    public String getIntactId2(){    return String.valueOf(edge.getProperty(INTACT_ID_2_PROPERTY));}
    public String getIntactId1(){  return String.valueOf(edge.getProperty(INTACT_ID_1_PROPERTY));}

    public void setOrganismsDiffer(String value){  edge.setProperty(ORGANISMS_DIFFER_PROPERTY, value);}
    public void setExperiments(String value){    edge.setProperty(EXPERIMENTS_PROPERTY, value);}
    public void setIntactId2(String value){  edge.setProperty(INTACT_ID_2_PROPERTY, value);}
    public void setIntactId1(String value){    edge.setProperty(INTACT_ID_1_PROPERTY, value);}

    public ProteinNode getProtein(){
        return new ProteinNode(getVertex(Direction.IN));
    }
    
    public IsoformNode getIsoformNode(){
        return new IsoformNode(getVertex(Direction.OUT));
    }
    
    
    @Override
    public String getLabel() {
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