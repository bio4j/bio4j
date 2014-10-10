package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.IsoformEventGenerator;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 10/7/2014.
 */
public class AlternativeProduct <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		AlternativeProduct<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.AlternativeProductType,
		I, RV, RVT, RE, RET
		> {

	public AlternativeProduct(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.AlternativeProductType type) {
		super(vertex, type);
	}

	@Override
	public AlternativeProduct<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// IsoformEventGenerator
	// ingoing
	public Stream<IsoformEventGenerator<I, RV, RVT, RE, RET>> isoformEventGenerator_in(){
		return inMany(graph().IsoformEventGenerator());
	}
	public Stream<Isoform<I, RV, RVT, RE, RET>> isoformEventGenerator_inV(){
		return inManyV(graph().IsoformEventGenerator());
	}


}
