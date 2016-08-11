package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class GeneLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
GeneLocation<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.GeneLocationType,
I, RV, RVT, RE, RET
> {

  public GeneLocation(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.GeneLocationType type) {
    super(vertex, type);
  }

  @Override
  public GeneLocation<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
