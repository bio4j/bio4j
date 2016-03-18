package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class City <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
City<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.CityType,
I, RV, RVT, RE, RET
> {

  public City(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.CityType type) {
    super(vertex, type);
  }

  @Override
  public City<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
