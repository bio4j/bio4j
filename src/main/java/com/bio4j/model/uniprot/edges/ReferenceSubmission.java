package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Submission;
import com.bio4j.model.uniprot.vertices.Reference;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferenceSubmission <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Reference<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
				ReferenceSubmission<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceSubmissionType,
				Submission<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubmissionType,
				I, RV, RVT, RE, RET
				> {

	public ReferenceSubmission(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceSubmissionType type) {

		super(edge, type);
	}

	@Override
	public ReferenceSubmission<I, RV, RVT, RE, RET> self() {
		return this;
	}
}