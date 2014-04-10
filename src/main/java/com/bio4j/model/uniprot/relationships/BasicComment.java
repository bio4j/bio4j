
package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.RelationshipType.Arity;
import com.bio4j.model.uniprot.nodes.CommentType;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BasicComment <
	R extends BasicComment<R,RT>, 
	RT extends Enum<RT> & BasicCommentType<R,RT>
> extends Relationship<Protein,Protein.Type, R,RT, CommentType, CommentType.Type> {
    
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

    public Protein source();
    public CommentType target();
    
}
