package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Kegg <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Kegg<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.KeggType,
I, RV, RVT, RE, RET
> {

  public Kegg(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.KeggType type) {
    super(vertex, type);
  }

  @Override
  public Kegg<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
