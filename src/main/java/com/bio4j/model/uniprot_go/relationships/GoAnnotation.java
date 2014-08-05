package com.bio4j.model.uniprot_go.relationships;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GoAnnotation<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGoGraph.UniprotGoEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				GoAnnotation<I, RV, RVT, RE, RET>, UniprotGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType,
				GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
				I, RV, RVT, RE, RET
				> {

	public GoAnnotation(RE edge, UniprotGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType type) {

		super(edge, type);
	}

	@Override
	public GoAnnotation<I, RV, RVT, RE, RET> self() {
		return this;
	}
}