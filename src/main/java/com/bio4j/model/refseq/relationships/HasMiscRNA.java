package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.MiscRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasMiscRNA extends HasGenomicFeature <
  HasMiscRNA,  HasMiscRNA.Type,
  MiscRNA, MiscRNA.Type
> {

  public GenomeElement source();
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
