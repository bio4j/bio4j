package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Country;
import com.bio4j.model.uniprot.nodes.Institute;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class InstituteCountry<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Institute<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InstituteType,
				InstituteCountry<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.InstituteCountryType,
				Country<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.CountryType,
				I, RV, RVT, RE, RET
				> {

	public InstituteCountry(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.InstituteCountryType type) {

		super(edge, type);
	}

	@Override
	public InstituteCountry<I, RV, RVT, RE, RET> self() {
		return this;
	}
}