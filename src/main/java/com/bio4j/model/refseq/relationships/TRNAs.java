package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.TRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface TRNAs extends Relationship <
  GenomeElement, GenomeElement.type,
  TRNAs,  TRNAs.type,
  TRNA, TRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public TRNA target();

  public static type TYPE = type.tRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    TRNAs,  TRNAs.type,
    TRNA, TRNA.type
  > {
    tRNAs;
    public type value() { return tRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
    public GenomeElement.type sourceType() { return GenomeElement.TYPE; }
    public TRNA.type targetType() { return TRNA.TYPE; }
  }
}
