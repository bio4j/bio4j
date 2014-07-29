package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Interpro <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Interpro<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.InterproType,
		I, RV, RVT, RE, RET
		> {

	public Interpro(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.InterproType type) {
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
	public List<ProteinInterpro<I, RV, RVT, RE, RET>> proteinInterpro_in(){
		return inMany(graph().ProteinInterpro());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinInterpro_inNodes(){
		return inManyV(graph().ProteinInterpro());
	}


}
