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
  GenomeElement, GenomeElement.type,
  MRNAs,  MRNAs.type,
  MRNA, MRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public MRNA target();

  public static type TYPE = type.mRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    MRNAs,  MRNAs.type,
    MRNA, MRNA.type
  > {
    mRNAs;
    public type value() { return mRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
  }
}
