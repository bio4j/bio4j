package com.bio4j.model.refseq.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.RRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasRRNA extends HasGenomicFeature <
  HasRRNA,  HasRRNA.Type,
  RRNA, RRNA.Type
> {

  public GenomeElement source();
  public RRNA target();

  public static Type TYPE = Type.hasRRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasRRNA,  HasRRNA.Type,
    RRNA, RRNA.Type
  > {
    hasRRNA;
    public Type value() { return hasRRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public RRNA.Type targetType() { return RRNA.TYPE; }
  }
}
