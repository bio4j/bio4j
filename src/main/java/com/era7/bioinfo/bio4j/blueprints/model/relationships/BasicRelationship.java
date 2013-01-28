/*
 * Copyright (C) 2010-2012  "Oh no sequences!"
 *
 * This is free software: you can redistribute it and/or modify
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

package com.era7.bioinfo.bio4j.blueprints.model.relationships;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import java.util.Set;

/**
 *
 * @author ppareja
 */
public abstract class BasicRelationship implements Edge{
    
    protected Edge edge = null;

    public BasicRelationship(Edge e){
        edge = e;
    }

    
    public Edge getEdge(){
        return edge;
    }
    
    @Override
    public Vertex getVertex(Direction drctn) throws IllegalArgumentException {
        return edge.getVertex(drctn);
    }

    @Override
    public Object getProperty(String string) {
        return edge.getProperty(string);
    }

    @Override
    public Set<String> getPropertyKeys() {
        return edge.getPropertyKeys();
    }

    @Override
    public void setProperty(String string, Object o) {
        edge.setProperty(string, o);
    }

    @Override
    public Object removeProperty(String string) {
        return edge.removeProperty(string);
    }

    @Override
    public Object getId() {
        return edge.getId();
    }

}
