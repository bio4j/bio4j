
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
	S extends Node<S,ST>,
	ST extends Enum<ST> & NodeType<S,ST>,
	R extends BasicComment<S,ST,R,RT,T,TT>, 
	RT extends Enum<RT> & BasicCommentType<S,ST,R,RT,T,TT>,
	T extends Node<T,TT>,
	TT extends Enum<TT> & NodeType<T,TT>	
	> extends Relationship<S,ST, R,RT, T,TT> {
    
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
    
    public static Type TYPE = Type.basicComment;
    public static enum Type implements RelationshipType <
      Protein, Protein.Type,
      BasicComment, BasicComment.Type,
      CommentType, CommentType.Type
    > {
    	basicComment;
      public Type value() { return basicComment; }
      public Arity arity() { return Arity.manyToMany; }
      public Protein.Type sourceType() { return Protein.TYPE; }
      public CommentType.Type targetType() { return CommentType.TYPE; }
    }

    public Protein source();
    public CommentType target();
    
}
