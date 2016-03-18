package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Article <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniProtGraph.UniProtVertex <
      Article<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ArticleType,
      I, RV, RVT, RE, RET
    >
{

  public Article(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ArticleType type) { super(vertex, type); }

  @Override
  public Article<I, RV, RVT, RE, RET> self() { return this; }
}
