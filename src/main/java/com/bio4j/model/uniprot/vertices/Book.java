package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Book<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Book<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.BookType,
I, RV, RVT, RE, RET
>  {

  public Book(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.BookType type) {
    super(vertex, type);
  }

  @Override
  public Book<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
