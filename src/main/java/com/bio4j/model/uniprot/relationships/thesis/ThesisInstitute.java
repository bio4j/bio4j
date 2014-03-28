
package com.bio4j.model.relationships.citation.thesis;

import com.bio4j.model.uniprot.nodes.Institute;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ThesisInstitute{
    
    //--------GETTERS-------------
    public Thesis getThesis();    
    public Institute getInstitute();
    
}
