package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public class AlternativeProduct <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniProtGraph.UniProtVertex<
      AlternativeProduct<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.AlternativeProductType,
      I, RV, RVT, RE, RET
    >
{

  public AlternativeProduct(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.AlternativeProductType type) { super(vertex, type); }

  @Override
  public AlternativeProduct<I, RV, RVT, RE, RET> self() { return this; }
}
