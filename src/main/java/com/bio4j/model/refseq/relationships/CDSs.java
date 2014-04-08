package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.CDS;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface CDSs extends Relationship <
  GenomeElement, GenomeElement.type,
  CDSs, CDSs.type,
  CDS, CDS.type
> {

  @Override
  public GenomeElement source();
  @Override
  public CDS target();

  public static type TYPE = type.cdss;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    CDSs, CDSs.type,
    CDS, CDS.type
  > {
    cdss;
    public type value() { return cdss; }
    public arity arity() { return arity.manyToMany; } // TODO review this
    public GenomeElement.type sourceType() { return GenomeElement.TYPE; }
    public CDS.type targetType() { return CDS.TYPE; }
  }
}