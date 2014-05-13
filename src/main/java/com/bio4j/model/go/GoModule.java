package com.bio4j.model.go;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.Module;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.go.nodes.*;
import com.bio4j.model.properties.*;
import com.bio4j.model.go.relationships.*;

/*

# Gene Ontology module

This includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Taking into account our data-in-edges approach, the modeling is completely straightforward.

### Terms

We have a `Term` relationship which contains property data present for each term. Note that some of these properties are represented as edges.

##### [Essential elements](http://www.geneontology.org/GO.ontology.structure.shtml#essential)

- `id` property of the `Term` rel
- `name` property of the `Term` rel
- `definition` property of the `Term` rel

The `namespace` can be determined once you are in a `term` context. It is represented by relationships (one type per namespace) going out of the term context. There are three of them:

- cellular component
- biological process
- molecular function

##### [Optional extras](http://www.geneontology.org/GO.ontology.structure.shtml#opt)

- `secondary_ids` property of the `Term` rel, an array. I don't see the point of this.
- `synonyms` They are split into
    + `exact`
    + `broad`
    + `narrow`
    + `related`

  We drop all of them but `exact`, and add an index over it.
- `cross_ref` an array of strings, property of the `Term` rel. _TODO is this connected with the corresponding DBs?_
- `comment` a standard text field.
- `subset` an array of strings. Each of them corresponds to a particular GoSlim. Again, this is modeled as relations going to the term context. GoSlims themselves are modeled as rels going out of a `GoSlims` node. See [GO Slim](http://www.geneontology.org/GO.slims.shtml).
- `obsolete` Terms marked as obsolete are dropped.

### GO Relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

- is a
- part of
- has part of
- regulates
    - negatively regulates 
    - positively regulates

## examples

#### all terms from Biological Process

Take the `GoRoot` node, `CellularComponent` rel and then the `Term` relationship.

**Before**, you'd need to either 

- iterate over all terms, retrieve a property, filter
- create an index based on the value of that property

#### terms from two different GoSlims

From `GoRoot` take `GoSlims` then the rel for each GoSlim that you want to pick, then `Term` out of them.

**Before**, you'd need to

- iterate over all terms, retrieve a property, filter
- create a property per GoSlim that you might want to get, index them (independently of any term index that you might have)
*/
public enum GoModule implements Module {

  go;
  
  // package name
  public static String PKG = "com.bio4j.model.go";
  // dependencies
  public static Set<Module> DEPENDENCIES = new HashSet<Module>();
  // node types
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
    add(GoTerm.TYPE);
    add(GoNamespace.TYPE);
    add(GoRoot.TYPE);
  }};
  // relationship types
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(Term.TYPE);
    add(IsA.TYPE); 
    add(PartOf.TYPE); 
    add(HasPartOf.TYPE); 
    add(NegativelyRegulates.TYPE); 
    add(PositivelyRegulates.TYPE); 
    add(Regulates.TYPE);
    add(BiologicalProcess.TYPE);
    add(MolecularFunction.TYPE);
    add(CellularComponent.TYPE);
  }};
  // property types
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // GoTerm
    add(Id.<Term, Term.Type>TYPE(Term.TYPE)); 
    add(Definition.<Term, Term.Type>TYPE(Term.TYPE));
    add(Comment.<Term, Term.Type>TYPE(Term.TYPE));
    add(Obsolete.<Term, Term.Type>TYPE(Term.TYPE));
    add(Name.<Term, Term.Type>TYPE(Term.TYPE));
    add(AlternativeIds.<Term, Term.Type>TYPE(Term.TYPE));
  }};


  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}