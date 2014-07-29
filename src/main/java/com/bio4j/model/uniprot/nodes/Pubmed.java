package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticlePubmed;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Pubmed <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Pubmed<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.PubmedType,
		I, RV, RVT, RE, RET
		> {

	public Pubmed(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.PubmedType type) {
		super(vertex, type);
	}

	@Override
	public Pubmed<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// articlePubmed
	// ingoing
	public ArticlePubmed<I, RV, RVT, RE, RET> articlePubmed_in(){
		return inOne(graph().ArticlePubmed());
	}
	public Article<I, RV, RVT, RE, RET> articlePubmed_inNodes(){
		return inOneV(graph().ArticlePubmed());
	}


}