package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Pubmed <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Pubmed<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PubmedType,
I, RV, RVT, RE, RET
> {

  public Pubmed(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PubmedType type) {
    super(vertex, type);
  }

  @Override
  public Pubmed<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
