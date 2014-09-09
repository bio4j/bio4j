package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Book;
import com.bio4j.model.uniprot.nodes.Person;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class BookEditor<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType,
				BookEditor<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookEditorType,
				Person<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PersonType,
				I, RV, RVT, RE, RET
				> {

	public BookEditor(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.BookEditorType type) {

		super(edge, type);
	}

	@Override
	public BookEditor<I, RV, RVT, RE, RET> self() {
		return this;
	}
}