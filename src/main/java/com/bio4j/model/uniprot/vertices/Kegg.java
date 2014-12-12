package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinKegg;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Kegg <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		Kegg<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.KeggType,
		I, RV, RVT, RE, RET
		> {

	public Kegg(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.KeggType type) {
		super(vertex, type);
	}

	@Override
	public Kegg<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinKegg
	// ingoing
	public Stream<ProteinKegg<I, RV, RVT, RE, RET>> proteinKegg_in(){
		return inMany(graph().ProteinKegg());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinKegg_inV(){
		return inManyV(graph().ProteinKegg());
	}


}
