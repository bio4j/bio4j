package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CommentType extends Node<CommentType, CommentType.Type>,
	Name<CommentType,CommentType.Type>{
    
  enum Type implements NodeType<CommentType, CommentType.Type> {

    commentType;
    public Type value() { return commentType; }
  }
  
    
}
