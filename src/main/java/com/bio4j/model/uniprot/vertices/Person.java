package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Person <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Person<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PersonType,
I, RV, RVT, RE, RET
> {

  public Person(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PersonType type) {
    super(vertex, type);
  }

  @Override
  public Person<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
