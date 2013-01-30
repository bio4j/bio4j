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
package com.era7.bioinfo.bio4j.titan.model.util;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.DatasetNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.EnzymeNode;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetriever {   
    
    
    protected Bio4jManager manager;
    
    public NodeRetriever(Bio4jManager bio4jManager){
        manager = bio4jManager;
    }
    
    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
    
    public EnzymeNode getEnzymeById(String id){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(EnzymeNode.ID_PROPERTY, id).iterator(); 
        if(iterator.hasNext()){
            return new EnzymeNode(iterator.next());
        }else{
            return null;
        }
    }
    
    //-------------------------------------------------------------------
    //--------------------------DATASETS-----------------------------------
        
    public DatasetNode getDatasetByName(String name){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, name).iterator();  
        if(iterator.hasNext()){
            return new DatasetNode(iterator.next());
        }else{
            return null;
        }
    }    
    public DatasetNode getSwissProtDataset(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.SWISS_PROT_DATASET_NAME).iterator();  
        if(iterator.hasNext()){
            return new DatasetNode(iterator.next());
        }else{
            return null;
        }
    }
    public DatasetNode getTremblDataset(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.TREMBL_DATASET_NAME).iterator();  
        if(iterator.hasNext()){
            return new DatasetNode(iterator.next());
        }else{
            return null;
        }
    }
}
