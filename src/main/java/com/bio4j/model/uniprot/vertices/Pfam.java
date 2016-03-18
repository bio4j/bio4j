package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public class Pfam <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Pfam<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PfamType,
I, RV, RVT, RE, RET
> {

  public Pfam(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PfamType type) {
    super(vertex, type);
  }

  @Override
  public Pfam<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
