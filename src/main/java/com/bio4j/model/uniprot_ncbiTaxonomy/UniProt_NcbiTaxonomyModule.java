package com.bio4j.model.uniprot_ncbiTaxonomy;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.PropertyType;

import com.bio4j.model.properties.*;
import com.bio4j.model.uniprot_ncbiTaxonomy.relationships.*;

// deps
import com.bio4j.model.uniprot.UniProtModule;
// import com.bio4j.model.ncbiTaxonomy.NcbiTaxonomyModule;

public enum UniProt_NcbiTaxonomyModule implements TypedGraph {

  uniprot_ncbiTaxonomy;
  
  public static String PKG = "com.bio4j.model.uniprot_ncbiTaxonomy";

  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{}};

  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{

    add(
      OrganismNCBITaxon.TYPE                    
    );
  }};

  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{}};


  public String pkg() { return PKG; }
  public Set<TypedGraph> dependencies() { return new HashSet<TypedGraph>() {{ 
    
      add(UniProtModule.uniprot);
      // add(NcbiTaxonomyModule.ncbiTaxonomy);
    }}; 
  }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }

  // you should implement indexes in subclasses
}
