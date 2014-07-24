package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferenceArticle <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferenceArticle<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferenceArticleType<S, ST, R, RT, T, TT>,
		T extends Article<T, TT>, TT extends UniprotGraph.ArticleType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
