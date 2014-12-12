package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Book;
import com.bio4j.model.uniprot.vertices.Person;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class BookEditor<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Book<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.BookType,
				BookEditor<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.BookEditorType,
				Person<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.PersonType,
				I, RV, RVT, RE, RET
				> {

	public BookEditor(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.BookEditorType type) {

		super(edge, type);
	}

	@Override
	public BookEditor<I, RV, RVT, RE, RET> self() {
		return this;
	}
}