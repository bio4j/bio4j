package com.bio4j.model.ncbiTaxonomy_geninfo.edges;

import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GenInfoNCBITaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		NCBITaxonomyGenInfoGraph.NCBITaxonomyGenInfoEdge<
				// src
				GenInfo<I, RV, RVT, RE, RET>,
				GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType,
				GenInfoGraph<I, RV, RVT, RE, RET>,
				// edge
				GenInfoNCBITaxon<I, RV, RVT, RE, RET>,
				NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>.GenInfoNCBITaxonType,
				//tgt
				NCBITaxon<I, RV, RVT, RE, RET>,
				NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
				NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
				// raw stuff
				I, RV, RVT, RE, RET
				> {

	public GenInfoNCBITaxon(RE edge, NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>.GenInfoNCBITaxonType type) {

		super(edge, type);
	}

	@Override
	public GenInfoNCBITaxon<I, RV, RVT, RE, RET> self() {
		return this;
	}
}