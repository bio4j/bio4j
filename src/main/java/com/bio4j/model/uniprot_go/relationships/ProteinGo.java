package com.bio4j.model.uniprot_go.relationships;

import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinGo{
    
  
  public Protein getProtein();
  public GoTerm getGoTerm();

  public String evidence();
}
