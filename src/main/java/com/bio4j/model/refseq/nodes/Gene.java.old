package com.bio4j.model.refseq.nodes;

import java.util.List;


import com.bio4j.model.go.nodes.Term.Type;
// relationships
import com.bio4j.model.refseq.relationships.HasGene;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface Gene extends GenomicFeature<Gene, Gene.Type> {

  // hasGene
  // in
  public List<? extends HasGene> hasGene_in();
  public List<? extends GenomeElement> hasGene_inNodes();


  public static Type TYPE = Type.gene;
  public default Type type() { return TYPE; }
  public static enum Type implements GenomicFeatureType<Gene, Gene.Type> {

    gene;
    public Type value() { return gene; }
  }
}
