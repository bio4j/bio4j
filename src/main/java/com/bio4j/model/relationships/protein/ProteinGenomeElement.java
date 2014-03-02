
package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinGenomeElement extends Relationship {
    
    public Protein getProtein();    
    public GenomeElement getGenomeElement();
    
}
