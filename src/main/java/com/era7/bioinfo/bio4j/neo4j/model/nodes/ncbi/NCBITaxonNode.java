/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.neo4j.model.nodes.ncbi;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.TaxonNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.ncbi.NCBITaxonParentRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.ncbi.NCBITaxonRel;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NCBITaxonNode extends BasicEntity{

    public static final String NCBI_TAXON_ID_INDEX = "ncbi_taxon_id_index";
    public static final String NCBI_TAXON_GI_ID_INDEX = "ncbi_taxon_gi_id_index";

    public static final String NODE_TYPE = NCBITaxonNode.class.getCanonicalName();

    public static final String NAME_PROPERTY = "ncbi_taxon_name";
    public static final String TAX_ID_PROPERTY = "ncbi_taxon_tax_id";
    public static final String SCIENTIFIC_NAME_PROPERTY = "ncbi_taxon_scientific_name";
    public static final String RANK_PROPERTY = "ncbi_taxon_rank";
    public static final String EMBL_CODE_PROPERTY = "ncbi_taxon_embl_code";
    public static final String COMMENTS_PROPERTY = "ncbi_taxon_comments";


    public NCBITaxonNode(Node n){
        super(n);
    }

    //----------------GETTERS---------------------
    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}
    public String getTaxId( ){  return String.valueOf(node.getProperty(TAX_ID_PROPERTY));}
    public String getRank(){    return String.valueOf(node.getProperty(RANK_PROPERTY));}
    public String getEmblCode(){    return String.valueOf(node.getProperty(EMBL_CODE_PROPERTY));}
    public String getComments(){    return String.valueOf(node.getProperty(COMMENTS_PROPERTY));}
    public String getScientificName(){    return String.valueOf(node.getProperty(SCIENTIFIC_NAME_PROPERTY));}
    
    //----------------SETTERS-------------------
    public void setTaxId(String value){  node.setProperty(TAX_ID_PROPERTY, String.valueOf(value));}
    public void setRank(String value){  node.setProperty(RANK_PROPERTY, value);}
    public void setEmblCode(String value){  node.setProperty(EMBL_CODE_PROPERTY, value);}
    public void setComments(String value){  node.setProperty(COMMENTS_PROPERTY, value);}
    public void setScientificName(String value){  node.setProperty(SCIENTIFIC_NAME_PROPERTY, value);}

    

    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}

    /**
     * 
     * @return 
     */
    public NCBITaxonNode getParent(){
        NCBITaxonNode parent = null;
        
        Iterator<Relationship> iterator = this.getNode().getRelationships(new NCBITaxonParentRel(null), Direction.INCOMING).iterator();
        if(iterator.hasNext()){
            parent = new NCBITaxonNode(iterator.next().getStartNode());
        }
        
        return parent;
    }
    
    /**
     * 
     * @return 
     */
    public List<NCBITaxonNode> getChildren(){
        List<NCBITaxonNode> list = new ArrayList<NCBITaxonNode>();
        
        Iterator<Relationship> iterator = this.getNode().getRelationships(new NCBITaxonParentRel(null), Direction.OUTGOING).iterator();
        
        while(iterator.hasNext()){
            Node tempNode = iterator.next().getEndNode();
            if(tempNode.getProperty(BasicEntity.NODE_TYPE_PROPERTY).equals(NCBITaxonNode.NODE_TYPE)){
                list.add(new NCBITaxonNode(tempNode));
            }           
        }
        
        return list;
    }
    
    public TaxonNode getTaxon(){
        TaxonNode taxon = null;
        
        Iterator<Relationship> iterator = node.getRelationships(new NCBITaxonRel(null), Direction.INCOMING).iterator();
        if(iterator.hasNext()){
            taxon = new TaxonNode(iterator.next().getStartNode());
        }
        
        return taxon;
    }
       

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NCBITaxonNode){
            NCBITaxonNode other = (NCBITaxonNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "name = " + getName();
    }

}
