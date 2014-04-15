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
  public static Set<Module> DEPENDENCIES = new HashSet<Module>() {{}}; // TODO review this
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
    // CDS
    add(Note.<CDS,CDS.Type>TYPE(CDS.TYPE));
    add(Positions.<CDS,CDS.Type>TYPE(CDS.TYPE));
    // Gene
    add(Note.<Gene,Gene.Type>TYPE(Gene.TYPE));
    add(Positions.<Gene,Gene.Type>TYPE(Gene.TYPE));
    // MRNA
    add(Note.<MRNA,MRNA.Type>TYPE(MRNA.TYPE));
    add(Positions.<MRNA,MRNA.Type>TYPE(MRNA.TYPE));
    // MiscRNA
    add(Note.<MiscRNA,MiscRNA.Type>TYPE(MiscRNA.TYPE));
    add(Positions.<MiscRNA,MiscRNA.Type>TYPE(MiscRNA.TYPE));
    // NcRNA
    add(Note.<NcRNA,NcRNA.Type>TYPE(NcRNA.TYPE));
    add(Positions.<NcRNA,NcRNA.Type>TYPE(NcRNA.TYPE));
    // RRNA
    add(Note.<RRNA,RRNA.Type>TYPE(RRNA.TYPE));
    add(Positions.<RRNA,RRNA.Type>TYPE(RRNA.TYPE));
    // TRNA
    add(Note.<TRNA,TRNA.Type>TYPE(TRNA.TYPE));
    add(Positions.<TRNA,TRNA.Type>TYPE(TRNA.TYPE));
    // TmRNA
    add(Note.<TmRNA,TmRNA.Type>TYPE(TmRNA.TYPE));
    add(Positions.<TmRNA,TmRNA.Type>TYPE(TmRNA.TYPE));
  }};


  public String pkg() { return PKG; }
  public Set<Module> dependencies() { return DEPENDENCIES; }
  public Set<PropertyType> propertyTypes() { return PROPERTY_TYPES; }
  public Set<NodeType> nodeTypes() { return NODE_TYPES; }
  public Set<RelationshipType> relationshipTypes() { return RELATIONSHIP_TYPES; }
}