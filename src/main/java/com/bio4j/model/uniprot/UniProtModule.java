package com.bio4j.model.uniprot;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.Module;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

public enum UniProtModule implements Module {

  uniprot;

  public static String PKG = "com.bio4j.model.uniprot";

  public String pkg() { return null; }
  public Set<Module> dependencies() { return null; }
  public Set<PropertyType> propertyTypes() { return null; }
  public Set<NodeType> nodeTypes() { return null; }
  public Set<RelationshipType> relationshipTypes() { return null; }
}