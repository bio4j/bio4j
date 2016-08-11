package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Disease <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Disease<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.DiseaseType,
I, RV, RVT, RE, RET
> {

  public Disease(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.DiseaseType type) {
    super(vertex, type);
  }

  @Override
  public Disease<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
