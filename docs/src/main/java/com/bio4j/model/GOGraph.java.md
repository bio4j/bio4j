
# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

Basically there are `Term` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class GOGraph<V,E> extends TypedGraph<GOGraph<V,E>,V,E> {

  public GOGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final GOGraph<V,E> self() { return this; }
```


## Terms

We have a `Term` vertex which contains property data present for each term. Do note though that some of these properties are represented as edges. We keep all data corresponding to what is called in the GO docs [*essential elements*](http://www.geneontology.org/GO.ontology.structure.shtml#essential). The `namespace` is represented by relationships (one type per namespace) going out of the term node; see below.

About the so-called [*optional extras*](http://www.geneontology.org/GO.ontology.structure.shtml#opt):

- `comment`: a standard text field.
- `obsolete`: GoTerms marked as obsolete shoud be **dropped**.


```java
  public final class Term extends Vertex<Term> {

    private Term(V vertex) { super(vertex, term); }

    @Override
    public final Term self() { return this; }
  }

  public final TermType term = new TermType();
  public final class TermType extends VertexType<Term> {

    @Override
    public final Term fromRaw(V vertex) { return new Term(vertex); }
```


### ID

The unique id of a term, indexed.


```java
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {

      private ID() { super(String.class); }

      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID,String> {

        private Index() { super(id); }
      }
    }

    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }

    public final Definition definition = new Definition();
    public final class Definition extends Property<String> implements FromAny, ToOne {

      private Definition() { super(String.class); }
    }

    public final Comment comment = new Comment();
    public final class Comment extends Property<String> implements FromAny {

      private Comment() { super(String.class); }
    }

    public final Subontology subOntology = new Subontology();
    public final class Subontology extends Property<Subontologies> implements FromAny, ToOne {

      private Subontology() { super(Subontologies.class); }
    }
  }
```


### Subontologies

There are three of them. Every term is a member of exactly one of them.


```java
  public static enum Subontologies {

    cellularComponent,
    biologicalProcess,
    molecularFunction;
  }
```


## Ontology relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have those which are part of the filtered ontology:

- is a
- part of
- regulates
  - negatively regulates
  - positively regulates


```java
  public final class IsA extends Edge<Term, IsA, Term> {

    private IsA(E edge) { super(edge, isA); }

    @Override
    public final IsA self() { return this; }
  }

  public final IsAType isA = new IsAType();
  public final class IsAType extends EdgeType<Term, IsA, Term> implements FromAny, ToAny {

    private IsAType() { super(term, term); }

    @Override
    public final IsA fromRaw(E edge) { return new IsA(edge); }
  }

  public final class PartOf extends Edge<Term, PartOf, Term> {

    private PartOf(E edge) { super(edge, partOf); }

    @Override
    public final PartOf self() { return this; }
  }

  public final PartOfType partOf = new PartOfType();
  public final class PartOfType extends EdgeType<Term, PartOf, Term> implements FromAny, ToAny {

    private PartOfType() { super(term, term); }

    @Override
    public final PartOf fromRaw(E edge) { return new PartOf(edge); }
  }

  public final class Regulates extends Edge<Term, Regulates, Term> {

    private Regulates(E edge) { super(edge, regulates); }

    @Override
    public final Regulates self() { return this; }
  }

  public final RegulatesType regulates = new RegulatesType();
  public final class RegulatesType extends EdgeType<Term, Regulates, Term> implements FromAny, ToAny {

    private RegulatesType() { super(term, term); }

    @Override
    public final Regulates fromRaw(E edge) { return new Regulates(edge); }
  }

  public final class NegativelyRegulates extends Edge<Term, NegativelyRegulates, Term> {

    private NegativelyRegulates(E edge) { super(edge, negativelyRegulates); }

    @Override
    public final NegativelyRegulates self() { return this; }
  }

  public final NegativelyRegulatesType negativelyRegulates = new NegativelyRegulatesType();
  public final class NegativelyRegulatesType extends EdgeType<Term, NegativelyRegulates, Term> implements FromAny, ToAny {

    private NegativelyRegulatesType() { super(term, term); }

    @Override
    public final NegativelyRegulates fromRaw(E edge) { return new NegativelyRegulates(edge); }
  }

  public final class PositivelyRegulates extends Edge<Term, PositivelyRegulates, Term> {

    private PositivelyRegulates(E edge) { super(edge, positivelyRegulates); }

    @Override
    public final PositivelyRegulates self() { return this; }
  }

  public final PositivelyRegulatesType positivelyRegulates = new PositivelyRegulatesType();
  public final class PositivelyRegulatesType extends EdgeType<Term, PositivelyRegulates, Term> implements FromAny, ToAny {

    private PositivelyRegulatesType() { super(term, term); }

    @Override
    public final PositivelyRegulates fromRaw(E edge) { return new PositivelyRegulates(edge); }
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