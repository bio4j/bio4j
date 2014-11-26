package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class ArticlePubmed<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Article<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticleType,
				ArticlePubmed<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ArticlePubmedType,
				Pubmed<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.PubmedType,
				I, RV, RVT, RE, RET
				> {

	public ArticlePubmed(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ArticlePubmedType type) {

		super(edge, type);
	}

	@Override
	public ArticlePubmed<I, RV, RVT, RE, RET> self() {
		return this;
	}
}