
package com.bio4j.model.relationships.citation.submission;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Submission;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubmissionProteinCitation{
    
    //----------GETTERS----------------
    public Protein getProtein();
    public Submission getSubmission();

    
}
