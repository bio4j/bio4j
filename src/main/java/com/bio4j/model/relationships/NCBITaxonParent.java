package com.bio4j.model.relationships.ncbi;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.NCBITaxon;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
 */
public interface NCBITaxonParent extends Relationship <
  NCBITaxon, NCBITaxon.type,
  NCBITaxonParent, NCBITaxonParent.type,
  NCBITaxon, NCBITaxon.type
> {

  enum type implements RelationshipType <
    NCBITaxon, NCBITaxon.type,
    NCBITaxonParent, NCBITaxonParent.type,
    NCBITaxon, NCBITaxon.type
  > {
    NCBITaxonParent;
    public type value() { return NCBITaxonParent; }
  }

}
