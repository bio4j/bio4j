package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.isoforms.nodes.Isoform;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
// TODO: migrate
public interface ProteinIsoform {
    
  public Protein getProtein();
  public Isoform getIsoformNode();   
}
