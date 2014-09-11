package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.SubcellularLocation;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinSubcellularLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinSubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinSubcellularLocationType,
				SubcellularLocation<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
				I, RV, RVT, RE, RET
				> {

	// properties
	public String status() {
		return get(type().status);
	}
	public String topology() {    return get(type().topology);	}
	public String topologyStatus() {    return get(type().topologyStatus);	}
	public String evidence() {
		return get(type().evidence);
	}

	public ProteinSubcellularLocation(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinSubcellularLocationType type) {

		super(edge, type);
	}

	@Override
	public ProteinSubcellularLocation<I, RV, RVT, RE, RET> self() {
		return this;
	}
}