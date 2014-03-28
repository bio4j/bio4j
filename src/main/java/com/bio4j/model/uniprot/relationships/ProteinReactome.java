package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinReactome{
    
  public Protein getProtein();    
  public ReactomeTerm getReactomeTerm();
}
