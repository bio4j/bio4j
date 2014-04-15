package com.bio4j.model.refseq.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.Gene;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface HasGene extends HasGenomicFeature <
  HasGene,  HasGene.Type,
  Gene, Gene.Type
> {

  public GenomeElement source();
  public Gene target();

  public static Type TYPE = Type.hasGene;
  public static enum Type implements HasGenomicFeatureType <
    HasGene,  HasGene.Type,
    Gene, Gene.Type
  > {
    hasGene;
    public Type value() { return hasGene; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public Gene.Type targetType() { return Gene.TYPE; }
  }
}