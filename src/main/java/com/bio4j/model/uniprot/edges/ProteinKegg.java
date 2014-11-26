package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Kegg;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinKegg <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinKegg<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeggType,
				Kegg<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.KeggType,
				I, RV, RVT, RE, RET
				> {

	public ProteinKegg(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeggType type) {

		super(edge, type);
	}

	@Override
	public ProteinKegg<I, RV, RVT, RE, RET> self() {
		return this;
	}
}