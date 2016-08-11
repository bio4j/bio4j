package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class PIR <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
PIR<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PIRType,
I, RV, RVT, RE, RET
> {

  public PIR(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PIRType type) {
    super(vertex, type);
  }

  @Override
  public PIR<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
