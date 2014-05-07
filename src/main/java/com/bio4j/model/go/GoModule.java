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