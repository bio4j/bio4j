package com.bio4j.model.ncbiTaxonomy.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.name;

import com.bio4j.model.uniprot.nodes.Taxon;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NCBITaxon extends Node<NCBITaxon, NCBITaxon.type>,
  name 
{
    
  public static enum type implements NodeType<NCBITaxon, NCBITaxon.type> {
    ncbiTaxon;
    public type value() { return ncbiTaxon; }
  }

  //----------------GETTERS---------------------
  public String getTaxId();
  public String getRank();
  public String getEmblCode();
  public String getComments();
  public String getScientificName();
  public NCBITaxon getParent();
  public List<NCBITaxon> getChildren();    
  public Taxon getTaxon();  
}
