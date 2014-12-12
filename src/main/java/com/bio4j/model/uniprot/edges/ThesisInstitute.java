package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Institute;
import com.bio4j.model.uniprot.vertices.Thesis;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ThesisInstitute <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Thesis<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ThesisType,
				ThesisInstitute<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ThesisInstituteType,
				Institute<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.InstituteType,
				I, RV, RVT, RE, RET
				> {

	public ThesisInstitute(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ThesisInstituteType type) {

		super(edge, type);
	}

	@Override
	public ThesisInstitute<I, RV, RVT, RE, RET> self() {
		return this;
	}
}