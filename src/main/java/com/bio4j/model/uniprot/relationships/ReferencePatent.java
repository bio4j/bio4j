package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Patent;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferencePatent <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferencePatent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferencePatentType,
				Patent<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PatentType,
				I, RV, RVT, RE, RET
				> {

	public ReferencePatent(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ReferencePatentType type) {

		super(edge, type);
	}

	@Override
	public ReferencePatent<I, RV, RVT, RE, RET> self() {
		return this;
	}
}