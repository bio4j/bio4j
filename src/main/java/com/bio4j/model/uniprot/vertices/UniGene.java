package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UniGene <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
UniGene<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.UniGeneType,
I, RV, RVT, RE, RET
> {

  public UniGene(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.UniGeneType type) {
    super(vertex, type);
  }

  @Override
  public UniGene<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
