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

import com.era7.bioinfo.bio4j.neo4j.model.nodes.EnzymeNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.ProteinNode;
import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 * Protein enzymatic activity (EC number connections)
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinEnzymaticActivityRel extends BasicRelationship{

    public static final String NAME = "PROTEIN_ENZYMATIC_ACTIVITY";

    public ProteinEnzymaticActivityRel(Relationship rel){
        super(rel);
    }
    
    public ProteinNode getProtein(){
        return new ProteinNode(getStartNode());
    }
    
    public EnzymeNode getEnzyme(){
        return new EnzymeNode(getEndNode());
    }

    @Override
    public String name() {
        return NAME;
    }

}