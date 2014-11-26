package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.OrganismTaxon;
import com.bio4j.model.uniprot.edges.TaxonParent;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
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

	// organismTaxon
	// ingoing
	public Optional<Stream<OrganismTaxon<I, RV, RVT, RE, RET>>> organismTaxon_in(){
		return inManyOptional(graph().OrganismTaxon());
	}
	public Optional<Stream<Organism<I, RV, RVT, RE, RET>>> organismTaxon_inV(){
		return inManyOptionalV(graph().OrganismTaxon());
	}

	// taxonParent
	// ingoing
	public Optional<Stream<TaxonParent<I, RV, RVT, RE, RET>>> taxonParent_in(){
		return inManyOptional(graph().TaxonParent());
	}
	public Optional<Stream<Taxon<I, RV, RVT, RE, RET>>> taxonParent_inV(){
		return inManyOptionalV(graph().TaxonParent());
	}

	// taxonParent
	// outgoing
	public Optional<TaxonParent<I, RV, RVT, RE, RET>> taxonParent_out(){
		return outOneOptional(graph().TaxonParent());
	}
	public Optional<Taxon<I, RV, RVT, RE, RET>> taxonParent_outV(){
		return outOneOptionalV(graph().TaxonParent());
	}


}
