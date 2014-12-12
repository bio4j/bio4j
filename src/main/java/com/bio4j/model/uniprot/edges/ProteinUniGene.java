package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.UniGene;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinUniGene <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinUniGene<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinUniGeneType,
				UniGene<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.UniGeneType,
				I, RV, RVT, RE, RET
				> {

	public ProteinUniGene(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinUniGeneType type) {

		super(edge, type);
	}

	@Override
	public ProteinUniGene<I, RV, RVT, RE, RET> self() {
		return this;
	}
}