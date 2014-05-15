package com.bio4j.model.proteinInteractions;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.properties.*;
import com.bio4j.model.proteinInteractions.relationships.*;

// deps
import com.bio4j.model.uniprot.UniProtModule;
import com.bio4j.model.isoforms.IsoformsModule;

public enum ProteinInteractionsModule implements TypedGraph {

  proteinInteractions;
  
  public static String PKG = "com.bio4j.model.proteinInteractions";
  // dependencies
  public static Set<TypedGraph> DEPENDENCIES = new HashSet<TypedGraph>() {{ 
    add(UniProtModule.uniprot);
    add(IsoformsModule.isoforms);
  }}; 
  // nodes
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{}};
  // rels
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(ProteinIsoformInteraction.TYPE);
    add(ProteinProteinInteraction.TYPE);
  }};
  // properties
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    add(Experiments.<ProteinProteinInteraction,ProteinProteinInteraction.Type>TYPE(ProteinProteinInteraction.TYPE));
    add(Experiments.<ProteinIsoformInteraction,ProteinIsoformInteraction.Type>TYPE(ProteinIsoformInteraction.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<TypedGraph> dependencies() { return new HashSet<TypedGraph>() {{ 

      add(UniProtModule.uniprot);
      // add(IsoformsModule.isoforms);
    }}; 
  }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }

  // you should implement indexes in subclasses
}
