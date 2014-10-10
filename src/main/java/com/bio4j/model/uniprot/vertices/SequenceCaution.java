package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinSequenceCaution;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

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

	// proteinSequenceCaution
	// ingoing
	public Stream<ProteinSequenceCaution<I, RV, RVT, RE, RET>> proteinSequenceCaution_in(){
		return inMany(graph().ProteinSequenceCaution());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>>  proteinSequenceCaution_inV(){
		return inManyV(graph().ProteinSequenceCaution());
	}


}
