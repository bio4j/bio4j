package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Ensembl;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinEnsembl <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinEnsembl<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEnsemblType,
				Ensembl<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.EnsemblType,
				I, RV, RVT, RE, RET
				> {

	public ProteinEnsembl(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinEnsemblType type) {

		super(edge, type);
	}

	@Override
	public ProteinEnsembl<I, RV, RVT, RE, RET> self() {
		return this;
	}
}