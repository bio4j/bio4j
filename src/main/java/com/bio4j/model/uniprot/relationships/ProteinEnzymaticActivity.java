package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinEnzymaticActivity{
    
  public Protein getProtein();
  public Enzyme getEnzyme();
}