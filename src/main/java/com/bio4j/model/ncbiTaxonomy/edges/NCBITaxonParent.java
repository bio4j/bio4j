package com.bio4j.model.ncbiTaxonomy.edges;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.angulillos.UntypedGraph;

public final class NCBITaxonParent<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		NCBITaxonomyGraph.NCBITaxonomyEdge<
				NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
				NCBITaxonParent<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonParentType,
				NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
				I, RV, RVT, RE, RET
				> {

	public NCBITaxonParent(RE edge, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonParentType type) {

		super(edge, type);
	}

	@Override
	public NCBITaxonParent<I, RV, RVT, RE, RET> self() {
		return this;
	}
}

