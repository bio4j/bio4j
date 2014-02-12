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
package com.ohnosequences.bio4j.model.relationships.protein;

import com.ohnosequences.bio4j.model.nodes.Protein;
import com.ohnosequences.bio4j.model.nodes.SequenceCaution;
import com.ohnosequences.bio4j.model.relationships.BasicRelationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BasicProteinSequenceCaution extends BasicRelationship{
    
    public String getText();
    public String getStatus();
    public String getEvidence();
    public String getId();
    public String getPosition();
    public String getResource();
    public String getVersion();
    
    public Protein getProtein();
    public SequenceCaution getSequenceCaution();

    public void setText(String value);
    public void setStatus(String value);
    public void setEvidence(String value);
    public void setId(String value);
    public void setPosition(String value);
    public void setResource(String value);
    public void setVersion(String value);
}
