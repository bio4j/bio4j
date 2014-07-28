package com.bio4j.model.go.relationships;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.ohnosequences.typedGraphs.UntypedGraph;

public final class IsA<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		GoGraph.GoEdge<
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				IsA<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.IsAType,
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				I, RV, RVT, RE, RET
				> {

	public IsA(RE edge, GoGraph<I, RV, RVT, RE, RET>.IsAType type) {

		super(edge, type);
	}

	@Override
	public IsA<I, RV, RVT, RE, RET> self() {
		return this;
	}
}

