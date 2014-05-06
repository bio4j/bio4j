package com.bio4j.model.uniprot_ncbiTaxonomy.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface OrganismNCBITaxon extends Relationship <
  Organism, Organism.Type,
  OrganismNCBITaxon, OrganismNCBITaxon.Type,
  NCBITaxon, NCBITaxon.Type
> {

  public Organism source();
  public NCBITaxon target();

  public static Type TYPE = Type.organismNCBITaxon; 
  public static enum Type implements RelationshipType <
    Organism, Organism.Type,
    OrganismNCBITaxon, OrganismNCBITaxon.Type,
    NCBITaxon, NCBITaxon.Type
  > {

    organismNCBITaxon;

    // TODO: review this
    public Arity arity() { return Arity.manyToMany; }
    
    public Type value() { return organismNCBITaxon; }
    public Organism.Type sourceType() { return Organism.TYPE; }
    public NCBITaxon.Type targetType() { return NCBITaxon.TYPE; }
  }

}
