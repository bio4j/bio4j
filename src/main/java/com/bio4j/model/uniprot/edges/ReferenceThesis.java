package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.model.uniprot.vertices.Thesis;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferenceThesis <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceThesis<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceThesisType,
				Thesis<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ThesisType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceThesis(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceThesisType type) {

		super(edge, type);
	}

	@Override
	public ReferenceThesis<I, RV, RVT, RE, RET> self() {
		return this;
	}
}