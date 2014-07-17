package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinFeature;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/17/2014.
 */
public interface FeatureType <
		N extends FeatureType<N, NT>,
		NT extends UniprotGraph.FeatureTypeType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	// properties

	public static interface name<
			N extends FeatureType<N, NT>,
			NT extends UniprotGraph.FeatureTypeType<N, NT>,
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

	// proteinFeature
	// ingoing
	public List<? extends ProteinFeature> proteinFeature_in();
	public List<? extends Protein> proteinFeature_inNodes();


}
