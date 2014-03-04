package com.bio4j.model.relationships.refseq;

import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.nodes.refseq.rna.NcRNA;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementNcRna extends Edge {
    
  public GenomeElement getGenomeElement();    
  public NcRNA getNcRNA();
}