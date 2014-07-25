package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticlePubmed;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.Date;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface Article <
		N extends Article<N, NT>,
		NT extends UniprotGraph.ArticleType<N, NT>
		>
		extends Node<N, NT> {

	public String title();
	public String date();
	public String doiId();

	// properties

		public static interface title<
			N extends Article<N, NT>,
			NT extends UniprotGraph.ArticleType<N, NT>,
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

	public static interface doiId<
			N extends Article<N, NT>,
			NT extends UniprotGraph.ArticleType<N, NT>,
			P extends doiId<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "doiId";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}


	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// articlePubmed
	// ingoing
	public <T extends ArticlePubmed> T articlePubmed_out();
	public <T extends Pubmed> T articlePubmed_outNode();
}
