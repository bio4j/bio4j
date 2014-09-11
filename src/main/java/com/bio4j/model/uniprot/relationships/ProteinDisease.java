package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Disease;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinDisease <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinDisease<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinDiseaseType,
				Disease<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.DiseaseType,
				I, RV, RVT, RE, RET
				> {

	public String status() {
		return get(type().status);
	}
	public String text() {
		return get(type().text);
	}
	public String evidence() {
		return get(type().evidence);
	}

	public ProteinDisease(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinDiseaseType type) {

		super(edge, type);
	}

	@Override
	public ProteinDisease<I, RV, RVT, RE, RET> self() {
		return this;
	}
}