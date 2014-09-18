package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.SequenceCaution;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinSequenceCaution <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinSequenceCaution<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinSequenceCautionType,
				SequenceCaution<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SequenceCautionType,
				I, RV, RVT, RE, RET
				> {

	public ProteinSequenceCaution(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinSequenceCautionType type) {

		super(edge, type);
	}

	@Override
	public ProteinSequenceCaution<I, RV, RVT, RE, RET> self() {
		return this;
	}
}