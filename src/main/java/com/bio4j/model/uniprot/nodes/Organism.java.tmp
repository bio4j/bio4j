package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.bio4j.model.uniprot.relationships.OrganismTaxon;
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

	public String scientificName();
	public String commonName();
	public String synonymName();

	// properties
	public static interface scientificName<
			N extends Organism<N, NT>,
			NT extends UniprotGraph.OrganismType<N, NT>,
			P extends scientificName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "scientificName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface commonName<
			N extends Organism<N, NT>,
			NT extends UniprotGraph.OrganismType<N, NT>,
			P extends commonName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "commonName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface synonymName<
			N extends Organism<N, NT>,
			NT extends UniprotGraph.OrganismType<N, NT>,
			P extends synonymName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "synonymName";
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

	// organismTaxon
	// outgoing
	public <T extends OrganismTaxon> T organismTaxon_out();
	public <T extends Taxon> T organismTaxon_outNode();
}
