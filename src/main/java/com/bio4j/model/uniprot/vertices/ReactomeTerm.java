package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class ReactomeTerm <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
ReactomeTerm<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.ReactomeTermType,
I, RV, RVT, RE, RET
> {

  public ReactomeTerm(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ReactomeTermType type) {
    super(vertex, type);
  }

  @Override
  public ReactomeTerm<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
