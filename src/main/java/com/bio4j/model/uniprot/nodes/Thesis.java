package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticlePubmed;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface Thesis <
		N extends Thesis<N, NT>,
		NT extends UniprotGraph.ThesisType<N, NT>
		>
		extends Node<N, NT> {

	public String title();
	public String date();

	// properties

	public static interface title<
			N extends Thesis<N, NT>,
			NT extends UniprotGraph.ThesisType<N, NT>,
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

	public static interface date<
			N extends Thesis<N, NT>,
			NT extends UniprotGraph.ThesisType<N, NT>,
			P extends date<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "date";
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
