package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface Patent<
		N extends Patent<N, NT>,
		NT extends UniprotGraph.PatentType<N, NT>
		>
		extends Node<N, NT> {

	public String number();

	public String title();

	public String date();


	// properties

	public static interface number<
			N extends Patent<N, NT>,
			NT extends UniprotGraph.PatentType<N, NT>,
			P extends number<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "number";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface title<
			N extends Patent<N, NT>,
			NT extends UniprotGraph.PatentType<N, NT>,
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
			N extends Patent<N, NT>,
			NT extends UniprotGraph.PatentType<N, NT>,
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

// proteinComment
// ingoing
//	public List<? extends ProteinComment> proteinComment_in();
//	public List<? extends Protein> proteinComment_inNodes();


}
