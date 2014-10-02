package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinRefSeq;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class RefSeq <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		RefSeq<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.RefSeqType,
		I, RV, RVT, RE, RET
		> {

	public RefSeq(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.RefSeqType type) {
		super(vertex, type);
	}

	@Override
	public RefSeq<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String nucleotideSequenceId() {
		return get(type().nucleotideSequenceId);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinRefSeq
	// ingoing
	public List<ProteinRefSeq<I, RV, RVT, RE, RET>> proteinRefSeq_in(){
		return inMany(graph().ProteinRefSeq());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinRefSeq_inV(){
		return inManyV(graph().ProteinRefSeq());
	}


}
