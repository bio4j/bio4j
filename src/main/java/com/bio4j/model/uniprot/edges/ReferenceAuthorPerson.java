package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Person;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 9/9/2014.
 */
public final class ReferenceAuthorPerson<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceAuthorPerson<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceAuthorPersonType,
				Person<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.PersonType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceAuthorPerson(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceAuthorPersonType type) {

		super(edge, type);
	}

	@Override
	public ReferenceAuthorPerson<I, RV, RVT, RE, RET> self() {
		return this;
	}
}