package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.MRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface MRNAs extends Relationship <
  GenomeElement, GenomeElement.Type,
  MRNAs,  MRNAs.Type,
  MRNA, MRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public MRNA target();

  public static Type TYPE = Type.mRNAs;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    MRNAs,  MRNAs.Type,
    MRNA, MRNA.Type
  > {
    mRNAs;
    public Type value() { return mRNAs; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public MRNA.Type targetType() { return MRNA.TYPE; }
  }
}
