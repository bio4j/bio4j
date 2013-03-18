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
package com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicVertex;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.TaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonParentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonRel;
import com.era7.bioinfo.bio4j.model.nodes.Taxon;
import com.era7.bioinfo.bio4j.model.nodes.ncbi.NCBITaxon;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NCBITaxonNode extends BasicVertex implements NCBITaxon{

    public static final String NODE_TYPE = NCBITaxonNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "ncbi_taxon_name";
    public static final String TAX_ID_PROPERTY = "ncbi_taxon_tax_id";
    public static final String SCIENTIFIC_NAME_PROPERTY = "ncbi_taxon_scientific_name";
    public static final String RANK_PROPERTY = "ncbi_taxon_rank";
    public static final String EMBL_CODE_PROPERTY = "ncbi_taxon_embl_code";
    public static final String COMMENTS_PROPERTY = "ncbi_taxon_comments";
    public static final String GI_IDS_PROPERTY = "ncbi_taxon_gi_ids";
    public static final String OLD_TAX_IDS_PROPERTY = "ncbi_taxon_old_tax_ids";


    public NCBITaxonNode(Vertex v){
        super(v);
    }

    //----------------GETTERS---------------------
    @Override
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}
    @Override
    public String getTaxId( ){  return String.valueOf(vertex.getProperty(TAX_ID_PROPERTY));}
    @Override
    public String getRank(){    return String.valueOf(vertex.getProperty(RANK_PROPERTY));}
    @Override
    public String getEmblCode(){    return String.valueOf(vertex.getProperty(EMBL_CODE_PROPERTY));}
    @Override
    public String getComments(){    return String.valueOf(vertex.getProperty(COMMENTS_PROPERTY));}
    @Override
    public String getScientificName(){    return String.valueOf(vertex.getProperty(SCIENTIFIC_NAME_PROPERTY));}
    
    //----------------SETTERS-------------------
    @Override
    public void setTaxId(String value){  vertex.setProperty(TAX_ID_PROPERTY, String.valueOf(value));}
    @Override
    public void setRank(String value){  vertex.setProperty(RANK_PROPERTY, value);}
    @Override
    public void setEmblCode(String value){  vertex.setProperty(EMBL_CODE_PROPERTY, value);}
    @Override
    public void setComments(String value){  vertex.setProperty(COMMENTS_PROPERTY, value);}
    @Override
    public void setScientificName(String value){  vertex.setProperty(SCIENTIFIC_NAME_PROPERTY, value);} 
    @Override
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}
    
    public void addOldTaxId(String value){  }
    public void addGiId(String value){}
    
    

    /**
     * 
     * @return 
     */
    @Override
    public NCBITaxon getParent(){
        NCBITaxonNode parent = null;
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, NCBITaxonParentRel.NAME).iterator();
        if(iterator.hasNext()){
            parent = new NCBITaxonNode(iterator.next());
        }
        
        return parent;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public List<NCBITaxon> getChildren(){
        List<NCBITaxon> list = new LinkedList<NCBITaxon>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, NCBITaxonParentRel.NAME).iterator();
        
        while(iterator.hasNext()){
            Vertex tempNode = iterator.next();
            if(tempNode.getProperty(BasicVertex.NODE_TYPE_PROPERTY).equals(NCBITaxonNode.NODE_TYPE)){
                list.add(new NCBITaxonNode(tempNode));
            }           
        }
        
        return list;
    }
    
    @Override
    public Taxon getTaxon(){
        TaxonNode taxon = null;
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, NCBITaxonRel.NAME).iterator();
        if(iterator.hasNext()){
            taxon = new TaxonNode(iterator.next());
        }
        
        return taxon;
    }

    @Override
    public String toString(){
        return "name = " + getName();
    }

}
