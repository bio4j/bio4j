package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class InterPro <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
InterPro<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.InterProType,
I, RV, RVT, RE, RET
> {

  public InterPro(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.InterProType type) {
    super(vertex, type);
  }

  @Override
  public InterPro<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
