package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Reference <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Reference<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
I, RV, RVT, RE, RET
> {

  public Reference(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType type) {
    super(vertex, type);
  }

  @Override
  public Reference<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
