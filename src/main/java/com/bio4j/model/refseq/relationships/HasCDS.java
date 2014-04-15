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