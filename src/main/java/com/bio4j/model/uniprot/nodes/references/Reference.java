package com.bio4j.model.uniprot.nodes.references;

import com.bio4j.model.go.nodes.GoTerm.Type;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

/*
  This node represents a reference; its properties (which depend on the particular type) are accessible through relationships pointing to the corresponding Reference Type.
*/
public interface Reference extends Node<Reference, Reference.Type> {
  
  public static Type TYPE = Type.reference;
  public default Type type() { return TYPE; }
  
  public static enum Type implements NodeType<Reference, Reference.Type> {
    
    reference;
    
    public Type value() { return reference; }
  }
}