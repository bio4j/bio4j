package com.bio4j.model.nodes.refseq.rna;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.NodeType;

public interface RNAType <
  R extends RNA<R,T>,
  T extends Enum<T> & RNAType<R,T>
> extends NodeType<R,T> {}