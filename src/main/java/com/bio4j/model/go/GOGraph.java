/*

# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Basically there are `GoTerm` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.

### GoTerms

We have a `GoTerm` vertex which contains property data present for each term. Do note though that some of these properties are represented as edges.

##### [Essential elements](http://www.geneontology.org/GO.ontology.structure.shtml#essential)

- `id` property of the `GoTerm` rel
- `name` property of the `GoTerm` rel
- `definition` property of the `GoTerm` rel

The `namespace` is represented by relationships (one type per namespace) going out of the term node. There are three of them:

- cellular component
- biological process
- molecular function

##### [Optional extras](http://www.geneontology.org/GO.ontology.structure.shtml#opt)

- `secondary_ids` We drop this. It is just legacy data with no meaning.
- `synonyms` They are split into
  + `exact`
  + `broad`
  + `narrow`
  + `related`

  We drop **all** of them **but** `exact`, and add an index over it.
- `cross_ref` an array of strings, property of the `GoTerm` rel. _TODO is this connected with the corresponding DBs?_
- `comment` a standard text field.
- `subset` an array of strings. Each of them corresponds to a particular GoSlim. As with namespaces, this is modeled as relations going from each term node to a `GoSlims` node. See [GO Slim](http://www.geneontology.org/GO.slims.shtml).
- `obsolete` GoTerms marked as obsolete shoud be **dropped**.

### GO Relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

- is a
- part of
- has part of
- regulates
  - negatively regulates
  - positively regulates

*/
package com.bio4j.model.go;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class GOGraph<V,E> extends TypedGraph<GOGraph<V,E>,V,E> {

  public GOGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final GOGraph<V,E> self() { return this; }

  public final TermType term = new TermType();
  public final class TermType extends VertexType<Term> {

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

    public final Definition definition = new Definition();
    public final class Definition extends Property<String> implements FromAny, ToOne {
      private Definition() { super(String.class); }
    }

    public final Comment comment = new Comment();
    public final class Comment extends Property<String> implements FromAny {
      private Comment() { super(String.class); }
    }

    public final Synonyms synonyms = new Synonyms();
    public final class Synonyms extends Property<String[]> implements FromAny {
      private Synonyms() { super(String[].class); }
    }

    @Override
    public final Term fromRaw(V vertex) { return new Term(vertex); }
  }

  public final SlimsType slims = new SlimsType();
  public final class SlimsType extends VertexType<Slims> {

    @Override
    public final Slims fromRaw(V vertex) { return new Slims(vertex); }
  }

  public final SubOntologiesType subOntologies = new SubOntologiesType();
  public final class SubOntologiesType extends VertexType<SubOntologies> {

    public final Name name = new Name();
    public final ByName ByName = new ByName();
    public final class Name extends Property<String> implements FromAtMostOne, ToOne {
      private Name() { super(String.class); }
    }
    public final class ByName extends UniqueIndex<Name,String> {
      private ByName() { super(name); }
    }

    @Override
    public final SubOntologies fromRaw(V vertex) { return new SubOntologies(vertex); }
  }

  public final PartOfType partOf = new PartOfType();
  public final class PartOfType extends EdgeType<Term, PartOf, Term> implements FromAny, ToAny {

    private PartOfType() { super(term, term); }
    @Override
    public final PartOf fromRaw(E edge) { return new PartOf(edge); }
  }
  public final IsAType isA = new IsAType();
  public final class IsAType extends EdgeType<Term, IsA, Term> implements FromAny, ToAny {
    private IsAType() { super(term, term); }
    @Override
    public final IsA fromRaw(E edge) { return new IsA(edge); }
  }
  public final HasPartOfType hasPartOf = new HasPartOfType();
  public final class HasPartOfType extends EdgeType<Term, HasPartOf, Term> implements FromAny, ToAny {
    private HasPartOfType() { super(term, term); }
    @Override
    public final HasPartOf fromRaw(E edge) { return new HasPartOf(edge); }
  }
  public final RegulatesType regulates = new RegulatesType();
  public final class RegulatesType extends EdgeType<Term, Regulates, Term> implements FromAny, ToAny {
    private RegulatesType() { super(term, term); }
    @Override
    public final Regulates fromRaw(E edge) { return new Regulates(edge); }
  }
  public final NegativelyRegulatesType negativelyRegulates = new NegativelyRegulatesType();
  public final class NegativelyRegulatesType extends EdgeType<Term, NegativelyRegulates, Term> implements FromAny, ToAny {
    private NegativelyRegulatesType() { super(term, term); }
    @Override
    public final NegativelyRegulates fromRaw(E edge) { return new NegativelyRegulates(edge); }
  }
  public final PositivelyRegulatesType positivelyRegulates = new PositivelyRegulatesType();
  public final class PositivelyRegulatesType extends EdgeType<Term, PositivelyRegulates, Term> implements FromAny, ToAny {
    private PositivelyRegulatesType() { super(term, term); }
    @Override
    public final PositivelyRegulates fromRaw(E edge) { return new PositivelyRegulates(edge); }
  }

  public final SubOntologyType subOntology = new SubOntologyType();
  public final class SubOntologyType extends EdgeType<Term, SubOntology, SubOntologies> implements FromAny, ToOne {

    private SubOntologyType() { super(term, subOntologies); }
    @Override
    public final SubOntology fromRaw(E edge) { return new SubOntology(edge); }
  }


  /*
    ## Auxiliary classes

  */
  public final class PartOf extends Edge<Term, PartOf, Term> {
    private PartOf(E edge) { super(edge, partOf); }
    @Override
    public final PartOf self() { return this; }
  }
  public final class IsA extends Edge<Term, IsA, Term> {
    private IsA(E edge) { super(edge, isA); }
    @Override
    public final IsA self() { return this; }
  }
  public final class HasPartOf extends Edge<Term, HasPartOf, Term> {
    private HasPartOf(E edge) { super(edge, hasPartOf); }
    @Override
    public final HasPartOf self() { return this; }
  }
  public final class Regulates extends Edge<Term, Regulates, Term> {
    private Regulates(E edge) { super(edge, regulates); }
    @Override
    public final Regulates self() { return this; }
  }
  public final class NegativelyRegulates extends Edge<Term, NegativelyRegulates, Term> {
    private NegativelyRegulates(E edge) { super(edge, negativelyRegulates); }
    @Override
    public final NegativelyRegulates self() { return this; }
  }
  public final class PositivelyRegulates extends Edge<Term, PositivelyRegulates, Term> {
    private PositivelyRegulates(E edge) { super(edge, positivelyRegulates); }
    @Override
    public final PositivelyRegulates self() { return this; }
  }

  public final class SubOntology extends Edge<Term, SubOntology, SubOntologies> {
    private SubOntology(E edge) { super(edge, subOntology); }
    @Override
    public final SubOntology self() { return this; }
  }

  public final class Term extends Vertex<Term> {
    private Term(V vertex) { super(vertex, term); }
    @Override
    public final Term self() { return this; }
  }

  public final class Slims extends Vertex<Slims> {
    private Slims(V vertex) { super(vertex, slims); }
    @Override
    public final Slims self() { return this; }
  }

  public final class SubOntologies extends Vertex<SubOntologies> {
    private SubOntologies(V vertex) { super(vertex, subOntologies); }
    @Override
    public final SubOntologies self() { return this; }
  }
}
