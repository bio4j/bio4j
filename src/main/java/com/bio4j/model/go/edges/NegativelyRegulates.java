package com.bio4j.model.go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.ohnosequences.typedGraphs.UntypedGraph;

public final class NegativelyRegulates<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		GoGraph.GoEdge<
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				NegativelyRegulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.NegativelyRegulatesType,
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				I, RV, RVT, RE, RET
				> {

	public NegativelyRegulates(RE edge, GoGraph<I, RV, RVT, RE, RET>.NegativelyRegulatesType type) {

		super(edge, type);
	}

	@Override
	public NegativelyRegulates<I, RV, RVT, RE, RET> self() {
		return this;
	}
}
