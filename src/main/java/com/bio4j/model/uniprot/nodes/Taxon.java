package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinTaxon;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Taxon <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Taxon<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.TaxonType,
		I, RV, RVT, RE, RET
		> {

	public Taxon(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.TaxonType type) {
		super(vertex, type);
	}

	@Override
	public Taxon<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// taxonParent
	// ingoing
	public ProteinTaxon<I, RV, RVT, RE, RET> proteinTaxon_in(){
		return inOne(graph().ProteinTaxon());
	}
	public Taxon<I, RV, RVT, RE, RET> proteinTaxon_inNode(){
		return inOneV(graph().ProteinTaxon());
	}


}
