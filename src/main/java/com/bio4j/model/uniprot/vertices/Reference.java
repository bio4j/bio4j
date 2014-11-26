package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinReference;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Reference <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Reference<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
		I, RV, RVT, RE, RET
		> {

	public Reference(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType type) {
		super(vertex, type);
	}

	@Override
	public Reference<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String date() {
		return get(type().date);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinReference
	// ingoing
	public Stream<ProteinReference<I, RV, RVT, RE, RET>> proteinReference_in(){
		return inMany(graph().ProteinReference());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinReference_inV(){
		return inManyV(graph().ProteinReference());
	}


}
