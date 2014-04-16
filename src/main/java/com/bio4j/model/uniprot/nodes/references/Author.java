package com.bio4j.model.uniprot.nodes.references;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/*
  This node represents an author of a reference.
*/
public interface Author extends Node<Author, Author.Type> {
  
  public static Type TYPE = Type.author;
  
  public static enum Type implements NodeType<Author, Author.Type> {
    
    author;
    
    public Type value() { return author; }
  }
}