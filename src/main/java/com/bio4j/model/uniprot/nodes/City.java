package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.BookCity;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class City <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		City<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.CityType,
		I, RV, RVT, RE, RET
		> {

	public City(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.CityType type) {
		super(vertex, type);
	}

	@Override
	public City<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// bookCity
	// ingoing
	public List<BookCity<I, RV, RVT, RE, RET>> bookCity_in(){
        return inMany(graph().BookCity());
    }
	public List<Book<I, RV, RVT, RE, RET>> bookCity_inNode(){
        return inManyV(graph().BookCity());
    }


}
