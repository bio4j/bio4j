package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Isoform <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Isoform<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.IsoformType,
		I, RV, RVT, RE, RET
		> {

	public Isoform(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.IsoformType type) {
		super(vertex, type);
	}

	@Override
	public Isoform<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String name() {
		return get(type().name);
	}
	public String note() {
		return get(type().note);
	}
	public String sequence() {
		return get(type().sequence);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships


}
