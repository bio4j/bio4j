package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.UniGene;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinUniGene <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinUniGene<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinUniGeneType,
				UniGene<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.UniGeneType,
				I, RV, RVT, RE, RET
				> {

	public ProteinUniGene(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinUniGeneType type) {

		super(edge, type);
	}

	@Override
	public ProteinUniGene<I, RV, RVT, RE, RET> self() {
		return this;
	}
}