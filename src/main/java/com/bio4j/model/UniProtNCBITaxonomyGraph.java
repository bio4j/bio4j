/*
  # UniProt NCBI taxonomy annotations

  There are two interesnting pieces of information here:

  1. the organism from which this protein comes
  2. the host, for viruses and the like
*/
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtNCBITaxonomyGraph<V,E> extends LinkGraph<UniProtNCBITaxonomyGraph<V,E>,V,E> {

  public UniProtNCBITaxonomyGraph(UniProtGraph<V,E> uniProtGraph, NCBITaxonomyGraph<V,E> ncbiTaxonomyGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
    this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;
  public final NCBITaxonomyGraph<V,E> ncbiTaxonomyGraph;

  @Override public final UniProtNCBITaxonomyGraph<V,E> self() { return this; }

  public final class Organism extends LinkEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Organism,
    NCBITaxonomyGraph<V,E>, NCBITaxonomyGraph<V,E>.Taxon
  >
  {
    private Organism(E edge) { super(edge, organism); }
    @Override public final Organism self() { return this; }
  }

  public final OrganismType organism = new OrganismType();
  public final class OrganismType extends LinkEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Organism,
    NCBITaxonomyGraph<V,E>, NCBITaxonomyGraph<V,E>.Taxon
  >
  implements FromAny, ToOne {

    private OrganismType() { super(uniProtGraph.protein, ncbiTaxonomyGraph.taxon); }
    @Override public final Organism fromRaw(E edge) { return new Organism(edge); }
  }

  public final class Host extends LinkEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Host,
    NCBITaxonomyGraph<V,E>, NCBITaxonomyGraph<V,E>.Taxon
  >
  {
    private Host(E edge) { super(edge, host); }
    @Override public final Host self() { return this; }
  }

  public final HostType host = new HostType();
  public final class HostType extends LinkEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Host,
    NCBITaxonomyGraph<V,E>, NCBITaxonomyGraph<V,E>.Taxon
  >
  implements FromAny, ToAny {

    private HostType() { super(uniProtGraph.protein, ncbiTaxonomyGraph.taxon); }
    @Override public final Host fromRaw(E edge) { return new Host(edge); }
  }
}
