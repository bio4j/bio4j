package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface CommentType extends Node<CommentType, CommentType.Type>,
  
  // properties
	Name<CommentType,CommentType.Type>
{
  
  public static Type TYPE = Type.commentType;
  public static enum Type implements NodeType<CommentType, CommentType.Type> {

    commentType;
    public Type value() { return commentType; }
  }
  
    
}
