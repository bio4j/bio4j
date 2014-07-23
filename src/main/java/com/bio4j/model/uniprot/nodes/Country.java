package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface Country <
		N extends Country<N, NT>,
		NT extends UniprotGraph.CountryType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	// properties

	public static interface name<
			N extends Country<N, NT>,
			NT extends UniprotGraph.CountryType<N, NT>,
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

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinComment
	// ingoing
//	public List<? extends ProteinComment> proteinComment_in();
//	public List<? extends Protein> proteinComment_inNodes();


}
