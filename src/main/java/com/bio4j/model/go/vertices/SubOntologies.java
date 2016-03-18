package com.bio4j.model.go.vertices;

import com.bio4j.model.go.GoGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class SubOntologies<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    GoGraph.GoVertex <
      SubOntologies<I, RV, RVT, RE, RET>,
      GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType,
      I, RV, RVT, RE, RET
    >
{

  public SubOntologies(RV vertex, GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType type) { super(vertex, type); }

  @Override
  public final SubOntologies<I, RV, RVT, RE, RET> self() { return this; }
}
