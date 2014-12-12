package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinFeature;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class FeatureType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		FeatureType<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.FeatureTypeType,
		I, RV, RVT, RE, RET
		> {

	public FeatureType(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.FeatureTypeType type) {
		super(vertex, type);
	}

	@Override
	public FeatureType<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinFeature
	// ingoing
	public Stream<ProteinFeature<I, RV, RVT, RE, RET>> proteinFeature_in(){
		return inMany(graph().ProteinFeature());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinFeature_inV(){
		return inManyV(graph().ProteinFeature());
	}


}
