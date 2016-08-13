/*
  # NCBI Taxonomy graph

  This graph models the NCBI taxonomy tree.

  > This documentation will be part of the import code, which is going to be in a separate repository

  Files used in the importing process can be found [here](ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz)

  Once that information is extracted we are building the tree from the information included in the following files:

  * **nodes.dmp**
  * **names.dmp**
 */
package com.bio4j.model.ncbiTaxonomy;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class NCBITaxonomyGraph<V,E> extends TypedGraph<NCBITaxonomyGraph<V,E>,V,E> {

  /*
    ## Vertices

    There is a single type of vertex, a taxon.
  */
  /*
    ### Taxon
  */
  public final class TaxonType extends VertexType<Taxon> {

    /*
      #### id
    */
    public final class Id extends Property<String> implements FromAtMostOne, ToOne {

      private Id() { super(String.class); }
    }
    public final Id id = new Id();

    /*
      #### ById

      An unique index for taxa by id.
    */
    public final class ById extends UniqueIndex<Id,String> {

      private ById() { super(id); }
    }
    public final ById byId = new ById();

    /*
      #### name

      This is the standard name of the taxon, like *Escherichia coli* or *Bacteria*.
    */
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }
    public final Name name = new Name();

    /*
      #### taxonomic rank

      Values are, among others, *species*, *genus*, or *no rank*.
    */
    public final class TaxonomicRank extends Property<String> implements FromAny, ToOne {

      private TaxonomicRank() { super(String.class); }
    }
    public final TaxonomicRank taxonomicRank = new TaxonomicRank();

    @Override
    public final Taxon fromRaw(V vertex) { return new Taxon(vertex); }
  }
  public final TaxonType taxon = new TaxonType();

  public final class Taxon extends Vertex<Taxon> {

    private Taxon(V vertex) { super(vertex, taxon); }
    @Override
    public final Taxon self() { return this; }
  }

  /*
    ## Edges

    This graph has only one edge type, `parent`.
  */
  /*
    ### Parent

    Every taxon *but* the root has *exactly one* parent.
  */
  public final class ParentType extends EdgeType<Taxon, Parent, Taxon> implements FromAny, ToAtMostOne {

    private ParentType() { super(taxon, taxon); }

    @Override
    public final Parent fromRaw(E edge) { return new Parent(edge); }
  }
  public final ParentType parent = new ParentType();

  public final class Parent extends Edge<Taxon, Parent, Taxon> {

    private Parent(E edge) { super(edge, parent); }
    @Override
    public final Parent self() { return this; }
  }

  public NCBITaxonomyGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final NCBITaxonomyGraph<V,E> self() { return this; }
}
