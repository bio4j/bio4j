package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinPfam;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/1/2014.
 */
public interface Pfam <
		N extends Pfam<N, NT>,
		NT extends UniprotGraph.PfamType<N, NT>
		>
		extends Node<N, NT> {

	public String name();
	public String id();

	// properties
	public static interface name<
			N extends Pfam<N, NT>,
			NT extends UniprotGraph.PfamType<N, NT>,
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
			N extends Pfam<N, NT>,
			NT extends UniprotGraph.PfamType<N, NT>,
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

	// proteinPfam
	// ingoing
	public List<? extends ProteinPfam> proteinPfam_in();
	public List<? extends Protein> proteinPfam_inNodes();
}
