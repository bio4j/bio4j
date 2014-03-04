package com.bio4j.model.relationships.refseq;

import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.nodes.refseq.rna.MiscRNA;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GenomeElementMiscRna extends Edge {
    
  public GenomeElement getGenomeElement();    
  public MiscRNA getMiscRNA();
}
