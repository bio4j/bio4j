package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Consortium <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Consortium<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.ConsortiumType,
I, RV, RVT, RE, RET
> {

  public Consortium(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ConsortiumType type) {
    super(vertex, type);
  }

  @Override
  public Consortium<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
