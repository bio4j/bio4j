package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinUniGene;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniGene <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		UniGene<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.UniGeneType,
		I, RV, RVT, RE, RET
		> {

	public UniGene(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.UniGeneType type) {
		super(vertex, type);
	}

	@Override
	public UniGene<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinUniGene
	// ingoing
	public Stream<ProteinUniGene<I, RV, RVT, RE, RET>> proteinUniGene_in(){
		return inMany(graph().ProteinUniGene());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinUniGene_inV(){
		return inManyV(graph().ProteinUniGene());
	}


}
