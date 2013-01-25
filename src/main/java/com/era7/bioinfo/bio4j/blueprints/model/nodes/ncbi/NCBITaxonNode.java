/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.BasicNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.TaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonParentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.ncbi.NCBITaxonRel;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NCBITaxonNode extends BasicNode{

    public static final String NODE_TYPE = NCBITaxonNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "ncbi_taxon_name";
    public static final String TAX_ID_PROPERTY = "ncbi_taxon_tax_id";
    public static final String SCIENTIFIC_NAME_PROPERTY = "ncbi_taxon_scientific_name";
    public static final String RANK_PROPERTY = "ncbi_taxon_rank";
    public static final String EMBL_CODE_PROPERTY = "ncbi_taxon_embl_code";
    public static final String COMMENTS_PROPERTY = "ncbi_taxon_comments";
    public static final String GI_IDS_PROPERTY = "ncbi_gi_ids";


    public NCBITaxonNode(Vertex v){
        super(v);
    }

    //----------------GETTERS---------------------
    public String getName(){    return String.valueOf(vertex.getProperty(NAME_PROPERTY));}
    public String getTaxId( ){  return String.valueOf(vertex.getProperty(TAX_ID_PROPERTY));}
    public String getRank(){    return String.valueOf(vertex.getProperty(RANK_PROPERTY));}
    public String getEmblCode(){    return String.valueOf(vertex.getProperty(EMBL_CODE_PROPERTY));}
    public String getComments(){    return String.valueOf(vertex.getProperty(COMMENTS_PROPERTY));}
    public String getScientificName(){    return String.valueOf(vertex.getProperty(SCIENTIFIC_NAME_PROPERTY));}
    
    //----------------SETTERS-------------------
    public void setTaxId(String value){  vertex.setProperty(TAX_ID_PROPERTY, String.valueOf(value));}
    public void setRank(String value){  vertex.setProperty(RANK_PROPERTY, value);}
    public void setEmblCode(String value){  vertex.setProperty(EMBL_CODE_PROPERTY, value);}
    public void setComments(String value){  vertex.setProperty(COMMENTS_PROPERTY, value);}
    public void setScientificName(String value){  vertex.setProperty(SCIENTIFIC_NAME_PROPERTY, value);} 
    public void setName(String value){  vertex.setProperty(NAME_PROPERTY, value);}

    /**
     * 
     * @return 
     */
    public NCBITaxonNode getParent(){
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
    public List<NCBITaxonNode> getChildren(){
        List<NCBITaxonNode> list = new ArrayList<NCBITaxonNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, NCBITaxonParentRel.NAME).iterator();
        
        while(iterator.hasNext()){
            Vertex tempNode = iterator.next();
            if(tempNode.getProperty(BasicNode.NODE_TYPE_PROPERTY).equals(NCBITaxonNode.NODE_TYPE)){
                list.add(new NCBITaxonNode(tempNode));
            }           
        }
        
        return list;
    }
    
    public TaxonNode getTaxon(){
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
