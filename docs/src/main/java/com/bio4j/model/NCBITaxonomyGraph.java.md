
# NCBI Taxonomy

Taxon vertices, with a *parent* edge corresponding to the tree structure. for an up-to-date description of the data see

- [The NCBI Taxonomy database](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC3245000/)
- [FTP dump readme](ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump_readme.txt)


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class NCBITaxonomyGraph<V,E> extends TypedGraph<NCBITaxonomyGraph<V,E>,V,E> {

  public NCBITaxonomyGraph(UntypedGraph<V,E> graph) { super(graph); }
  @Override public final NCBITaxonomyGraph<V,E> self() { return this; }
```


## Taxon



```java
  public final class Taxon extends Vertex<Taxon> {

    private Taxon(V vertex) { super(vertex, taxon); }

    @Override public final Taxon self() { return this; }
  }

  public final TaxonType taxon = new TaxonType();
  public final class TaxonType extends VertexType<Taxon> {

    @Override public final Taxon fromRaw(V vertex) { return new Taxon(vertex); }
```


### ID

Indexed for unique matches.


```java
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {

      private ID() { super(String.class); }

      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID, String> {

        private Index() { super(id); }
      }
    }
```


### Name

This is the standard name of the taxon, like *Escherichia coli* or *Bacteria*. Corresponds the the (non-unique) *scientific name* in NCBI taxonomy data.


```java
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }
```


### Taxonomic rank

Values are, among others, *species*, *genus*, or *no rank*; see the enum below.


```java
    public final TaxonomicRank taxonomicRank = new TaxonomicRank();
    public final class TaxonomicRank extends Property<TaxonomicRanks> implements FromAny, ToOne {

      private TaxonomicRank() { super(TaxonomicRanks.class); }
    }
  }
```


The set of valid ranks is nowhere documented; this is just an approximation.


```java
  public static enum TaxonomicRanks {

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
```


### Parent taxon

Every taxon *but* the root has *exactly one* parent.


```java
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

```




[main/java/com/bio4j/model/UniProtGraph.java]: UniProtGraph.java.md
[main/java/com/bio4j/model/UniProtENZYMEGraph.java]: UniProtENZYMEGraph.java.md
[main/java/com/bio4j/model/NCBITaxonomyGraph.java]: NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/UniRefGraph.java]: UniRefGraph.java.md
[main/java/com/bio4j/model/ENZYMEGraph.java]: ENZYMEGraph.java.md
[main/java/com/bio4j/model/UniProtNCBITaxonomyGraph.java]: UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/GOGraph.java]: GOGraph.java.md
[main/java/com/bio4j/model/UniProtGOGraph.java]: UniProtGOGraph.java.md