package com.bio4j.model.ncbiTaxonomy.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;
import com.bio4j.model.properties.comment;
import com.bio4j.model.properties.taxId;
import com.bio4j.model.properties.scientificName;
import com.bio4j.model.properties.taxonomicRank;
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
  taxId, // TODO what is this? probably should be changed to id
  name,
  comment, // WARNING changed comments to comment
  scientificName,
  taxonomicRank, // WARNING this was rank before
  EmblCode // TODO maybe this should be promoted to a rel

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
