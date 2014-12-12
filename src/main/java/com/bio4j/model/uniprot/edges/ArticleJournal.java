package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Article;
import com.bio4j.model.uniprot.vertices.Journal;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class ArticleJournal<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Article<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ArticleType,
				ArticleJournal<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ArticleJournalType,
				Journal<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.JournalType,
				I, RV, RVT, RE, RET
				> {

	public ArticleJournal(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ArticleJournalType type) {

		super(edge, type);
	}

	// properties
	public String volume() {
		return get(type().volume);
	}
	public String first() {    return get(type().first);	}
	public String last() {
		return get(type().last);
	}

	@Override
	public ArticleJournal<I, RV, RVT, RE, RET> self() {
		return this;
	}
}