
package com.bio4j.model.relationships.citation.thesis;

import com.bio4j.model.nodes.citation.Thesis;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisAuthor extends Edge {
    
    //----------GETTERS----------------
    public Thesis getThesis();
    
}
