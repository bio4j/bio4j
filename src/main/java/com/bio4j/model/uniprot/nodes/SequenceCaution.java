package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinComment;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class SequenceCaution <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		SequenceCaution<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.SequenceCautionType,
		I, RV, RVT, RE, RET
		> {

	public SequenceCaution(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.SequenceCautionType type) {
		super(vertex, type);
	}

	@Override
	public SequenceCaution<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships



}
