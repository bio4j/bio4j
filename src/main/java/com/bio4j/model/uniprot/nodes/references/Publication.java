package com.bio4j.model.uniprot.nodes.references;

import com.bio4j.model.go.nodes.Term.Type;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

/*
  This node represents a publication; its properties (which depend on the particular type) are accessible through relationships pointing to the corresponding Publication Type, like `Journal`.
*/
public interface Publication extends Node<Publication, Publication.Type> {
  
  public static Type TYPE = Type.reference;
  public default Type type() { return TYPE; }
  
  public static enum Type implements NodeType<Publication, Publication.Type> {
    
    reference;
    
    public Type value() { return reference; }
  }
}