package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Pfam;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinPfam extends Relationship {
    
  public Protein getProtein();    
  public Pfam getPfam();    
}
