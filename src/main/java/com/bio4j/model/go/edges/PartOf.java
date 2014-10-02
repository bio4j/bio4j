package com.bio4j.model.go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.ohnosequences.typedGraphs.UntypedGraph;

public final class PartOf<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		GoGraph.GoEdge<
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				PartOf<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.PartOfType,
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				I, RV, RVT, RE, RET
				> {

	public PartOf(RE edge, GoGraph<I, RV, RVT, RE, RET>.PartOfType type) {

		super(edge, type);
	}

	@Override
	public PartOf<I, RV, RVT, RE, RET> self() {
		return this;
	}
}
