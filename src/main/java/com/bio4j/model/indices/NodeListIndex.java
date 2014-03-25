package com.bio4j.model.indices;

import java.util.List;
import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface NodeListIndex < 
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>, 
  V
> extends NodeIndex<N,T,V> {

  List<? extends N> getNodes(V byValue);
}
