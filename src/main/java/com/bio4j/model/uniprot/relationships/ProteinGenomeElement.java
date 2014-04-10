package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.refseq.nodes.GenomeElement;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinGenomeElement{
    
  public Protein getProtein();
  public GenomeElement getGenomeElement();   
}
