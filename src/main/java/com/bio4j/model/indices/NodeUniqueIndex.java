package com.bio4j.model.indices;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface NodeUniqueIndex <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>, 
  V
> extends NodeIndex<N,T,V> {

  N getNode(V byValue);  
}