package com.bio4j.model.indices;

import com.bio4j.model.Node;

public abstract class NodeIndex<N extends Node, K>{
  abstract N getNode(K key);
}
