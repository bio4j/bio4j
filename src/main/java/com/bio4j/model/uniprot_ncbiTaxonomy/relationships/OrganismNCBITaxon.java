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
  Organism, Organism.type,
  OrganismNCBITaxon, OrganismNCBITaxon.type,
  NCBITaxon, NCBITaxon.type
> {

  public static enum type implements RelationshipType <
    Organism, Organism.type,
    OrganismNCBITaxon, OrganismNCBITaxon.type,
    NCBITaxon, NCBITaxon.type
  > {
    OrganismNCBITaxon;
    public type value() { return OrganismNCBITaxon; }
    // TODO: review this
    public arity arity() { return arity.manyToMany; }
  }

}
