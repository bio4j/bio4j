package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ReferenceOnlineArticle;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class OnlineArticle <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		OnlineArticle<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
		I, RV, RVT, RE, RET
		>  {

	public OnlineArticle(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType type) {
		super(vertex, type);
	}

	@Override
	public OnlineArticle<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referenceOnlineArticle
	// ingoing
	public ReferenceOnlineArticle<I, RV, RVT, RE, RET> referenceOnlineArticle_in(){
		return inOne(graph().ReferenceOnlineArticle());
	}
	public Reference<I, RV, RVT, RE, RET> referenceOnlineArticle_inV(){
		return inOneV(graph().ReferenceOnlineArticle());
	}
}
