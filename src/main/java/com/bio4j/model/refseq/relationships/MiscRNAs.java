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
  GenomeElement, GenomeElement.Type,
  MiscRNAs,  MiscRNAs.Type,
  MiscRNA, MiscRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public MiscRNA target();

  public static Type TYPE = Type.miscRNAs;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    MiscRNAs,  MiscRNAs.Type,
    MiscRNA, MiscRNA.Type
  > {
    miscRNAs;
    public Type value() { return miscRNAs; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public MiscRNA.Type targetType() { return MiscRNA.TYPE; }
  }
}