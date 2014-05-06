package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.CDS;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasCDS extends HasGenomicFeature <
  HasCDS,  HasCDS.Type,
  CDS, CDS.Type
> {

  public GenomeElement source();
  public CDS target();

  public static Type TYPE = Type.hasCDS;
  public static enum Type implements HasGenomicFeatureType <
    HasCDS,  HasCDS.Type,
    CDS, CDS.Type
  > {
    hasCDS;
    public Type value() { return hasCDS; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public CDS.Type targetType() { return CDS.TYPE; }
  }
}
