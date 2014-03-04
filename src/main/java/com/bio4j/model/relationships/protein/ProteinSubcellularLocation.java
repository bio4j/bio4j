package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.SubcellularLocation;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinSubcellularLocation extends Edge {
    
  public String getEvidence();
  public String getStatus();
  public String getTopology();
  public String getTopologyStatus();

  public void setEvidence(String value);
  public void setStatus(String value);
  public void setTopology(String value);
  public void setTopologyStatus(String value);


  public Protein getProtein();    
  public SubcellularLocation getSubcellularLocation();
}
