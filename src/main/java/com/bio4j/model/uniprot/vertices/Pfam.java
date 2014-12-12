package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinPfam;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public class Pfam <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniProtVertex<
		Pfam<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.PfamType,
		I, RV, RVT, RE, RET
		> {

	public Pfam(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PfamType type) {
		super(vertex, type);
	}

	@Override
	public Pfam<I, RV, RVT, RE, RET> self() {
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

	// proteinPfam
	// ingoing
	public Stream<ProteinPfam<I, RV, RVT, RE, RET>> proteinPfam_in(){
		return inMany(graph().ProteinPfam());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinPfam_inV(){
		return inManyV(graph().ProteinPfam());
	}


}
