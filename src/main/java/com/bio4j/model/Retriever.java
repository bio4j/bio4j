package com.bio4j.model;

import java.util.List;

// if needed add indexes as params
public abstract class Retriever<N extends Node<N,T>, T extends Enum<T> & NodeType<N,T>> {

  // public <V, P extends Enum<P> & PropertyType<P,V>, I extends NodeUniqueIndex<N,T,P,V>> N getNodeFrom(I index, V value) { 

  //   return index.getNode(value); 
  // }
  // public <V, P extends Enum<P> & PropertyType<P,V>, I extends NodeListIndex<N,T,P,V>> List<? extends N> getNodesFrom(I index, V value) {

  //   return index.getNodes(value);
  // }
}