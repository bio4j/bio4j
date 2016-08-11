package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Thesis<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Thesis<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.ThesisType,
I, RV, RVT, RE, RET
>  {

  public Thesis(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ThesisType type) {
    super(vertex, type);
  }

  @Override
  public Thesis<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
