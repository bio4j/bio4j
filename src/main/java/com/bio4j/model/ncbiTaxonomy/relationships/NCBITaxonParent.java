package com.bio4j.model.ncbiTaxonomy.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface NCBITaxonParent extends Relationship <
  NCBITaxon, NCBITaxon.type,
  NCBITaxonParent, NCBITaxonParent.type,
  NCBITaxon, NCBITaxon.type
> {

  public static type TYPE = type.ncbiTaxonParent;
  public static enum type implements RelationshipType <
    NCBITaxon, NCBITaxon.type,
    NCBITaxonParent, NCBITaxonParent.type,
    NCBITaxon, NCBITaxon.type
  > {
    ncbiTaxonParent;
    public type value() { return ncbiTaxonParent; }
  }

  @Override
  public NCBITaxon source();
  public NCBITaxon target();
}
