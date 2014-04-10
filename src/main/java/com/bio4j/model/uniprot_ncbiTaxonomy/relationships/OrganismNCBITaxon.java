package com.bio4j.model.uniprot_ncbiTaxonomy.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface OrganismNCBITaxon extends Relationship <
  Organism, Organism.Type,
  OrganismNCBITaxon, OrganismNCBITaxon.Type,
  NCBITaxon, NCBITaxon.Type
> {

  public static enum Type implements RelationshipType <
    Organism, Organism.Type,
    OrganismNCBITaxon, OrganismNCBITaxon.Type,
    NCBITaxon, NCBITaxon.Type
  > {
    OrganismNCBITaxon;
    public Type value() { return OrganismNCBITaxon; }
    // TODO: review this
    public Arity arity() { return Arity.manyToMany; }
    public Organism.Type sourceType() { return Organism.TYPE; }
    public NCBITaxon.Type targetType() { return NCBITaxon.TYPE; }
  }

}
