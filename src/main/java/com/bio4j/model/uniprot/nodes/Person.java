package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticleJournal;
import com.bio4j.model.uniprot.relationships.ReferenceAuthor;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

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

	// referenceAuthor
	// ingoing
	public List<ReferenceAuthor<I, RV, RVT, RE, RET>> referenceAuthor_in(){
		return inMany(graph().ReferenceAuthor());
	}
	public List<Reference<I, RV, RVT, RE, RET>> referenceAuthor_inNodes(){
		return inManyV(graph().ReferenceAuthor());
	}


}
