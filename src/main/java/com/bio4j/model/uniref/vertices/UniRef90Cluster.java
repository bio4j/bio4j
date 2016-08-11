package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UniRef90Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniRefGraph.UniRefVertex<
  UniRef90Cluster<I, RV, RVT, RE, RET>,
  UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
  I, RV, RVT, RE, RET
  > {

  public UniRef90Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType type) {
  super(vertex, type);
  }

  @Override
  public UniRef90Cluster<I, RV, RVT, RE, RET> self() {
  return this;
  }
}
