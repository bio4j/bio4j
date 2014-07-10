package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.TaxonParent;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/9/2014.
 */
public interface Taxon <
		N extends Taxon<N, NT>,
		NT extends UniprotGraph.TaxonType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	public static interface name<
			N extends Taxon<N, NT>,
			NT extends UniprotGraph.TaxonType<N, NT>,
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

	// taxonParent
	// ingoing
	public <T extends TaxonParent> T taxonParent_in();
	public <T extends Taxon> T taxonParent_inNode();

	// taxonParent
	// outgoing
	public <T extends TaxonParent> T taxonParent_out();
	public <T extends Taxon> T taxonParent_outNode();
}
