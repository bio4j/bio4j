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
package com.era7.bioinfo.bio4j.model.relationships.protein;

import com.era7.bioinfo.bio4j.model.nodes.Isoform;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.relationships.BasicRelationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinIsoformInteraction extends BasicRelationship{
    
    public String getOrganismsDiffer();
    public String getExperiments();
    public String getIntactId2();
    public String getIntactId1();

    public void setOrganismsDiffer(String value);
    public void setExperiments(String value);
    public void setIntactId2(String value);
    public void setIntactId1(String value);

    public Protein getProtein();    
    public Isoform getIsoformNode();
    
}
