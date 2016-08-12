/*
  # NCBI Taxonomy graph

  This graph includes the whole NCBI taxonomy tree.
  Files used in the importing process can be found [here](ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz)

  Once that information is extracted we are building the tree from the information included in the following files:

  * **nodes.dmp**
  * **names.dmp**

  ## data model

  It simply consists of the vertices of NCBITaxon type plus the hierarchical relationships represented as NCBITaxonParent edges.

  ### NCBITaxons

  We have a `NCBITaxon` vertex which contains property data present for each NCBI taxonomic unit.

  ##### NCBITaxon properties stored

  - id
  - name
  - comment
  - scientific name
  - taxonomic rank
 */
package com.bio4j.model.ncbiTaxonomy;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class NCBITaxonomyGraph<V,E> extends TypedGraph<NCBITaxonomyGraph<V,E>,V,E> {

  public NCBITaxonomyGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final NCBITaxonomyGraph<V,E> self() { return this; }

  /*
    ## Vertices
  */
  /*
    ### Taxon
  */
  public final class Taxon extends Vertex<Taxon> {

    private Taxon(V vertex) { super(vertex, taxon); }
    @Override
    public final Taxon self() { return this; }
  }

  public final TaxonType taxon = new TaxonType();
  public final class TaxonType extends VertexType<Taxon> {

    public final Id id = new Id();
    public final ById byId = new ById();
    public final class Id extends Property<String> implements FromAtMostOne, ToOne {
      private Id() { super(String.class); }
    }
    public final class ById extends UniqueIndex<Id,String> {
      private ById() { super(id); }
    }

    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {
      private Name() { super(String.class); }
    }

    // TODO this should be always defined, right?
    public final TaxonomicRank taxonomicRank = new TaxonomicRank();
    public final class TaxonomicRank extends Property<String> implements FromAny {
      private TaxonomicRank() { super(String.class); }
    }

    @Override
    public final Taxon fromRaw(V vertex) { return new Taxon(vertex); }
  }

  /*
    ## Edges
  */
  /*
    ### Parent
  */
  public final class Parent extends Edge<Taxon, Parent, Taxon> {

    private Parent(E edge) { super(edge, parent); }
    @Override
    public final Parent self() { return this; }
  }

  public final ParentType parent = new ParentType();

  public final class ParentType extends EdgeType<Taxon, Parent, Taxon> implements FromAny, ToAtMostOne {

    private ParentType() { super(taxon, taxon); }

    @Override
    public final Parent fromRaw(E edge) { return new Parent(edge); }
  }
}
