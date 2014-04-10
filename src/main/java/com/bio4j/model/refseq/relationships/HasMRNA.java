package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.MRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface HasMRNA extends HasGenomicFeature <
  HasMRNA,  HasMRNA.Type,
  MRNA, MRNA.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public MRNA target();

  public static Type TYPE = Type.hasMRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasMRNA,  HasMRNA.Type,
    MRNA, MRNA.Type
  > {
    hasMRNA;
    public Type value() { return hasMRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public MRNA.Type targetType() { return MRNA.TYPE; }
  }
}