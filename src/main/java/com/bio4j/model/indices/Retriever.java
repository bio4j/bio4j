package com.bio4j.model.indices;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import java.util.List;

// if needed add indexes as params
public abstract class Retriever<N extends Node<N,T>, T extends Enum<T> & NodeType<N,T>> {

  public <V, I extends NodeUniqueIndex<N,T,V>> N getNodeFrom(I index, V value) { 

    return index.getNode(value); 
  }
  public <V, I extends NodeListIndex<N,T,V>> List<? extends N> getNodesFrom(I index, V value) {

    return index.getNodes(value);
  }
}