package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.BookCity;
import com.bio4j.model.uniprot.edges.BookPublisher;
import com.bio4j.model.uniprot.edges.ReferenceBook;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
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
	// ingoing
	public ReferenceBook<I, RV, RVT, RE, RET> referenceBook_in(){
		return inOne(graph().ReferenceBook());
	}
	public Reference<I, RV, RVT, RE, RET> referenceBook_inV(){
		return inOneV(graph().ReferenceBook());
	}

	// bookCity
	// outgoing
	public BookCity<I, RV, RVT, RE, RET> bookCity_out(){
		return outOne(graph().BookCity());
	}
	public City<I, RV, RVT, RE, RET> bookCity_outV(){
		return outOneV(graph().BookCity());
	}

    // bookPublisher
    // outgoing
    public BookPublisher<I, RV, RVT, RE, RET> bookPublisher_out(){
        return outOne(graph().BookPublisher());
    }
    public Publisher<I, RV, RVT, RE, RET> bookPublisher_outV(){
        return outOneV(graph().BookPublisher());
    }
}
