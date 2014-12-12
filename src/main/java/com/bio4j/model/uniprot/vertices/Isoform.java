package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinIsoformInteraction;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Isoform <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		Isoform<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.IsoformType,
		I, RV, RVT, RE, RET
		> {

	public Isoform(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.IsoformType type) {
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

	// proteinIsoformInteraction
	// ingoing
	public Stream<ProteinIsoformInteraction<I, RV, RVT, RE, RET>> proteinIsoformInteraction_in(){
		return inMany(graph().ProteinIsoformInteraction());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinIsoformInteraction_inV(){
		return inManyV(graph().ProteinIsoformInteraction());
	}


}
