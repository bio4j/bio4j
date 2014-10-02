package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.model.uniprot.vertices.UnpublishedObservation;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferenceUnpublishedObservation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceUnpublishedObservation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceUnpublishedObservationType,
				UnpublishedObservation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceUnpublishedObservation(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceUnpublishedObservationType type) {

		super(edge, type);
	}

	@Override
	public ReferenceUnpublishedObservation<I, RV, RVT, RE, RET> self() {
		return this;
	}
}