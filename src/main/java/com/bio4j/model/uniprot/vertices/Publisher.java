package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Publisher <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Publisher<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PublisherType,
I, RV, RVT, RE, RET
> {

  public Publisher(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PublisherType type) {
    super(vertex, type);
  }

  @Override
  public Publisher<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
