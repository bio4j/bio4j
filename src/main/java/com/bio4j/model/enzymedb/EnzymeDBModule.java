package com.bio4j.model.enzymedb;

import java.util.Set;
import java.util.HashSet;

import com.bio4j.model.Module;
import com.bio4j.model.NodeType;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.PropertyType;

// deps
import com.bio4j.model.uniprot.UniProtModule;

import com.bio4j.model.enzymedb.nodes.*;
import com.bio4j.model.enzymedb.relationships.*;
import com.bio4j.model.properties.*;


public enum EnzymeDBModule implements Module {

  enzymeDB;
  
  // package name
  public static String PKG = "com.bio4j.model.enzymedb";
  // dependencies
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{
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
    add(new Id.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new Cofactors.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new OfficialName.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new AlternateNames.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new CatalyticActivity.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new Comment.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE)); 
    add(new PrositeCrossReferences.Type<Enzyme, Enzyme.Type>(Enzyme.TYPE));
  }};

  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}