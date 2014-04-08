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
public interface Genes extends Relationship <
  GenomeElement, GenomeElement.type,
  Genes,  Genes.type,
  Gene, Gene.type
> {

  @Override
  public GenomeElement source();
  @Override
  public Gene target();

  public static type TYPE = type.genes;
  public static enum type implements RelationshipType <
    GenomeElement, GenomeElement.type,
    Genes,  Genes.type,
    Gene, Gene.type
  > {
    genes;
    public type value() { return genes; }
    public arity arity() { return arity.manyToMany; } // TODO review this
    public GenomeElement.type sourceType() { return GenomeElement.TYPE; }
    public Gene.type targetType() { return Gene.TYPE; }
  }
}