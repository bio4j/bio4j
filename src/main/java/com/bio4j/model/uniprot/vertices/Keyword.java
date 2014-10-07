package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinKeyword;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/29/2014.
 */
public class Keyword <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Keyword<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.KeywordType,
		I, RV, RVT, RE, RET
		> {

	public Keyword(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.KeywordType type) {
		super(vertex, type);
	}

	@Override
	public Keyword<I, RV, RVT, RE, RET> self() {
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

	// proteinKeyword
	// ingoing
	public List<ProteinKeyword<I, RV, RVT, RE, RET>> proteinKeyword_in(){
		return inMany(graph().ProteinKeyword());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinKeyword_inV(){
		return inManyV(graph().ProteinKeyword());
	}


}
