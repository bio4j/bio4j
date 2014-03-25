package com.bio4j.model.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.nodes.Taxon;

public interface TaxonParent extends Relationship <
  Taxon, Taxon.type,
  TaxonParent, TaxonParent.type,
  Taxon, Taxon.type
> {
    
  enum type implements RelationshipType <
    Taxon, Taxon.type,
    TaxonParent, TaxonParent.type,
    Taxon, Taxon.type
  > {

    taxonParent;
    public type value() { return taxonParent; }
  }

}