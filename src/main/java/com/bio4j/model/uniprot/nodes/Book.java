package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ReferenceBook;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class Book<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Book<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.BookType,
		I, RV, RVT, RE, RET
		>  {

	public Book(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.BookType type) {
		super(vertex, type);
	}

	@Override
	public Book<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	//referenceBook
	// outgoing
	public ReferenceBook<I, RV, RVT, RE, RET> referenceBook_in(){
		outOne(graph().ReferenceBook());
	}
	public Reference<I, RV, RVT, RE, RET> referenceBook_inNode(){
		outOneV(graph().ReferenceBook());
	}
}
