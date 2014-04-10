package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.RRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface RRNAs extends Relationship <
  GenomeElement, GenomeElement.Type,
  RRNAs,  RRNAs.Type,
  RRNA, RRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public RRNA target();

  public static Type TYPE = Type.rRNAs;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    RRNAs,  RRNAs.Type,
    RRNA, RRNA.Type
  > {
    rRNAs;
    public Type value() { return rRNAs; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public RRNA.Type targetType() { return RRNA.TYPE; }
  }
}
