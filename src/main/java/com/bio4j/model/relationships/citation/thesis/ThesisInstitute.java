
package com.bio4j.model.relationships.citation.thesis;

import com.bio4j.model.nodes.Institute;
import com.bio4j.model.nodes.citation.Thesis;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisInstitute extends Edge {
    
    //--------GETTERS-------------
    public Thesis getThesis();    
    public Institute getInstitute();
    
}
