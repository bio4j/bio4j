package com.bio4j.model.go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.angulillos.UntypedGraph;

public final class PositivelyRegulates<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  GoGraph.GoEdge<
    GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
    PositivelyRegulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.PositivelyRegulatesType,
    GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
    I, RV, RVT, RE, RET
  >
{

  public PositivelyRegulates(RE edge, GoGraph<I, RV, RVT, RE, RET>.PositivelyRegulatesType type) {

    super(edge, type);
  }

  @Override
  public final PositivelyRegulates<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
