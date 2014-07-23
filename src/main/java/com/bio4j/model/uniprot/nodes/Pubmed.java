package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticlePubmed;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface Pubmed <
		N extends Pubmed<N, NT>,
		NT extends UniprotGraph.PubmedType<N, NT>
		>
		extends Node<N, NT> {

	public String id();

	// properties


	public static interface id<
			N extends Pubmed<N, NT>,
			NT extends UniprotGraph.PubmedType<N, NT>,
			P extends id<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "id";
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
	public <T extends ArticlePubmed> T articlePubmed_in();
	public <T extends Article> T articlePubmed_inNode();
}
