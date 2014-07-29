package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.model.uniprot.relationships.ProteinFeature;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class FeatureType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		FeatureType<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.FeatureTypeType,
		I, RV, RVT, RE, RET
		> {

	public FeatureType(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.FeatureTypeType type) {
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
	public List<ProteinFeature<I, RV, RVT, RE, RET>> proteinFeature_in(){
		return inMany(graph().ProteinFeature());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinFeature_inNodes(){
		return inManyV(graph().ProteinFeature());
	}


}
