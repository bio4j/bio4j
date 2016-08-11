package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UnpublishedObservation<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
UnpublishedObservation<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType,
I, RV, RVT, RE, RET
>  {

  public UnpublishedObservation(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType type) {
    super(vertex, type);
  }

  @Override
  public UnpublishedObservation<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
