package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ThesisInstitute;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Institute <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Institute<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.InstituteType,
		I, RV, RVT, RE, RET
		> {

	public Institute(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.InstituteType type) {
		super(vertex, type);
	}

	@Override
	public Institute<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// thesisInstitute
	// ingoing
	public List<ThesisInstitute<I, RV, RVT, RE, RET>> thesisInstitute_in(){
		return inMany(graph().ThesisInstitute());
	}
	public List<Thesis<I, RV, RVT, RE, RET>> thesisInstitute_inNodes(){
		return inManyV(graph().ThesisInstitute());
	}


}
