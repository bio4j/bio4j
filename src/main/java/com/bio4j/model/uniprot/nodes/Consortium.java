package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.BookCity;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Consortium <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Consortium<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ConsortiumType,
		I, RV, RVT, RE, RET
		> {

	public Consortium(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ConsortiumType type) {
		super(vertex, type);
	}

	@Override
	public Consortium<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships


}
