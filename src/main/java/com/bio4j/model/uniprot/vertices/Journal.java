package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Journal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Journal<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.JournalType,
I, RV, RVT, RE, RET
> {

  public Journal(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.JournalType type) {
    super(vertex, type);
  }

  @Override
  public Journal<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
