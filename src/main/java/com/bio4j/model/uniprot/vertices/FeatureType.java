package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class FeatureType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
FeatureType<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.FeatureTypeType,
I, RV, RVT, RE, RET
> {

  public FeatureType(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.FeatureTypeType type) {
    super(vertex, type);
  }

  @Override
  public FeatureType<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
