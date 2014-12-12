package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.GeneLocation;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinGeneLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinGeneLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinGeneLocationType,
				GeneLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.GeneLocationType,
				I, RV, RVT, RE, RET
				> {

	public ProteinGeneLocation(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinGeneLocationType type) {
		super(edge, type);
	}

	// properties
	public String name() {
		return get(type().name);
	}


	@Override
	public ProteinGeneLocation<I, RV, RVT, RE, RET> self() {
		return this;
	}
}