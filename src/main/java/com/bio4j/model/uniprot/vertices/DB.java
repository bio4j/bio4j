package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class DB <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
DB<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.DBType,
I, RV, RVT, RE, RET
>
{

  public DB(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.DBType type) {
    super(vertex, type);
  }

  @Override
  public DB<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
