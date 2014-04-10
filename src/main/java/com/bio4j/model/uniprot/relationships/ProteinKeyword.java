package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Keyword;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinKeyword{
    
  public Protein getProtein();
  public Keyword getKeyword();
}