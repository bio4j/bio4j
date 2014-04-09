package com.bio4j.model.refseq.nodes;

import com.bio4j.model.NodeType;

public interface RNAType <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends NodeType<R,T> {}