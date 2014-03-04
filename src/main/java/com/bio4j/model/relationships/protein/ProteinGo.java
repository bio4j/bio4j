package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.GoTerm;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinGo extends Edge {
    
  public String getEvidence();
  public Protein getProtein();
  public GoTerm getGoTerm();

  public void setEvidence(String value);    
}
