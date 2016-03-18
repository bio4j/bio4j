package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Country <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Country<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.CountryType,
I, RV, RVT, RE, RET
> {

  public Country(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.CountryType type) {
    super(vertex, type);
  }

  @Override
  public Country<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
