package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.uniprot.nodes.Pfam;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinPfam{
    
  public Protein getProtein();    
  public Pfam getPfam();    
}
