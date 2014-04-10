package com.bio4j.model.refseq.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// relationships
// import com.bio4j.model.refseq.relationships.HasGene;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Gene extends GenomicFeature<Gene, Gene.Type> {

  // hasGene
  // in
  // public List<? extends HasGene> hasGene_in();
  public List<? extends GenomeElement> hasGene_inNodes();


  public static Type TYPE = Type.gene;
  public static enum Type implements GenomicFeatureType<Gene, Gene.Type> {

    gene;
    public Type value() { return gene; }
  }
}
