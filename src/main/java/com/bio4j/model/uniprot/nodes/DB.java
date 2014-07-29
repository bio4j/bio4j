package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class DB <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		DB<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.DBType,
		I, RV, RVT, RE, RET
		> {

	public DB(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.DBType type) {
		super(vertex, type);
	}

	@Override
	public DB<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// submissionDB
	// ingoing
	public List<SubmissionDB<I, RV, RVT, RE, RET>> submissionDB_in(){
		return inMany(graph().SubmissionDB());
	}
	public List<Submission<I, RV, RVT, RE, RET>> submissionDB_inNodes(){
		return inManyV(graph().SubmissionDB());
	}


}
