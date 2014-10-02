package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Pfam;
import com.bio4j.model.uniprot.vertices.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinPfam <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinPfam<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinPfamType,
				Pfam<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PfamType,
				I, RV, RVT, RE, RET
				> {

	public ProteinPfam(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinPfamType type) {

		super(edge, type);
	}

	@Override
	public ProteinPfam<I, RV, RVT, RE, RET> self() {
		return this;
	}
}