package com.bio4j.model.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface CommentType extends Node<CommentType, CommentType.type> {
    
  enum type implements NodeType<CommentType, CommentType.type> {

    commentType;
    public type value() { return commentType; }
  }
  
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value); 
    
}
