package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ArticlePubmed;
import com.bio4j.model.uniprot.edges.ReferenceArticle;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Article <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Article<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ArticleType,
		I, RV, RVT, RE, RET
		>  {

	public Article(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ArticleType type) {
		super(vertex, type);
	}

	@Override
	public Article<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	public String doId() {
		return get(type().doId);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// articlePubmed
	// outgoing
	public ArticlePubmed<I, RV, RVT, RE, RET>  articlePubmed_out(){
		return outOne(graph().ArticlePubmed());
	}
	public Pubmed<I, RV, RVT, RE, RET> articlePubmed_outV(){
		return outOneV(graph().ArticlePubmed());
	}
	// referenceArticle
	// ingoing
	public ReferenceArticle<I, RV, RVT, RE, RET> referenceArticle_in(){
		return inOne(graph().ReferenceArticle());
	}
	public Reference<I, RV, RVT, RE, RET> referenceArticle_inV(){
		return inOneV(graph().ReferenceArticle());
	}
}
