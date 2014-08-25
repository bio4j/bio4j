package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ReferencePatent;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class Patent<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Patent<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.PatentType,
		I, RV, RVT, RE, RET
		>  {

	public Patent(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.PatentType type) {
		super(vertex, type);
	}

	@Override
	public Patent<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}
	public String number() {
		return get(type().number);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referencePatent
	// ingoing
	public ReferencePatent<I, RV, RVT, RE, RET> referencePatent_in(){
		return inOne(graph().ReferencePatent());
	}
	public Reference<I, RV, RVT, RE, RET> referencePatent_inNode(){
		return inOneV(graph().ReferencePatent());
	}
}
