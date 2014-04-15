package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.TmRNA;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface HasTmRNA extends HasGenomicFeature <
  HasTmRNA,  HasTmRNA.Type,
  TmRNA, TmRNA.Type
> {

  public GenomeElement source();
  public TmRNA target();

  public static Type TYPE = Type.hasTmRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasTmRNA,  HasTmRNA.Type,
    TmRNA, TmRNA.Type
  > {
    hasTmRNA;
    public Type value() { return hasTmRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public TmRNA.Type targetType() { return TmRNA.TYPE; }
  }
}