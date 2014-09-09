package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 9/9/2014.
 */
public final class ReferenceAuthor <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceAuthor<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceAuthorType,
				Person<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PersonType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceAuthor(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceAuthorType type) {

		super(edge, type);
	}

	@Override
	public ReferenceAuthor<I, RV, RVT, RE, RET> self() {
		return this;
	}
}