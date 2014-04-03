package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.MiscRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface MiscRNAs extends Relationship <
  GenomeElement, GenomeElement.type,
  MiscRNAs,  MiscRNAs.type,
  MiscRNA, MiscRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public MiscRNA target();

  public static type TYPE = type.miscRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    MiscRNAs,  MiscRNAs.type,
    MiscRNA, MiscRNA.type
  > {
    miscRNAs;
    public type value() { return miscRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
  }
}