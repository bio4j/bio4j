package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.EMBL;
import com.bio4j.model.uniprot.vertices.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinEMBL <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinEMBL<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEMBLType,
				EMBL<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.EMBLType,
				I, RV, RVT, RE, RET
				> {

	public ProteinEMBL(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEMBLType type) {

		super(edge, type);
	}

	@Override
	public ProteinEMBL<I, RV, RVT, RE, RET> self() {
		return this;
	}
}