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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.SubcellularLocationNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinSubcellularLocationRel extends BasicRelationship{

    public static final String NAME = "PROTEIN_SUBCELLULAR_LOCATION";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "subcellular location";

    public static final String EVIDENCE_PROPERTY = "evidence";
    public static final String STATUS_PROPERTY = "status";
    public static final String TOPOLOGY_STATUS_PROPERTY = "topology_status";
    public static final String TOPOLOGY_PROPERTY = "topology";

    public ProteinSubcellularLocationRel(Relationship rel){
        super(rel);
    }

    public String getEvidence(){    return String.valueOf(this.relationship.getProperty(EVIDENCE_PROPERTY));}
    public String getStatus(){  return String.valueOf(this.relationship.getProperty(STATUS_PROPERTY));}
    public String getTopology(){    return String.valueOf(this.relationship.getProperty(TOPOLOGY_PROPERTY));}
    public String getTopologyStatus(){  return String.valueOf(this.relationship.getProperty(TOPOLOGY_STATUS_PROPERTY));}

    public void setEvidence(String value){  this.relationship.setProperty(EVIDENCE_PROPERTY, value);}
    public void setStatus(String value){    this.relationship.setProperty(STATUS_PROPERTY, value);}
    public void setTopology(String value){  this.relationship.setProperty(TOPOLOGY_PROPERTY, value);}
    public void setTopologyStatus(String value){    this.relationship.setProperty(TOPOLOGY_STATUS_PROPERTY, value);}

    
    public ProteinNode getProtein(){
        return new ProteinNode(getStartNode());
    }
    
    public SubcellularLocationNode getSubcellularLocation(){
        return new SubcellularLocationNode(getEndNode());
    }
    
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString(){
        return "status = " + getStatus() +
                "\nevidence = " + getEvidence() +
                "\ntopology = " + getTopology() +
                "\ntopology status = " + getTopologyStatus();
    }

}