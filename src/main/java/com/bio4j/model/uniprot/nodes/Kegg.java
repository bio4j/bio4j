package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinKegg;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Kegg <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Kegg<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.KeggType,
		I, RV, RVT, RE, RET
		> {

	public Kegg(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.KeggType type) {
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
	public List<ProteinKegg<I, RV, RVT, RE, RET>> proteinKegg_in(){
		return inMany(graph().ProteinKegg());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinKegg_inNodes(){
		return inManyV(graph().ProteinKegg());
	}


}
