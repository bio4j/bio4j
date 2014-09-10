package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Book;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferenceBook <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceBook<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceBookType,
				Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType,
				I, RV, RVT, RE, RET
				> {

	// properties
	public String title() {
		return get(type().title);
	}
	public int first() {    return get(type().first);	}
	public int last() {
		return get(type().last);
	}
	public String volume() {
		return get(type().volume);
	}

	public ReferenceBook(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceBookType type) {

		super(edge, type);
	}

	@Override
	public ReferenceBook<I, RV, RVT, RE, RET> self() {
		return this;
	}
}