package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinDataset {
    
  public Protein getProtein();
  public Dataset getDataset();
    
}