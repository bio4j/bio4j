package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface Keyword <
		N extends Keyword<N, NT>,
		NT extends UniprotGraph.KeywordType<N, NT>
		>
		extends Node<N, NT> {

	public String name();
	public String id();

	// properties
	public static interface name<
			N extends Keyword<N, NT>,
			NT extends UniprotGraph.KeywordType<N, NT>,
			P extends name<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "name";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface id<
			N extends Keyword<N, NT>,
			NT extends UniprotGraph.KeywordType<N, NT>,
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

	// proteinKeyword
	// outgoing
	public List<? extends ProteinKeyword> proteinKeyword_out();
	public List<? extends Organism> proteinKeyword_outNodes();


}
