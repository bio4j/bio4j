/*
 * Copyright (C) 2010-2013  "Bio4j"
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
package com.ohnosequences.bio4j.model.nodes.ncbi;

import com.ohnosequences.bio4j.model.Node;
import com.ohnosequences.bio4j.model.nodes.Taxon;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NCBITaxon extends Node{
    
    //----------------GETTERS---------------------
    public String getName();
    public String getTaxId();
    public String getRank();
    public String getEmblCode();
    public String getComments();
    public String getScientificName();
    public NCBITaxon getParent();
    public List<NCBITaxon> getChildren();    
    public Taxon getTaxon();
    
    //----------------SETTERS-------------------
    public void setTaxId(String value);
    public void setRank(String value);
    public void setEmblCode(String value);
    public void setComments(String value);
    public void setScientificName(String value);
    public void setName(String value);
        
}
