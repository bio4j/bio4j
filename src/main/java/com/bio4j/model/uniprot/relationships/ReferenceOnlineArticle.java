package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferenceOnlineArticle <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferenceOnlineArticle<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferenceOnlineArticleType<S, ST, R, RT, T, TT>,
		T extends OnlineArticle<T, TT>, TT extends UniprotGraph.OnlineArticleType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
