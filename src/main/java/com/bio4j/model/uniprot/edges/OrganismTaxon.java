package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.Organism;
import com.bio4j.model.uniprot.vertices.Taxon;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class OrganismTaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Organism<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismType,
				OrganismTaxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OrganismTaxonType,
				Taxon<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.TaxonType,
				I, RV, RVT, RE, RET
				> {

	public OrganismTaxon(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.OrganismTaxonType type) {

		super(edge, type);
	}

	@Override
	public OrganismTaxon<I, RV, RVT, RE, RET> self() {
		return this;
	}
}