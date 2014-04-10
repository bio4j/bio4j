package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.NcRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface NcRNAs extends Relationship <
  GenomeElement, GenomeElement.Type,
  NcRNAs,  NcRNAs.Type,
  NcRNA, NcRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public NcRNA target();

  public static Type TYPE = Type.ncRNAs;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    NcRNAs,  NcRNAs.Type,
    NcRNA, NcRNA.Type
  > {
    ncRNAs;
    public Type value() { return ncRNAs; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public NcRNA.Type targetType() { return NcRNA.TYPE; }
  }
}
