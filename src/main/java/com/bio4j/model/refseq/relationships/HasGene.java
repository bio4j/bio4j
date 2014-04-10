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
public interface HasGene extends Relationship <
  GenomeElement, GenomeElement.Type,
  HasGene,  HasGene.Type,
  Gene, Gene.Type
> {

  @Override
  public GenomeElement source();
  @Override
  public Gene target();

  public static Type TYPE = Type.genes;
  public static enum Type implements RelationshipType <
    GenomeElement, GenomeElement.Type,
    HasGene,  HasGene.Type,
    Gene, Gene.Type
  > {
    genes;
    public Type value() { return genes; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public Gene.Type targetType() { return Gene.TYPE; }
  }
}