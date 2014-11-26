package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Book;
import com.bio4j.model.uniprot.vertices.Publisher;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class BookPublisher<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Book<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookType,
				BookPublisher<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.BookPublisherType,
				Publisher<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PublisherType,
				I, RV, RVT, RE, RET
				> {

	public BookPublisher(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.BookPublisherType type) {

		super(edge, type);
	}

	@Override
	public BookPublisher<I, RV, RVT, RE, RET> self() {
		return this;
	}
}