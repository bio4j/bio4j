package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Isoform;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinIsoform extends Edge {
    
  public Protein getProtein();
  public Isoform getIsoformNode();   
}
