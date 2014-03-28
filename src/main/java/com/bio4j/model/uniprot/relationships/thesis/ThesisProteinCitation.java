
package com.bio4j.model.relationships.citation.thesis;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisProteinCitation{
    
    //---------GETTERS-------------
    public Thesis getThesis();    
    public Protein getProtein();
    
}
