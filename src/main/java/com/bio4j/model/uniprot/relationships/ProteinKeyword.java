package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Keyword;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinKeyword <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinKeyword<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeywordType,
				Keyword<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.KeywordType,
				I, RV, RVT, RE, RET
				> {

	public ProteinKeyword(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinKeywordType type) {

		super(edge, type);
	}

	@Override
	public ProteinKeyword<I, RV, RVT, RE, RET> self() {
		return this;
	}
}