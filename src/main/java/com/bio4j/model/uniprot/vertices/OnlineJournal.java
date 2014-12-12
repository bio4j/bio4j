package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.OnlineArticleOnlineJournal;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class OnlineJournal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		OnlineJournal<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.OnlineJournalType,
		I, RV, RVT, RE, RET
		> {

	public OnlineJournal(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.OnlineJournalType type) {
		super(vertex, type);
	}

	@Override
	public OnlineJournal<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// onlineArticleOnlineJournal
	// ingoing
	public Stream<OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>> onlineArticleOnlineJournal_in(){
		return inMany(graph().OnlineArticleOnlineJournal());
	}
	public Stream<OnlineArticle<I, RV, RVT, RE, RET>> onlineArticleOnlineJournal_inV(){
		return inManyV(graph().OnlineArticleOnlineJournal());
	}


}
