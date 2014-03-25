package com.bio4j.model.indices;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

public interface NodeIndex <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>, 
  V
> {}
