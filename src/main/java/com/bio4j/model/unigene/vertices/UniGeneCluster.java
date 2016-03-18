package com.bio4j.model.unigene.vertices;

import com.bio4j.model.unigene.UniGeneGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UniGeneCluster<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniGeneGraph.UniGeneVertex <
      UniGeneCluster<I, RV, RVT, RE, RET>,
      UniGeneGraph<I, RV, RVT, RE, RET>.UniGeneClusterType,
      I, RV, RVT, RE, RET
    >
{

  public UniGeneCluster(RV vertex, UniGeneGraph<I, RV, RVT, RE, RET>.UniGeneClusterType type) {
    super(vertex, type);
  }

  @Override
  public UniGeneCluster<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
