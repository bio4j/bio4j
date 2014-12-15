package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.uniprot.edges.ProteinGeneName;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GeneName <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniProtVertex<
		GeneName<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.GeneNameType,
		I, RV, RVT, RE, RET
		> {

	public GeneName(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.GeneNameType type) {
		super(vertex, type);
	}

	@Override
	public GeneName<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinGeneName
	// ingoing
	public Stream<ProteinGeneName<I, RV, RVT, RE, RET>> proteinGeneName_in(){
		return inMany(graph().ProteinGeneName());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinGeneName_inV(){
		return inManyV(graph().ProteinGeneName());
	}


}
