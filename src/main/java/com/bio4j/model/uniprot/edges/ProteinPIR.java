package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.PIR;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinPIR <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinPIR<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinPIRType,
				PIR<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.PIRType,
				I, RV, RVT, RE, RET
				> {

	public ProteinPIR(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinPIRType type) {

		super(edge, type);
	}

	@Override
	public ProteinPIR<I, RV, RVT, RE, RET> self() {
		return this;
	}
}