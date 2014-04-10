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
public interface HasMiscRNA extends HasGenomicFeature <
  HasMiscRNA,  HasMiscRNA.Type,
  MiscRNA, MiscRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public MiscRNA target();

  public static Type TYPE = Type.hasMiscRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasMiscRNA,  HasMiscRNA.Type,
    MiscRNA, MiscRNA.Type
  > {
    hasMiscRNA;
    public Type value() { return hasMiscRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public MiscRNA.Type targetType() { return MiscRNA.TYPE; }
  }
}