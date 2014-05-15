package com.bio4j.model.uniprot;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

public enum UniProtModule implements TypedGraph {

  uniprot;

  public static String PKG = "com.bio4j.model.uniprot";

  public String pkg() { return PKG; }
  public Set<TypedGraph> dependencies() { return null; }
  public Set<PropertyType> propertyTypes() { return null; }
  public Set<NodeType> nodeTypes() { return null; }
  public Set<RelationshipType> relationshipTypes() { return null; }
}
