package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinOrganism{
 
  public Protein getProtein();
  public Organism getOrganism();
    
}
