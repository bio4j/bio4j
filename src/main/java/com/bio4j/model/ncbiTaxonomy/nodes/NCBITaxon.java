package com.bio4j.model.ncbiTaxonomy.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;

import com.bio4j.model.uniprot.nodes.Taxon;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NCBITaxon extends Node<NCBITaxon, NCBITaxon.type>,
  name 
{
  
  public static type TYPE = type.ncbiTaxon;  
  public static enum type implements NodeType<NCBITaxon, NCBITaxon.type> {
    ncbiTaxon;
    public type value() { return ncbiTaxon; }
  }

  // properties
  // TODO: move them to the interface def
  public String taxId();
  public String rank();
  public String emblCode();
  public String comments();
  public String scientificName();

  // other rels
  public NCBITaxon parent();
  public List<NCBITaxon> getChildren();    
  public Taxon getTaxon();  
}
