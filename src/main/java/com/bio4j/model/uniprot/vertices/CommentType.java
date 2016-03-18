package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class CommentType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
CommentType<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType,
I, RV, RVT, RE, RET
> {

  public CommentType(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType type) {
    super(vertex, type);
  }

  @Override
  public CommentType<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
