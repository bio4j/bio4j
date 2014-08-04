package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.SubmissionDB;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.Date;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Submission <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Submission<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType,
		I, RV, RVT, RE, RET
		>  {

	public Submission(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType type) {
		super(vertex, type);
	}

	@Override
	public Submission<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// submissionDB
	// outgoing
	public SubmissionDB<I, RV, RVT, RE, RET> submissionDB_out(){
		outOne(graph().SubmissionDB());
	}
	public DB<I, RV, RVT, RE, RET> submissionDB_outNode(){
		outOneV(graph().SubmissionDB());
	}
}
