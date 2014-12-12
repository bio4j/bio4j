package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinInterpro;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Interpro <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		Interpro<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.InterproType,
		I, RV, RVT, RE, RET
		> {

	public Interpro(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.InterproType type) {
		super(vertex, type);
	}

	@Override
	public Interpro<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinInterpro
	// ingoing
	public Stream<ProteinInterpro<I, RV, RVT, RE, RET>> proteinInterpro_in(){
		return inMany(graph().ProteinInterpro());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinInterpro_inV(){
		return inManyV(graph().ProteinInterpro());
	}


}
