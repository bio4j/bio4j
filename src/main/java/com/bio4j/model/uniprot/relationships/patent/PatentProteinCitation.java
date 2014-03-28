
package com.bio4j.model.relationships.citation.patent;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Patent;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PatentProteinCitation{
    
    //------------GETTERS-----------------
    public Patent getPatent();    
    public Protein getProtein();
    
}
