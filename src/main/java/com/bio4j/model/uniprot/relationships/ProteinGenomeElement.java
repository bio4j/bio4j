package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.refseq.nodes.GenomeElement;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface ProteinGenomeElement{
    
  public Protein getProtein();
  public GenomeElement getGenomeElement();   
}
