package com.bio4j.model.relationships.citation.uo;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.UnpublishedObservation;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface UnpublishedObservationProteinCitation extends Relationship {
    
    //--------------GETTERS------------------
    public UnpublishedObservation getUnpublishedObservation();    
    public Protein getProtein();
    
}
