package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.reactome.ReactomeTerm;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinReactome extends Edge {
    
  public Protein getProtein();    
  public ReactomeTerm getReactomeTerm();
}
