package com.bio4j.model.relationships.refseq;

import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.nodes.refseq.rna.TmRNA;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementTmRna extends Edge {
    
  public GenomeElement getGenomeElement();
  public TmRNA getTmRNA();
}