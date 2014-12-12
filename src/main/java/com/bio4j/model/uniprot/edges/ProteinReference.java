package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinReference <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinReference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinReferenceType,
				Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
				I, RV, RVT, RE, RET
				> {

	public ProteinReference(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinReferenceType type) {

		super(edge, type);
	}

	@Override
	public ProteinReference<I, RV, RVT, RE, RET> self() {
		return this;
	}
}