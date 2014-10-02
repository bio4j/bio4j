package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Organism;
import com.bio4j.model.uniprot.vertices.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinOrganism <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinOrganism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinOrganismType,
				Organism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismType,
				I, RV, RVT, RE, RET
				> {

	public ProteinOrganism(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinOrganismType type) {

		super(edge, type);
	}

	@Override
	public ProteinOrganism<I, RV, RVT, RE, RET> self() {
		return this;
	}
}