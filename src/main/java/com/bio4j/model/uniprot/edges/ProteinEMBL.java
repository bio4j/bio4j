package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.EMBL;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinEMBL <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinEMBL<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinEMBLType,
				EMBL<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.EMBLType,
				I, RV, RVT, RE, RET
				> {

	public ProteinEMBL(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinEMBLType type) {

		super(edge, type);
	}

	@Override
	public ProteinEMBL<I, RV, RVT, RE, RET> self() {
		return this;
	}
}