package com.bio4j.model.relationships.refseq;

import com.bio4j.model.nodes.refseq.Gene;
import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementGene extends Relationship {
    
  public GenomeElement getGenomeElement();    
  public Gene getGene();
}