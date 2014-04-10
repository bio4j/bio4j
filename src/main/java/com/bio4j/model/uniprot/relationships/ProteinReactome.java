package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinReactome{
    
  public Protein getProtein();    
  public ReactomeTerm getReactomeTerm();
}
