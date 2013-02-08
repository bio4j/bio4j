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
package com.era7.bioinfo.bio4j.titan.model;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicVertex;
import com.thinkaurelius.titan.core.*;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import java.util.Set;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class BasicNode implements TitanVertex{
    
    BasicVertex vertex = null;
    
    public BasicNode(BasicVertex v){
        vertex = v;
    }

    @Override
    public TitanEdge addEdge(TitanLabel tl, TitanVertex tv) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TitanEdge addEdge(String string, TitanVertex tv) {
        return 
    }

    @Override
    public TitanProperty addProperty(TitanKey tk, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TitanProperty addProperty(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setProperty(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object removeProperty(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TitanQuery query() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getProperty(TitanKey tk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getProperty(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <O> O getProperty(TitanKey tk, Class<O> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <O> O getProperty(String string, Class<O> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanProperty> getProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanProperty> getProperties(TitanKey tk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanProperty> getProperties(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanEdge> getTitanEdges(Direction drctn, TitanLabel... tls) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Edge> getEdges(Direction drctn, String... strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanEdge> getEdges() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<TitanRelation> getRelations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getEdgeCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getPropertyCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isNew() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLoaded() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isModified() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRemoved() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isReferenceVertex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAvailable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAccessible() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Vertex> getVertices(Direction drctn, String... strings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> getPropertyKeys() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
