package com.bio4j.model.enzymedb.vertices;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_enzymedb.edges.EnzymaticActivity;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

public final class Enzyme<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends EnzymeDBGraph.EnzymeVertex<
		Enzyme<I, RV, RVT, RE, RET>,
		EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeType,
		I, RV, RVT, RE, RET
		> {

	public Enzyme(RV vertex, EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeType type) {
		super(vertex, type);
	}

	@Override
	public Enzyme<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	public String[] cofactors() {
		return get(type().cofactors);
	}

	public String officialName() {
		return get(type().officialName);
	}

	public String[] alternateNames() {
		return get(type().alternateNames);
	}

	public String comment() {
		return get(type().comment);
	}

	public String catalyticActivity() {
		return get(type().catalyticActivity);
	}

	public String[] prositeCrossReferences() {
		return get(type().prositeCrossReferences);
	}



	//-----enzymaticActivity----
	// ingoing
	public Stream<EnzymaticActivity<I, RV, RVT, RE, RET>> enzymaticActivity_in(){   return inMany(graph().uniprotEnzymeDBGraph().EnzymaticActivity());}
	public Stream<Protein<I, RV, RVT, RE, RET>> enzymaticActivity_inV(){   return inManyV(graph().uniprotEnzymeDBGraph().EnzymaticActivity());}


}
