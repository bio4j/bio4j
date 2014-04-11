package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.isoforms.nodes.Isoform;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinIsoform extends Relationship <

  Protein, Protein.Type,
  ProteinIsoform, ProteinIsoform.Type,
  Isoform, Isoform.Type

> {
    
  public Protein source();
  public Isoform target();


  public static Type TYPE = Type.proteinIsoform;

  public static enum Type implements RelationshipType <

    Protein, Protein.Type,
    ProteinIsoform, ProteinIsoform.Type,
    Isoform, Isoform.Type
  
  > {

    proteinIsoform;

    public Arity arity() { return Arity.manyToMany; }

    public Type value() { return proteinIsoform; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Isoform.Type targetType() { return Isoform.TYPE; }
  }


}
