package com.bio4j.model.experiment.superabstract;

public interface TaxonParent extends Relationship<
  Taxon, Taxon.type,
  TaxonParent, TaxonParent.type,
  Taxon, Taxon.type
> {
    
  enum type implements RelationshipType<
    Taxon, Taxon.type,
    TaxonParent, TaxonParent.type,
    Taxon, Taxon.type
  > {

    taxonParent;

    public type value() { return taxonParent; }
  }

  // not needed, but it's here just so that you can see how this can only return `taxonParent`
  public type getType();

  public Taxon getSource();
  public Taxon getTarget();
}