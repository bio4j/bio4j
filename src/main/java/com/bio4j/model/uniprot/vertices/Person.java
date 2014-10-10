package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ReferenceAuthorPerson;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Person <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Person<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.PersonType,
		I, RV, RVT, RE, RET
		> {

	public Person(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.PersonType type) {
		super(vertex, type);
	}

	@Override
	public Person<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referenceAuthorPeron
	// ingoing
	public Stream<ReferenceAuthorPerson<I, RV, RVT, RE, RET>> referenceAuthorPerson_in(){
		return inMany(graph().ReferenceAuthorPerson());
	}
	public Stream<Reference<I, RV, RVT, RE, RET>> referenceAuthorPerson_inV(){
		return inManyV(graph().ReferenceAuthorPerson());
	}


}
