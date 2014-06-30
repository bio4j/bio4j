package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinReactomeTerm;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface ReactomeTerm<
		N extends ReactomeTerm<N, NT>,
		NT extends UniprotGraph.ReactomeTermType<N, NT>
		>
		extends Node<N, NT> {

	public String id();
	public String pathwayName();

	// properties
	public static interface id<
			N extends ReactomeTerm<N, NT>,
			NT extends UniprotGraph.ReactomeTermType<N, NT>,
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

	public static interface pathwayName<
			N extends ReactomeTerm<N, NT>,
			NT extends UniprotGraph.ReactomeTermType<N, NT>,
			P extends pathwayName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "pathwayName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinReactome
	// outgoing
	public List<? extends ProteinReactomeTerm> proteinReactome_out();
	public List<? extends Organism> proteinReactome_outNodes();
}
