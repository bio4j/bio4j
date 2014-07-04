package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinPIR;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface PIR <
		N extends PIR<N, NT>,
		NT extends UniprotGraph.PIRType<N, NT>
		>
		extends Node<N, NT> {

	public String entryName();
	public String id();

	// properties
	public static interface entryName<
			N extends PIR<N, NT>,
			NT extends UniprotGraph.PIRType<N, NT>,
			P extends entryName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "entryName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface id<
			N extends PIR<N, NT>,
			NT extends UniprotGraph.PIRType<N, NT>,
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

	// proteinPIR
	// ingoing
	public List<? extends ProteinPIR> proteinPIR_in();
	public List<? extends Protein> proteinPIR_inNodes();
}
