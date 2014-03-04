
package com.bio4j.model.relationships.citation.patent;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Patent;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PatentProteinCitation extends Edge {
    
    //------------GETTERS-----------------
    public Patent getPatent();    
    public Protein getProtein();
    
}
