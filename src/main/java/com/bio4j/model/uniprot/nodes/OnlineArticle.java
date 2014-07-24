package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface OnlineArticle <
		N extends OnlineArticle<N, NT>,
		NT extends UniprotGraph.OnlineArticleType<N, NT>
		>
		extends Node<N, NT> {

	public String title();

	// properties

	public static interface title<
			N extends OnlineArticle<N, NT>,
			NT extends UniprotGraph.OnlineArticleType<N, NT>,
			P extends title<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "title";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

}
