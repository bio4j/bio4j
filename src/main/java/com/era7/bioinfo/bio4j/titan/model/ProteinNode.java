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

import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinNode extends com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode{
    
    public ProteinNode(Vertex v){
        super(v);
    }
    
    
    
    @Override
    public String[] getEMBLreferences(){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(EMBL_REFERENCES_PROPERTY).iterator();
        List<String> tempList = new LinkedList<String>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;        
    }
    
    @Override
    public String[] getEnsemblPlantsReferences(){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(ENSEMBL_PLANTS_REFERENCES_PROPERTY).iterator();
        List<String> tempList = new LinkedList<String>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;
    }
    
    @Override
    public String[] getRefseqReferences(){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(REFSEQ_REFERENCES_PROPERTY).iterator();
        List<String> tempList = new LinkedList<String>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;
    }

    @Override
    public String[] getAlternativeAcessions(){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(ALTERNATIVE_ACCESSIONS_PROPERTY).iterator();
        List<String> tempList = new LinkedList<String>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;
    }
    
    
    @Override
    public void setEMBLreferences(String[] list){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(EMBL_REFERENCES_PROPERTY);
        for (String string : list) {
            tempVertex.addProperty(EMBL_REFERENCES_PROPERTY, string);
        } 
    }
    
    @Override
    public void setEnsemblPlantsReferences(String[] list){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY);
        for (String string : list) {
            tempVertex.addProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY, string);
        } 
    }

    @Override
    public void setRefseqReferences(String[] list){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(REFSEQ_REFERENCES_PROPERTY);
        for (String string : list) {
            tempVertex.addProperty(REFSEQ_REFERENCES_PROPERTY, string);
        }  
    }
    
    @Override
    public void setAlternativeAccessions(String[] list){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
        for (String string : list) {
            tempVertex.addProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, string);
        }        
    }
    
}
