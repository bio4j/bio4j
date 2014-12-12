package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 9/23/2014.
 */
public class ProteinProteinInteraction<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinProteinInteraction<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinProteinInteractionType,
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				I, RV, RVT, RE, RET
				> {

	public ProteinProteinInteraction(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinProteinInteractionType type) {

		super(edge, type);
	}

	// properties
	public String experiments() {
		return get(type().experiments);
	}
	public String organismsDiffer() {    return get(type().organismsDiffer);	}
	public String intActId1() {
		return get(type().intActId1);
	}
	public String intActId2() {
		return get(type().intActId2);
	}

	@Override
	public ProteinProteinInteraction<I, RV, RVT, RE, RET> self() {
		return this;
	}
}

