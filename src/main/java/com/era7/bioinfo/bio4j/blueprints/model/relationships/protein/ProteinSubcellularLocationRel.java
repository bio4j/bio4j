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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.SubcellularLocationNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.BasicRelationshipBlueprints;
import com.era7.bioinfo.bio4j.model.relationships.protein.ProteinSubcellularLocation;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

/**
 * 
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinSubcellularLocationRel extends BasicRelationshipBlueprints implements ProteinSubcellularLocation{

    public static final String NAME = "PROTEIN_SUBCELLULAR_LOCATION";

    public static final String UNIPROT_ATTRIBUTE_TYPE_VALUE = "subcellular location";

    public static final String EVIDENCE_PROPERTY = "protein_subcellular_location_evidence";
    public static final String STATUS_PROPERTY = "protein_subcellular_location_status";
    public static final String TOPOLOGY_STATUS_PROPERTY = "protein_subcellular_location_topology_status";
    public static final String TOPOLOGY_PROPERTY = "protein_subcellular_location_topology";

    public ProteinSubcellularLocationRel(Edge e){
        super(e);
    }

    @Override
    public String getEvidence(){    return String.valueOf(edge.getProperty(EVIDENCE_PROPERTY));}
    @Override
    public String getStatus(){  return String.valueOf(edge.getProperty(STATUS_PROPERTY));}
    @Override
    public String getTopology(){    return String.valueOf(edge.getProperty(TOPOLOGY_PROPERTY));}
    @Override
    public String getTopologyStatus(){  return String.valueOf(edge.getProperty(TOPOLOGY_STATUS_PROPERTY));}

    @Override
    public void setEvidence(String value){  edge.setProperty(EVIDENCE_PROPERTY, value);}
    @Override
    public void setStatus(String value){    edge.setProperty(STATUS_PROPERTY, value);}
    @Override
    public void setTopology(String value){  edge.setProperty(TOPOLOGY_PROPERTY, value);}
    @Override
    public void setTopologyStatus(String value){    edge.setProperty(TOPOLOGY_STATUS_PROPERTY, value);}

    @Override
    public ProteinNode getProtein(){
        return new ProteinNode(getVertex(Direction.IN));
    }
    
    @Override
    public SubcellularLocationNode getSubcellularLocation(){
        return new SubcellularLocationNode(getVertex(Direction.OUT));
    }
    
    @Override
    public String getType() {
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