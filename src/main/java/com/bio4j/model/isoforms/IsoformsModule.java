package com.bio4j.model.isoforms;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.Module;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.isoforms.nodes.*;
import com.bio4j.model.isoforms.relationships.*;
import com.bio4j.model.properties.*;

import com.bio4j.model.uniprot.UniProtModule;


public enum IsoformsModule implements Module {

  isoforms;
  
  // package name
  public static String PKG = "com.bio4j.model.isoforms";
  // dependencies
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{
    add(UniProtModule.uniprot);
    // TODO something else?
  }};
  // node types
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
    add(Isoform.TYPE);
    add(AlternativeProduct.TYPE);
  }};
  // relationship types
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(IsoformEventGenerator.TYPE);
    // TODO add the others
  }};
  // property types
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // Isoform
    add(Id.<Isoform, Isoform.Type>TYPE(Isoform.TYPE));
    add(Name.<Isoform, Isoform.Type>TYPE(Isoform.TYPE));
    add(Sequence.<Isoform, Isoform.Type>TYPE(Isoform.TYPE));
    add(Note.<Isoform, Isoform.Type>TYPE(Isoform.TYPE));
    // AlternativeProduct
    add(Name.<AlternativeProduct, AlternativeProduct.Type>TYPE(AlternativeProduct.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}