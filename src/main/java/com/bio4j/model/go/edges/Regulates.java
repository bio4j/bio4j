package com.bio4j.model.go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.angulillos.UntypedGraph;

public final class Regulates<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		GoGraph.GoEdge<
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				Regulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.RegulatesType,
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				I, RV, RVT, RE, RET
				> {

	public Regulates(RE edge, GoGraph<I, RV, RVT, RE, RET>.RegulatesType type) {

		super(edge, type);
	}

	@Override
	public Regulates<I, RV, RVT, RE, RET> self() {
		return this;
	}
}
