package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Interpro;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinInterpro <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinInterpro<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinInterproType,
				Interpro<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InterproType,
				I, RV, RVT, RE, RET
				> {

	public ProteinInterpro(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinInterproType type) {

		super(edge, type);
	}

	@Override
	public ProteinInterpro<I, RV, RVT, RE, RET> self() {
		return this;
	}
}