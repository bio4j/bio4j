package com.bio4j.model.go.vertices;

import com.bio4j.model.go.GoGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class GoTerm<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    GoGraph.GoVertex <
      GoTerm<I, RV, RVT, RE, RET>,
      GoGraph<I, RV, RVT, RE, RET>.GoTermType,
      I, RV, RVT, RE, RET
    >
{

  public GoTerm(RV vertex, GoGraph<I, RV, RVT, RE, RET>.GoTermType type) { super(vertex, type); }

  @Override
  public final GoTerm<I, RV, RVT, RE, RET> self() { return this; }
}
