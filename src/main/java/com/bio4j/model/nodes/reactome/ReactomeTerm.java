
package com.bio4j.model.nodes.reactome;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ReactomeTerm extends Node {
    
    //------GETTERS-----
    public String getId();
    public String getPathwayName();
    public List<Protein> getAssociatedProteins();

    //------SETTERS------
    public void setId(String value);
    public void setPathwayName(String value);
  
    
    
    
}
