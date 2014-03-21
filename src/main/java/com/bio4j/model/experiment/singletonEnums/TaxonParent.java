package com.bio4j.model.experiment.singletonEnums;

public interface TaxonParent extends Relationship<TaxonParent.type> {

  enum type implements RelationshipType<type> { 
    taxonParent;
    public type value() { return taxonParent; } 
  }

  // now getType() can only return taxonParent
}

