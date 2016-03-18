package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Ensembl <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Ensembl<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.EnsemblType,
I, RV, RVT, RE, RET
> {

  public Ensembl(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.EnsemblType type) {
    super(vertex, type);
  }

  @Override
  public Ensembl<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
