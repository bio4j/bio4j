package com.bio4j.model.refseq;

import java.util.Set;
import java.util.HashSet;

import com.bio4j.model.Module;
import com.bio4j.model.NodeType;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.PropertyType;

import com.bio4j.model.refseq.nodes.*;
import com.bio4j.model.properties.*;
import com.bio4j.model.refseq.relationships.*;

public enum RefSeqModule implements Module {
  
  refseq;

  public static String PKG = "com.bio4j.model.refseq";
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{}};
  public static Set<NodeType> NODE_TYPES = new HashSet<NodeType>() {{
    add(CDS.TYPE);
    add(Gene.TYPE);
    add(GenomeElement.TYPE);
    add(MRNA.TYPE);
    add(MiscRNA.TYPE);
    add(NcRNA.TYPE);
    add(RRNA.TYPE);
    add(TRNA.TYPE);
    add(TmRNA.TYPE);
  }};
  public static Set<RelationshipType> RELATIONSHIP_TYPES = new HashSet<RelationshipType>() {{
    add(HasCDS.TYPE);
    add(HasGene.TYPE);
    add(HasMRNA.TYPE);
    add(HasMiscRNA.TYPE);
    add(HasNcRNA.TYPE);
    add(HasRRNA.TYPE);
    add(HasTRNA.TYPE);
    add(HasTmRNA.TYPE);
  }};

  public static Set<PropertyType> PROPERTY_TYPES = new HashSet<PropertyType>() {{
    // TODO a lot of props here
  }};


  public String pkg() { return PKG; }
  // TODO really no deps here?
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}