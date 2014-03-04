
package com.bio4j.model.relationships.comment;

import com.bio4j.model.nodes.CommentType;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BasicComment extends Edge {
    
    //---------GETTERS--------------
    public String getText();
    public String getStatus();
    public String getEvidence();
    
    public Protein getProtein();
    public CommentType getCommentType();

    //----------SETTERS-------------
    public void setText(String value);
    public void setStatus(String value);
    public void setEvidence(String value);
    
}
