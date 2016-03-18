package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class EMBL <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
EMBL<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.EMBLType,
I, RV, RVT, RE, RET
> {

  public EMBL(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.EMBLType type) {
    super(vertex, type);
  }

  @Override
  public EMBL<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
