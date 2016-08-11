package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Institute <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Institute<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.InstituteType,
I, RV, RVT, RE, RET
> {

  public Institute(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.InstituteType type) {
    super(vertex, type);
  }

  @Override
  public Institute<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
