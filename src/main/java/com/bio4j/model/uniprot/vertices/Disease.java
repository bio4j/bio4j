package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinDisease;
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
	public String description() { return get(type().description);}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinDisease
	// ingoing
	public List<ProteinDisease<I, RV, RVT, RE, RET>> proteinDisease_in(){
		return inMany(graph().ProteinDisease());
	}
	public List<Protein<I, RV, RVT, RE, RET>>  proteinDisease_inV(){
		return inManyV(graph().ProteinDisease());
	}


}
