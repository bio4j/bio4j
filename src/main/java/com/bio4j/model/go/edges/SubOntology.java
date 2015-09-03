package com.bio4j.model.go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.angulillos.UntypedGraph;

public final class SubOntology<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  GoGraph.GoEdge<
    GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
    SubOntology<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.SubOntologyType,
    SubOntologies<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType,
    I, RV, RVT, RE, RET
    > {

  public SubOntology(RE edge, GoGraph<I, RV, RVT, RE, RET>.SubOntologyType type) {

  super(edge, type);
  }

  @Override
  public SubOntology<I, RV, RVT, RE, RET> self() {
  return this;
  }
}
