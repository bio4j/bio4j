package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;
import com.bio4j.model.Relationship;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UnpublishedObservationProteinCitation{
    
    //--------------GETTERS------------------
    public UnpublishedObservation getUnpublishedObservation();    
    public Protein getProtein();
    
}
