package com.bio4j.model.enzymedb;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

// deps
import com.bio4j.model.uniprot.UniProtModule;

import com.bio4j.model.enzymedb.nodes.*;
import com.bio4j.model.enzymedb.relationships.*;
import com.bio4j.model.properties.*;


public enum EnzymeDBModule implements TypedGraph {

  enzymeDB;
  
  // package name
  public static String PKG = "com.bio4j.model.enzymedb";
  // dependencies
  public static Set<TypedGraph> DEPENDENCIES = new HashSet<TypedGraph>() {{
    add(UniProtModule.uniprot);
  }};
  // node types
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
    add(Enzyme.TYPE);
  }};
  // relationship types
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(EnzymaticActivity.TYPE);
  }};
  // property types
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // Enzyme
    add(Id.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(Cofactors.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(OfficialName.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(AlternateNames.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(CatalyticActivity.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(Comment.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE)); 
    add(PrositeCrossReferences.<Enzyme, Enzyme.Type>TYPE(Enzyme.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<TypedGraph> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}
