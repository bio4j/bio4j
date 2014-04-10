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
public interface HasNcRNA extends HasGenomicFeature <
  HasNcRNA,  HasNcRNA.Type,
  NcRNA, NcRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public NcRNA target();

  public static Type TYPE = Type.hasNcRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasNcRNA,  HasNcRNA.Type,
    NcRNA, NcRNA.Type
  > {
    hasNcRNA;
    public Type value() { return hasNcRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public NcRNA.Type targetType() { return NcRNA.TYPE; }
  }
}