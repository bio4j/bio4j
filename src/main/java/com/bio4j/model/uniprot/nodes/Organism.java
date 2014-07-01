package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface Organism<
		N extends Organism<N, NT>,
		NT extends OrganismType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	// properties
	public static interface name<
			N extends Organism<N, NT>,
			NT extends UniprotGraph.OrganismType<N, NT>,
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

	// proteinOrganism
	// ingoing
	public List<? extends ProteinOrganism> proteinOrganism_in();
	public List<? extends Protein> proteinOrganism_inNodes();
}
