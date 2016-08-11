package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class SubcellularLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
SubcellularLocation<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
I, RV, RVT, RE, RET
> {

  public SubcellularLocation(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationType type) {
    super(vertex, type);
  }

  @Override
  public SubcellularLocation<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
