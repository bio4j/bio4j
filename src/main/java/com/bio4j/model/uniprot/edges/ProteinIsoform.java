package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 9/23/2014.
 */
public class ProteinIsoform<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinIsoform<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinIsoformType,
				Isoform<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.IsoformType,
				I, RV, RVT, RE, RET
				> {

	public ProteinIsoform(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinIsoformType type) {

		super(edge, type);
	}

	@Override
	public ProteinIsoform<I, RV, RVT, RE, RET> self() {
		return this;
	}
}

