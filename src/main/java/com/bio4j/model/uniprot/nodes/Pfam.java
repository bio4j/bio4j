package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinPfam;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/29/2014.
 */
public class Pfam <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Pfam<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.PfamType,
		I, RV, RVT, RE, RET
		> {

	public Pfam(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.PfamType type) {
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
	public List<ProteinPfam<I, RV, RVT, RE, RET>> proteinPfam_in(){
		return inMany(graph().ProteinPfam());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinPfam_inNodes(){
		return inManyV(graph().ProteinPfam());
	}


}
