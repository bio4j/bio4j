package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinComment;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class CommentType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniProtVertex<
		CommentType<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType,
		I, RV, RVT, RE, RET
		> {

	public CommentType(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType type) {
		super(vertex, type);
	}

	@Override
	public CommentType<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinComment
	// ingoing
	public Stream<ProteinComment<I, RV, RVT, RE, RET>> proteinComment_in(){
		return inMany(graph().ProteinComment());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinComment_inV(){
		return inManyV(graph().ProteinComment());
	}


}
