/*
  # NCBI Taxonomy

  Taxon vertices, with a *parent* edge corresponding to the tree structure. for an up-to-date description of the data see

  - [The NCBI Taxonomy database](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3245000/)
  - [FTP dump readme](ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump_readme.txt)
*/
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class NCBITaxonomyGraph<V,E> extends TypedGraph<NCBITaxonomyGraph<V,E>,V,E> {

  public NCBITaxonomyGraph(UntypedGraph<V,E> graph) { super(graph); }
  @Override public final NCBITaxonomyGraph<V,E> self() { return this; }

  /*
    ## Taxon

  */
  public final class Taxon extends Vertex<Taxon> {

    private Taxon(V vertex) { super(vertex, taxon); }

    @Override public final Taxon self() { return this; }
  }

  public final TaxonType taxon = new TaxonType();
  public final class TaxonType extends VertexType<Taxon> {

    @Override public final Taxon fromRaw(V vertex) { return new Taxon(vertex); }

    /*
      ### ID

      Indexed for unique matches.
    */
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {

      private ID() { super(String.class); }

      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {

        private Index() { super(id); }
      }
    }

    /*
      ### Name

      This is the standard name of the taxon, like *Escherichia coli* or *Bacteria*. Corresponds the the (non-unique) *scientific name* in NCBI taxonomy data.
    */
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }

    /*
      ### Taxonomic rank

      Values are, among others, *species*, *genus*, or *no rank*; see the enum below.
    */
    public final Rank rank = new Rank();
    public final class Rank extends Property<TaxonomicRank> implements FromAny, ToOne {

      private Rank() { super(TaxonomicRank.class); }
    }
  }

  /*
    The set of valid ranks is nowhere documented; this is just an approximation.
  */
  public static enum TaxonomicRank {

    noRank,
    superkingdom,
    kingdom,
    superphylum,
    phylum,
    subphylum,
    clazz, // reserved word
    subclass,
    superclass,
    infraclass,
    order,
    parvorder,
    suborder,
    infraorder,
    family,
    subfamily,
    superfamily,
    tribe,
    subtribe,
    genus,
    subgenus,
    speciesGroup,
    speciesSubgroup,
    species,
    subspecies,
    varietas,
    forma,
    thereIsAnIndeterminateNumberOfRanksAsATaxonomistMayInventANewRankAtWillAtAnyTimeIfTheyFeelThisIsNecessary;
  }

  /*
    ### Parent taxon

    Every taxon *but* the root has *exactly one* parent.
  */
  public final class Parent extends Edge<Taxon, Parent, Taxon> {

    private Parent(E edge) { super(edge, parent); }

    @Override public final Parent self() { return this; }
  }

  public final ParentType parent = new ParentType();
  public final class ParentType extends EdgeType<Taxon, Parent, Taxon> implements FromAny, ToAtMostOne {

    private ParentType() { super(taxon, taxon); }
    @Override public final Parent fromRaw(E edge) { return new Parent(edge); }
  }
}
