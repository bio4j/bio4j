package com.bio4j.model.uniprot_enzymedb.relationships;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class EnzymaticActivity<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotEnzymeDBGraph.UniprotEnzymeDBEdge<
				// src
				Protein<I, RV, RVT, RE, RET>,
				UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				UniprotGraph<I, RV, RVT, RE, RET>,
				// edge
				EnzymaticActivity<I, RV, RVT, RE, RET>,
				UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymaticActivityType,
				//tgt
				Enzyme<I, RV, RVT, RE, RET>,
				EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeType,
				EnzymeDBGraph<I, RV, RVT, RE, RET>,
				// raw stuff
				I, RV, RVT, RE, RET
				> {

	public EnzymaticActivity(RE edge, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymaticActivityType type) {

		super(edge, type);
	}

	@Override
	public EnzymaticActivity<I, RV, RVT, RE, RET> self() {
		return this;
	}
}