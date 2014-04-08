package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.TmRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface TmRNAs extends Relationship <
  GenomeElement, GenomeElement.type,
  TmRNAs, TmRNAs.type,
  TmRNA, TmRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public TmRNA target();

  public static type TYPE = type.tmRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    TmRNAs, TmRNAs.type,
    TmRNA, TmRNA.type
  > {
    tmRNAs;
    public type value() { return tmRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
    public GenomeElement.type sourceType() { return GenomeElement.TYPE; }
    public TmRNA.type targetType() { return TmRNA.TYPE; }
  }
}
