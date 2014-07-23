package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Pubmed;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface ArticlePubmed <
		S extends Article<S, ST>, ST extends UniprotGraph.ArticleType<S, ST>,
		R extends ArticlePubmed<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ArticlePubmedType<S, ST, R, RT, T, TT>,
		T extends Pubmed<T, TT>, TT extends UniprotGraph.PubmedType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}