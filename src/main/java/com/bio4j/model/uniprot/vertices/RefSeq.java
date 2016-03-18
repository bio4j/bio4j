package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class RefSeq <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
RefSeq<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.RefSeqType,
I, RV, RVT, RE, RET
> {

  public RefSeq(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.RefSeqType type) {
    super(vertex, type);
  }

  @Override
  public RefSeq<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
