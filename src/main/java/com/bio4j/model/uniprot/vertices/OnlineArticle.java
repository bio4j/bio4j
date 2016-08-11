package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class OnlineArticle <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
OnlineArticle<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
I, RV, RVT, RE, RET
>  {

  public OnlineArticle(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.OnlineArticleType type) {
    super(vertex, type);
  }

  @Override
  public OnlineArticle<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
