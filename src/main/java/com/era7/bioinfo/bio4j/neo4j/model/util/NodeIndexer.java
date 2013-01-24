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
package com.era7.bioinfo.bio4j.neo4j.model.util;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.reactome.ReactomeTermNode;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeIndexer {
    
    protected Bio4jManager manager;
    
    
    public NodeIndexer(Bio4jManager bio4jManager){
        manager = bio4jManager;
    }
    
    
    public void indexReactomeTermById(ReactomeTermNode node,Transaction txn, boolean commitTxn){
        
        manager.getReactomeTermIdIndex().add(node.getNode(), ReactomeTermNode.REACTOME_TERM_ID_INDEX, node.getId());
        if(commitTxn){
            txn.success();
            txn.finish();
        }        
    }
    
    
}
