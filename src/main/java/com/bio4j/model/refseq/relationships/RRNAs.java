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
  GenomeElement, GenomeElement.type,
  RRNAs,  RRNAs.type,
  RRNA, RRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public RRNA target();

  public static type TYPE = type.rRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    RRNAs,  RRNAs.type,
    RRNA, RRNA.type
  > {
    rRNAs;
    public type value() { return rRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
  }
}
