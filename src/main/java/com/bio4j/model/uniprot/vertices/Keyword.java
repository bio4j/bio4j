package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public class Keyword <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Keyword<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.KeywordType,
I, RV, RVT, RE, RET
> {

  public Keyword(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.KeywordType type) {
    super(vertex, type);
  }

  @Override
  public Keyword<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
