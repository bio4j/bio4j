package com.bio4j.model.ncbiTaxonomy.nodes;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph.NCBITaxonType;
import com.bio4j.model.ncbiTaxonomy.relationships.NCBITaxonParent;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/11/2014.
 */
public interface NCBITaxon <
		N extends NCBITaxon<N, NT>,
		NT extends NCBITaxonType<N, NT>
		>
		extends Node<N, NT> {

	public String id();
	public String name();
	public String comment(); // WARNING changed comments to comment
	public String scientificName();
	public String taxonomicRank(); // WARNING this was rank before


	// properties
	public static interface id<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>,
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
	public static interface name<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>,
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
	public static interface comment<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>,
			P extends comment<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "comment";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}
	public static interface scientificName<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>,
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
	public static interface taxonomicRank<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>,
			P extends taxonomicRank<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "taxonomicRank";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// nCBITaxonParent
	// incoming
	public List<? extends NCBITaxonParent> nCBITaxonParent_in();
	public List<? extends NCBITaxon> nCBITaxonParent_inNodes();

	// nCBITaxonParent
	// outgoing
	public <T extends NCBITaxonParent> T nCBITaxonParent_out();
	public <T extends NCBITaxon> T nCBITaxonParent_outNodes();


}
