
package com.bio4j.model.relationships.citation.submission;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Submission;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubmissionProteinCitation extends Edge {
    
    //----------GETTERS----------------
    public Protein getProtein();
    public Submission getSubmission();

    
}
