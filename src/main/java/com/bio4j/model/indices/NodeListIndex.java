package com.bio4j.model.indices;

import java.util.List;
import com.bio4j.model.Node;

public abstract class NodeListIndex<N extends Node, K>{
  abstract List<? extends N> getNodes(K key);
}
