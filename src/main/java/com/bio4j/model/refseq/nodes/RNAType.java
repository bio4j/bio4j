package com.bio4j.model.refseq.nodes;

import com.ohnosequences.typedGraphs.NodeType;

public interface RNAType <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends GenomicFeatureType<R,T> {}