package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.OnlineArticleOnlineJournal;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class OnlineJournal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		OnlineJournal<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.OnlineJournalType,
		I, RV, RVT, RE, RET
		> {

	public OnlineJournal(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.OnlineJournalType type) {
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
	public List<OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>> onlineArticleOnlineJournal_in(){
		return inMany(graph().OnlineArticleOnlineJournal());
	}
	public List<OnlineArticle<I, RV, RVT, RE, RET>> onlineArticleOnlineJournal_inNodes(){
		return inManyV(graph().OnlineArticleOnlineJournal());
	}


}
