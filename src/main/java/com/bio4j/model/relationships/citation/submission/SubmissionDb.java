
package com.bio4j.model.relationships.citation.submission;

import com.bio4j.model.nodes.citation.DB;
import com.bio4j.model.nodes.citation.Submission;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface SubmissionDb extends Relationship {
        
    //----------GETTERS----------------
    public String getDate();
    public DB getDB();    
    public Submission getSubmission();

    //----------SETTERS----------------
    public void setDate(String value);
}
