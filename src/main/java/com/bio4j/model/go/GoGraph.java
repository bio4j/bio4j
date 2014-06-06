package com.bio4j.model.go;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.Relationship;

import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.relationships.*;

/*

# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Basically there are `Term` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.

### Terms

We have a `Term` node which contains property data present for each term. Do note though that some of these properties are represented as edges.

##### [Essential elements](http://www.geneontology.org/GO.ontology.structure.shtml#essential)

- `id` property of the `Term` rel
- `name` property of the `Term` rel
- `definition` property of the `Term` rel

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
- `cross_ref` an array of strings, property of the `Term` rel. _TODO is this connected with the corresponding DBs?_
- `comment` a standard text field.
- `subset` an array of strings. Each of them corresponds to a particular GoSlim. As with namespaces, this is modeled as relations going from each term node to a `GoSlims` node. See [GO Slim](http://www.geneontology.org/GO.slims.shtml).
- `obsolete` Terms marked as obsolete are **dropped**.

### GO Relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

- is a
- part of
- has part of
- regulates
    - negatively regulates 
    - positively regulates

## examples

_TODO_
*/
public interface GoGraph {

    // Override to a precise type
    // public GoSlimsType<N,NT> GoSlims();
    // Node types
    public static interface GoSlimsType<
            N extends GoSlims<N, NT>,
            NT extends GoSlimsType<N, NT>
            >
            extends Node.Type<N, NT> {
    }

    public static interface TermType<
            N extends Term<N, NT>,
            NT extends TermType<N, NT>
            >
            extends Node.Type<N, NT> {
    }

    public static interface SubOntologiesType<
            N extends SubOntologies<N, NT>,
            NT extends SubOntologiesType<N, NT>
            >
            extends Node.Type<N, NT> {
    }


    // relationship types
    public static interface BiologicalProcessType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends BiologicalProcess<S, ST, R, RT, T, TT>, RT extends BiologicalProcessType<S, ST, R, RT, T, TT>,
            T extends SubOntologies<T, TT>, TT extends SubOntologiesType<T, TT>
            >
            extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
    }

    public static interface MolecularFunctionType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends MolecularFunction<S, ST, R, RT, T, TT>, RT extends MolecularFunctionType<S, ST, R, RT, T, TT>,
            T extends SubOntologies<T, TT>, TT extends SubOntologiesType<T, TT>
            >
            extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
    }

    public static interface CellularComponentType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends CellularComponent<S, ST, R, RT, T, TT>, RT extends CellularComponentType<S, ST, R, RT, T, TT>,
            T extends SubOntologies<T, TT>, TT extends SubOntologiesType<T, TT>
            >
            extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
    }

    interface HasPartOfType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends HasPartOf<S, ST, R, RT, T, TT>, RT extends HasPartOfType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    public static interface IsAType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends IsA<S, ST, R, RT, T, TT>, RT extends IsAType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    interface NegativelyRegulatesType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends NegativelyRegulates<S, ST, R, RT, T, TT>, RT extends NegativelyRegulatesType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    interface PartOfType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends PartOf<S, ST, R, RT, T, TT>, RT extends PartOfType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    interface RegulatesType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends Regulates<S, ST, R, RT, T, TT>, RT extends RegulatesType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    interface PositivelyRegulatesType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends PositivelyRegulates<S, ST, R, RT, T, TT>, RT extends PositivelyRegulatesType<S, ST, R, RT, T, TT>,
            T extends Term<T, TT>, TT extends TermType<T, TT>
            >
            extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
    }

    interface SubOntologyType<
            S extends Term<S, ST>, ST extends TermType<S, ST>,
            R extends SubOntology<S, ST, R, RT, T, TT>, RT extends SubOntologyType<S, ST, R, RT, T, TT>,
            T extends SubOntologies<T, TT>, TT extends SubOntologiesType<T, TT>
            >
            extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
    }

    // // package name
    // public static String PKG = "com.bio4j.model.go";
    // // dependencies
    // public static Set<TypedGraph> DEPENDENCIES = new HashSet<TypedGraph>();
    // // node types
    // public static Set<Node.Type> NODE_TYPES = new HashSet<Node.Type>() {{
    //   add(Term.TYPE);
    //   add(SubOntologies.TYPE);
    //   add(GoSlims.TYPE);
    // }};
    // // relationship types
    // public static Set<Relationship.Type> RELATIONSHIP_TYPES = new HashSet<Relationship.Type>() {{
    //   add(IsA.TYPE);
    //   add(PartOf.TYPE);
    //   add(HasPartOf.TYPE);
    //   add(NegativelyRegulates.TYPE);
    //   add(PositivelyRegulates.TYPE);
    //   add(Regulates.TYPE);
    //   // subontologies
    //   add(BiologicalProcess.TYPE);
    //   add(MolecularFunction.TYPE);
    //   add(CellularComponent.TYPE);
    //   // TODO GoSlims
    // }};
    // // property types
    // public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    //   // Term
    //   add(Id.<Term, TermType>TYPE(Term.TYPE));
    //   add(Definition.<Term, TermType>TYPE(Term.TYPE));
    //   add(Comment.<Term, TermType>TYPE(Term.TYPE));
    //   add(Synonym.<Term, TermType>TYPE(Term.TYPE));
    //   add(Name.<Term, TermType>TYPE(Term.TYPE));
    // }};


    // public String pkg() { return PKG; }
    // public Set<TypedGraph> dependencies() { return DEPENDENCIES; }
    // public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
    // public Set<Node.Type> nodeTypes() { return NODE_TYPES; }
    // public Set<Relationship.Type> relationshipTypes() { return RELATIONSHIP_TYPES; }
}
