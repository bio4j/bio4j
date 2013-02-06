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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicNode;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import org.apache.commons.configuration.Configuration;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class Bio4jManager{
        
    protected TitanGraph graph;
    
    /*
     * Constructor
     * @param dbFolder
     */
    public Bio4jManager(String dbFolder) {
        graph = TitanFactory.open(dbFolder);
    }
    
    /*
     * Constructor
     * @param dbFolder
     */
    public Bio4jManager(Configuration config) {
        graph = TitanFactory.open(config);
    }
    
    /**
     * Creates a new node
     * @param nodeType Type of the new node
     * @return 
     */
    public Vertex createNode(String nodeType){
        Vertex vertex = graph.addVertex(null);
        vertex.setProperty(BasicNode.NODE_TYPE_PROPERTY, nodeType);
        return vertex;
    }
    
    public TitanGraph getGraph(){
        return graph;
    }
    
    public void shutDown(){
        graph.shutdown();
    }
}
