package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.SubmissionDB;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

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
	public Stream<SubmissionDB<I, RV, RVT, RE, RET>> submissionDB_in(){
		return inMany(graph().SubmissionDB());
	}
	public Stream<Submission<I, RV, RVT, RE, RET>> submissionDB_inV(){
		return inManyV(graph().SubmissionDB());
	}


}
