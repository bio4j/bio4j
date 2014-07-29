package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinFeature <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinFeature<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinFeatureType,
				FeatureType<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.FeatureTypeType,
				I, RV, RVT, RE, RET
				> {

	public ProteinFeature(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinFeatureType type) {

		super(edge, type);
	}

	@Override
	public ProteinFeature<I, RV, RVT, RE, RET> self() {
		return this;
	}
}