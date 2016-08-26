
# NCBI Taxonomy graph

This graph models the NCBI taxonomy tree.

> This documentation will be part of the import code, which is going to be in a separate repository

Files used in the importing process can be found [here](ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz)

Once that information is extracted we are building the tree from the information included in the following files:

* **nodes.dmp**
* **names.dmp**


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class NCBITaxonomyGraph<V,E> extends TypedGraph<NCBITaxonomyGraph<V,E>,V,E> {

  public NCBITaxonomyGraph(UntypedGraph<V,E> graph) { super(graph); }
  @Override public final NCBITaxonomyGraph<V,E> self() { return this; }
```


## Vertices

There is a single type of vertex, a taxon.

### Taxon

```java
  public final class Taxon extends Vertex<Taxon> {

    private Taxon(V vertex) { super(vertex, taxon); }
    @Override public final Taxon self() { return this; }
  }

  public final TaxonType taxon = new TaxonType();
  public final class TaxonType extends VertexType<Taxon> {

    @Override public final Taxon fromRaw(V vertex) { return new Taxon(vertex); }
```

#### Id

```java
    public final Id id = new Id();
    public final class Id extends Property<String> implements FromAtMostOne, ToOne {

      private Id() { super(String.class); }
    }
```


#### ById

A unique index for taxa by id.


```java
    public final ById byId = new ById();
    public final class ById extends UniqueIndex<Id, String> {

      private ById() { super(id); }
    }
```


#### Name

This is the standard name of the taxon, like *Escherichia coli* or *Bacteria*.


```java
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }
```


#### Taxonomic rank

Values are, among others, *species*, *genus*, or *no rank*.


```java
    public final TaxonomicRank taxonomicRank = new TaxonomicRank();
    public final class TaxonomicRank extends Property<TaxonomicRanks> implements FromAny, ToOne {

      private TaxonomicRank() { super(TaxonomicRanks.class); }
    }
  }

  public static enum TaxonomicRanks {

    // TODO add everything here
    species,
    genus,
    noRank;
  }
```


## Edges

This graph has only one edge type, `parent`.


### Parent

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
[main/java/com/bio4j/model/ENZYMEGraph.java]: ENZYMEGraph.java.md
[main/java/com/bio4j/model/UniProtNCBITaxonomyGraph.java]: UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/GOGraph.java]: GOGraph.java.md
[main/java/com/bio4j/model/UniProtGOGraph.java]: UniProtGOGraph.java.md