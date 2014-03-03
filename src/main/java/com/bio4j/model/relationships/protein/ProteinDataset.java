package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Dataset;
import com.bio4j.model.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinDataset {
    
  public Protein getProtein();
  public Dataset getDataset();
    
}