package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.OrganismTaxon;
import com.bio4j.model.uniprot.edges.ProteinOrganism;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Organism <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniProtVertex<
		Organism<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.OrganismType,
		I, RV, RVT, RE, RET
		> {

	public Organism(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.OrganismType type) {
		super(vertex, type);
	}

	@Override
	public Organism<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String scientificName() {
		return get(type().scientificName);
	}
	public String commonName() {
		return get(type().commonName);
	}
	public String synonymName() {
		return get(type().synonymName);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// organismTaxon
	// outgoing
	public OrganismTaxon<I, RV, RVT, RE, RET> organismTaxon_out(){
		return outOne(graph().OrganismTaxon());
	}
	public Taxon<I, RV, RVT, RE, RET> organismTaxon_outV(){
		return outOneV(graph().OrganismTaxon());
	}

	// proteinOrganism
	// ingoing
	public Stream<ProteinOrganism<I, RV, RVT, RE, RET>> proteinOrganism_in(){
		return inMany(graph().ProteinOrganism());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinOrganism_inV(){
		return inManyV(graph().ProteinOrganism());
	}


}
