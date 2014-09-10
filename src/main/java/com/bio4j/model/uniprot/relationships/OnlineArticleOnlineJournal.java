package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.OnlineJournal;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class OnlineArticleOnlineJournal<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				OnlineArticle<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
				OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleOnlineJournalType,
				OnlineJournal<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.OnlineJournalType,
				I, RV, RVT, RE, RET
				> {

	public OnlineArticleOnlineJournal(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleOnlineJournalType type) {

		super(edge, type);
	}

	// properties
	public String locator() {
		return get(type().locator);
	}

	@Override
	public OnlineArticleOnlineJournal<I, RV, RVT, RE, RET> self() {
		return this;
	}
}