package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ArticleJournal;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Journal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		Journal<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.JournalType,
		I, RV, RVT, RE, RET
		> {

	public Journal(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.JournalType type) {
		super(vertex, type);
	}

	@Override
	public Journal<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// articleJournal
	// ingoing
	public Stream<ArticleJournal<I, RV, RVT, RE, RET>> articleJournal_in(){
		return inMany(graph().ArticleJournal());
	}
	public Stream<Article<I, RV, RVT, RE, RET>> articleJournal_inV(){
		return inManyV(graph().ArticleJournal());
	}


}
