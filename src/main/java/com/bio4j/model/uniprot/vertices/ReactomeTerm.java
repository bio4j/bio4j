package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinReactomeTerm;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class ReactomeTerm <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		ReactomeTerm<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ReactomeTermType,
		I, RV, RVT, RE, RET
		> {

	public ReactomeTerm(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ReactomeTermType type) {
		super(vertex, type);
	}

	@Override
	public ReactomeTerm<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String pathwayName() {
		return get(type().pathwayName);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinReactomeTerm
	// ingoing
	public Stream<ProteinReactomeTerm<I, RV, RVT, RE, RET>> proteinReactomeTerm_in(){
		return inMany(graph().ProteinReactomeTerm());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinReactomeTerm_inV(){
		return inManyV(graph().ProteinReactomeTerm());
	}


}
