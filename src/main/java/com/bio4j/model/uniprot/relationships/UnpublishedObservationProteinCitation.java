package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface UnpublishedObservationProteinCitation{
    
    //--------------GETTERS------------------
    public UnpublishedObservation getUnpublishedObservation();    
    public Protein getProtein();
    
}
