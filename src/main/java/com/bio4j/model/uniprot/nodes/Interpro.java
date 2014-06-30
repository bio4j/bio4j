package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface Interpro<
		N extends Interpro<N, NT>,
		NT extends UniprotGraph.InterproType<N, NT>
		>
		extends Node<N, NT> {

	public String name();
	public String id();

	// properties
	public static interface name<
			N extends Interpro<N, NT>,
			NT extends UniprotGraph.InterproType<N, NT>,
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
			N extends Interpro<N, NT>,
			NT extends UniprotGraph.InterproType<N, NT>,
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

	// proteinInterpro
	// outgoing
	public List<? extends ProteinInterpro> proteinInterpro_out();
	public List<? extends Organism> proteinInterpro_outNodes();
}
