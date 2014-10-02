package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.ReactomeTerm;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinReactomeTerm <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinReactomeTerm<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinReactomeTermType,
				ReactomeTerm<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReactomeTermType,
				I, RV, RVT, RE, RET
				> {

	public ProteinReactomeTerm(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinReactomeTermType type) {

		super(edge, type);
	}

	@Override
	public ProteinReactomeTerm<I, RV, RVT, RE, RET> self() {
		return this;
	}
}