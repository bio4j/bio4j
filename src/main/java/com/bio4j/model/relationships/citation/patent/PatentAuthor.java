
package com.bio4j.model.relationships.citation.patent;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.Patent;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface PatentAuthor extends Edge {
    
    //---------GETTERS------------
    public Patent getPatent(); 
    public Person getAuthor();
}
