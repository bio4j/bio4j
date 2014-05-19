package com.bio4j.model.go;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

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
public abstract class GoGraph implements TypedGraph {
  
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
  //   add(Id.<Term, Term.Type>TYPE(Term.TYPE)); 
  //   add(Definition.<Term, Term.Type>TYPE(Term.TYPE));
  //   add(Comment.<Term, Term.Type>TYPE(Term.TYPE));
  //   add(Synonym.<Term, Term.Type>TYPE(Term.TYPE));
  //   add(Name.<Term, Term.Type>TYPE(Term.TYPE));
  // }};


  // public String pkg() { return PKG; }
  // public Set<TypedGraph> dependencies() { return DEPENDENCIES; }
  // public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  // public Set<Node.Type> nodeTypes() { return NODE_TYPES; }
  // public Set<Relationship.Type> relationshipTypes() { return RELATIONSHIP_TYPES; }
}
