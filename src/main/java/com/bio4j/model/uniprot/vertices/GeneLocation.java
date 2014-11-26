package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinGeneLocation;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GeneLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		GeneLocation<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.GeneLocationType,
		I, RV, RVT, RE, RET
		> {

	public GeneLocation(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.GeneLocationType type) {
		super(vertex, type);
	}

	@Override
	public GeneLocation<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinGeneLocation
	// ingoing
	public Stream<ProteinGeneLocation<I, RV, RVT, RE, RET>> proteinGeneLocation_in(){
		return inMany(graph().ProteinGeneLocation());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinGeneLocation_inV(){
		return inManyV(graph().ProteinGeneLocation());
	}


}
