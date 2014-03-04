
package com.bio4j.model.relationships.citation.uo;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.UnpublishedObservation;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface UnpublishedObservationAuthor extends Edge  {
    
    //-----------GETTERS----------------
    public UnpublishedObservation getUnpublishedObservation();    
    public Person getAuthor();
    
}
