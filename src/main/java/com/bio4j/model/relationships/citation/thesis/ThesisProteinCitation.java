
package com.bio4j.model.relationships.citation.thesis;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Thesis;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisProteinCitation extends Relationship {
    
    //---------GETTERS-------------
    public Thesis getThesis();    
    public Protein getProtein();
    
}
