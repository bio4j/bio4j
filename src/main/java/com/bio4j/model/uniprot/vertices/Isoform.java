package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Isoform <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Isoform<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.IsoformType,
I, RV, RVT, RE, RET
> {

  public Isoform(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.IsoformType type) {
    super(vertex, type);
  }

  @Override
  public Isoform<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
