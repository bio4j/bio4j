package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.BookCity;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Disease <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Disease<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.DiseaseType,
		I, RV, RVT, RE, RET
		> {

	public Disease(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.DiseaseType type) {
		super(vertex, type);
	}

	@Override
	public Disease<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}
	public String id() {
		return get(type().id);
	}
	public String acronym() {
		return get(type().acronym);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships



}
