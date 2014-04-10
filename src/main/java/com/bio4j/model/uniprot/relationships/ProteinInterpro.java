package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Interpro;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinInterpro{
    
  public Protein getProtein();
  public Interpro getInterpro();   
}