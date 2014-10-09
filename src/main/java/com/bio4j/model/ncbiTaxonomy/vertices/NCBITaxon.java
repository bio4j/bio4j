package com.bio4j.model.ncbiTaxonomy.vertices;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.edges.NCBITaxonParent;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

public final class NCBITaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends NCBITaxonomyGraph.NCBITaxonomyVertex<
		NCBITaxon<I, RV, RVT, RE, RET>,
		NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
		I, RV, RVT, RE, RET
		> {

	public NCBITaxon(RV vertex, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType type) {
		super(vertex, type);
	}

	@Override
	public NCBITaxon<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	public String name() {
		return get(type().name);
	}

	public String comment() {
		return get(type().comment);
	}

	public String scientificName() {
		return get(type().scientificName);
	}

	public String taxonomicRank() {
		return get(type().taxonomicRank);
	}

	//----ncbiTaxonParent-------
	// ingoing
	public Stream<NCBITaxonParent<I, RV, RVT, RE, RET>> ncbiTaxonParent_in(){
		return inMany(graph().NCBITaxonParent());
	}
	public Stream<NCBITaxon<I, RV, RVT, RE, RET>> ncbiTaxonParent_inV(){
		return inManyV(graph().NCBITaxonParent());
	}

	//----ncbiTaxonParent-------
	// outgoing
	public NCBITaxonParent<I, RV, RVT, RE, RET> ncbiTaxonParent_out(){
		return outOne(graph().NCBITaxonParent());
	}
	public NCBITaxon<I, RV, RVT, RE, RET> ncbiTaxonParent_outV(){
		return outOneV(graph().NCBITaxonParent());
	}

	//----proteinNCBITaxon-------
	// ingoing
	public Stream<ProteinNCBITaxon<I, RV, RVT, RE, RET>> proteinNCBITaxon_in(){
		return inMany(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinNCBITaxon_inV(){
		return inManyV(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}

}
