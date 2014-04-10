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
  GenomeElement, GenomeElement.Type,
  CDSs, CDSs.Type,
  CDS, CDS.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public CDS target();

  public static Type TYPE = Type.cdss;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    CDSs, CDSs.Type,
    CDS, CDS.Type
  > {
    cdss;
    public Type value() { return cdss; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public CDS.Type targetType() { return CDS.TYPE; }
  }
}