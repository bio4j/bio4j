package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.DatasetType;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface Dataset<
		N extends Dataset<N, NT>,
		NT extends DatasetType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	// properties
	public static interface name<
			N extends Dataset<N, NT>,
			NT extends DatasetType<N, NT>,
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
}
