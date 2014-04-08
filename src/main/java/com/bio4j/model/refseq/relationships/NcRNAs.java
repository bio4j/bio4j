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
  GenomeElement, GenomeElement.type,
  NcRNAs,  NcRNAs.type,
  NcRNA, NcRNA.type
> {

  @Override
  public GenomeElement source();
  @Override
  public NcRNA target();

  public static type TYPE = type.ncRNAs;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    NcRNAs,  NcRNAs.type,
    NcRNA, NcRNA.type
  > {
    ncRNAs;
    public type value() { return ncRNAs; }
    public arity arity() { return arity.manyToMany; } // TODO review this
    public GenomeElement.type sourceType() { return GenomeElement.TYPE; }
    public NcRNA.type targetType() { return NcRNA.TYPE; }
  }
}
