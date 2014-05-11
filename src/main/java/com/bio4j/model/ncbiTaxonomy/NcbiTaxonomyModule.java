package com.bio4j.model.ncbiTaxonomy;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.Module;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.ncbiTaxonomy.nodes.*;
import com.bio4j.model.ncbiTaxonomy.relationships.*;
import com.bio4j.model.properties.*;

import com.bio4j.model.uniprot.UniProtModule;


public enum NcbiTaxonomyModule implements Module {

  ncbiTaxonomy;
  
  // package name
  public static String PKG = "com.bio4j.model.ncbiTaxonomy";
  // dependencies
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{
   // TODO no deps?
  }};
  // node types
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
    add(NCBITaxon.TYPE);
  }};
  // relationship types
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(Parent.TYPE);
  }};
  // property types
  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // NcbiTaxon
    add(TaxId.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
    add(Name.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
    add(Comment.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
    add(ScientificName.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
    add(TaxonomicRank.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
    add(EmblCode.<NCBITaxon, NCBITaxon.Type>TYPE(NCBITaxon.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}