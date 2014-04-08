package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.uniprot.nodes.Taxon;

public interface TaxonParent extends Relationship <
  Taxon, Taxon.type,
  TaxonParent, TaxonParent.type,
  Taxon, Taxon.type
> {
  
  public static type TYPE = type.INSTANCE;   
  public static enum type implements RelationshipType <
    Taxon, Taxon.type,
    TaxonParent, TaxonParent.type,
    Taxon, Taxon.type
  > {

    taxonParent;
    public static type INSTANCE = taxonParent;
    public type value() { return taxonParent; }
    public arity arity() { return arity.manyToOne; }
    public Taxon.type sourceType() { return Taxon.TYPE; }
    public Taxon.type targetType() { return Taxon.TYPE; }
  }
}