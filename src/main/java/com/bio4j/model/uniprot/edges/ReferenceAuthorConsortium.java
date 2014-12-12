package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Consortium;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 9/9/2014.
 */
public final class ReferenceAuthorConsortium <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceAuthorConsortium<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceAuthorConsortiumType,
				Consortium<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ConsortiumType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceAuthorConsortium(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceAuthorConsortiumType type) {

		super(edge, type);
	}

	@Override
	public ReferenceAuthorConsortium<I, RV, RVT, RE, RET> self() {
		return this;
	}
}