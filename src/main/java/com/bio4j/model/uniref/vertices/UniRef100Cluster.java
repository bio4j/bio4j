package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UniRef100Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniRefGraph.UniRefVertex<
  UniRef100Cluster<I, RV, RVT, RE, RET>,
  UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
  I, RV, RVT, RE, RET
  > {

  public UniRef100Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType type) {
  super(vertex, type);
  }

  @Override
  public UniRef100Cluster<I, RV, RVT, RE, RET> self() {
  return this;
  }
}
