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
  GenomeElement, GenomeElement.Type,
  TmRNAs, TmRNAs.Type,
  TmRNA, TmRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public TmRNA target();

  public static Type TYPE = Type.tmRNAs;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    TmRNAs, TmRNAs.Type,
    TmRNA, TmRNA.Type
  > {
    tmRNAs;
    public Type value() { return tmRNAs; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public TmRNA.Type targetType() { return TmRNA.TYPE; }
  }
}
