package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Country;
import com.bio4j.model.uniprot.vertices.Institute;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class InstituteCountry<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Institute<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.InstituteType,
				InstituteCountry<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.InstituteCountryType,
				Country<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.CountryType,
				I, RV, RVT, RE, RET
				> {

	public InstituteCountry(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.InstituteCountryType type) {

		super(edge, type);
	}

	@Override
	public InstituteCountry<I, RV, RVT, RE, RET> self() {
		return this;
	}
}