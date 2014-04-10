package com.bio4j.model.ncbiTaxonomy.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.TaxId;
import com.bio4j.model.properties.ScientificName;
import com.bio4j.model.properties.TaxonomicRank;
import com.bio4j.model.properties.EmblCode;

// relationships
import com.bio4j.model.ncbiTaxonomy.relationships.Parent;

import com.bio4j.model.uniprot.nodes.Taxon;


/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface NCBITaxon extends Node<NCBITaxon, NCBITaxon.type>,
  
  // properties
  TaxId<NCBITaxon, NCBITaxon.type>, // TODO what is this? probably should be changed to id
  Name<NCBITaxon, NCBITaxon.type>,
  Comment<NCBITaxon, NCBITaxon.type>, // WARNING changed comments to comment
  ScientificName<NCBITaxon, NCBITaxon.type>,
  TaxonomicRank<NCBITaxon, NCBITaxon.type>, // WARNING this was rank before
  EmblCode<NCBITaxon, NCBITaxon.type> // TODO maybe this should be promoted to a rel

{
  
  // parent
  // incoming
  public List<? extends Parent> in_parent();
  public List<? extends Taxon> in_parent_nodes();
  // outgoing
  public Parent out_parent();
  public Taxon out_parent_nodes();

  // TODO what is this??
  public Taxon taxon();

  public static type TYPE = type.ncbiTaxon;  
  public static enum type implements NodeType<NCBITaxon, NCBITaxon.type> {
    ncbiTaxon;
    public type value() { return ncbiTaxon; }
  }
}
