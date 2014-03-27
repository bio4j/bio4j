package com.bio4j.model.relationships.ncbi;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.nodes.Organism;
import com.bio4j.model.nodes.NCBITaxon;

/**
 *
 * @author Alexey Alekhin <aalekhin@ohnosequences.com>
 */
public interface OrganismNCBITaxon extends Relationship <
  Organism, Organism.type,
  OrganismNCBITaxon, OrganismNCBITaxon.type,
  NCBITaxon, NCBITaxon.type
> {

  enum type implements RelationshipType <
    Organism, Organism.type,
    OrganismNCBITaxon, OrganismNCBITaxon.type,
    NCBITaxon, NCBITaxon.type
  > {
    OrganismNCBITaxon;
    public type value() { return OrganismNCBITaxon; }
  }

}
