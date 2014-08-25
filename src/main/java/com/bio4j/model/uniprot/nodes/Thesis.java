package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ReferenceThesis;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class Thesis<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Thesis<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ThesisType,
		I, RV, RVT, RE, RET
		>  {

	public Thesis(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ThesisType type) {
		super(vertex, type);
	}

	@Override
	public Thesis<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referenceThesis
	// ingoing
	public ReferenceThesis<I, RV, RVT, RE, RET> referenceThesis_in(){
		return inOne(graph().ReferenceThesis());
	}
	public Reference<I, RV, RVT, RE, RET> referenceThesis_inNode(){
        return inOneV(graph().ReferenceThesis());
	}
}
