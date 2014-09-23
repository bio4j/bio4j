package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Isoform;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 9/23/2014.
 */
public class IsoformProteinInteraction<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Isoform<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.IsoformType,
				IsoformProteinInteraction<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.IsoformProteinInteractionType,
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				I, RV, RVT, RE, RET
				> {

	public IsoformProteinInteraction(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.IsoformProteinInteractionType type) {

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
	public IsoformProteinInteraction<I, RV, RVT, RE, RET> self() {
		return this;
	}
}

