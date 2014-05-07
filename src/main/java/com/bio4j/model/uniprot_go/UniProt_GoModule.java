package com.bio4j.model.uniprot_go;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.Module;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.properties.*;
import com.bio4j.model.uniprot_go.relationships.*;

// deps
import com.bio4j.model.uniprot.UniProtModule;
import com.bio4j.model.go.GoModule;

public enum UniProt_GoModule implements Module {

  uniprot_go;
  
  public static String PKG = "com.bio4j.model.uniprot_go";
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{   
    add(UniProtModule.uniprot);
    add(GoModule.go);
  }}; 
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{}};
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(GoAnnotation.TYPE);
  }};
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    add(Evidence.<GoAnnotation, GoAnnotation.Type>TYPE(GoAnnotation.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}