package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.BookCity;
import com.bio4j.model.uniprot.relationships.ProteinReference;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class CommentType <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		CommentType<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.CommentTypeType,
		I, RV, RVT, RE, RET
		> {

	public CommentType(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.CommentTypeType type) {
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
	public List<ProteinComment<I, RV, RVT, RE, RET>> proteinComment_in();
	public List<Protein<I, RV, RVT, RE, RET>> proteinComment_inNodes();


}
