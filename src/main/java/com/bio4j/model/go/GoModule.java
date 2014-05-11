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

  - `secondary_ids` property of the `Term` rel, an array.
  - `synonyms` ?? _maybe we could encode the types as rels?_
  - `cross_ref` an array of strings, property of the `Term` rel. _TODO improve this_
  - `comment`
  - `subset` an array of strings. _TODO this should be a rel to a node representing the corresponding [GO Slim](http://www.geneontology.org/GO.slims.shtml)_
  - `obsolete` a relationship from the term context.

  ### GO Relationships

  See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

  - is a
  - part of
  - has part of
  - regulates
      - negatively regulates 
      - positively regulates
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
  }};
  // relationship types
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(IsA.TYPE); 
    add(PartOf.TYPE); 
    add(HasPartOf.TYPE); 
    add(NegativelyRegulates.TYPE); 
    add(PositivelyRegulates.TYPE); 
    add(Regulates.TYPE);
  }};
  // property types
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // GoTerm
    add(Id.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE)); 
    add(Definition.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE));
    add(Comment.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE));
    add(Obsolete.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE));
    add(Name.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE));
    add(AlternativeIds.<GoTerm, GoTerm.Type>TYPE(GoTerm.TYPE));
  }};


  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}