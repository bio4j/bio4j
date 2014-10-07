package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.RefSeq;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinRefSeq <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinRefSeq<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinRefSeqType,
				RefSeq<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.RefSeqType,
				I, RV, RVT, RE, RET
				> {

	public ProteinRefSeq(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinRefSeqType type) {

		super(edge, type);
	}

	@Override
	public ProteinRefSeq<I, RV, RVT, RE, RET> self() {
		return this;
	}
}