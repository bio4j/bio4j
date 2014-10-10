package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.InstituteCountry;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Country <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Country<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.CountryType,
		I, RV, RVT, RE, RET
		> {

	public Country(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.CountryType type) {
		super(vertex, type);
	}

	@Override
	public Country<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// instituteCountry
	// ingoing
	public Stream<InstituteCountry<I, RV, RVT, RE, RET>> instituteCountry_in(){
		return inMany(graph().InstituteCountry());
	}
	public Stream<Institute<I, RV, RVT, RE, RET>> instituteCountry_inV(){
		return inManyV(graph().InstituteCountry());
	}


}
