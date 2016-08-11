package com.bio4j.model.go.vertices;

import com.bio4j.model.go.GoGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class GoSlims<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
  extends
    GoGraph.GoVertex <
      GoSlims<I,RV,RVT,RE,RET>,
      GoGraph<I,RV,RVT,RE,RET>.GoSlimsType,
      I,RV,RVT,RE,RET
    >
{

  public GoSlims(RV vertex, GoGraph<I,RV,RVT,RE,RET>.GoSlimsType type) { super(vertex, type); }

  @Override
  public GoSlims<I,RV,RVT,RE,RET> self() { return this; }
}
