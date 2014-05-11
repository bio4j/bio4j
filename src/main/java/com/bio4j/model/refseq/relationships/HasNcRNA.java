package com.bio4j.model.refseq.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.NcRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasNcRNA extends HasGenomicFeature <
  HasNcRNA,  HasNcRNA.Type,
  NcRNA, NcRNA.Type
> {

  public GenomeElement source();
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
