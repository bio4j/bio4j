package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinUniGene;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface UniGene <
		N extends UniGene<N, NT>,
		NT extends UniprotGraph.UniGeneType<N, NT>
		>
		extends Node<N, NT> {

	public String id();

	// properties

	public static interface id<
			N extends UniGene<N, NT>,
			NT extends UniprotGraph.UniGeneType<N, NT>,
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

	// proteinUniGene
	// ingoing
	public List<? extends ProteinUniGene> proteinUniGene_in();
	public List<? extends Protein> proteinUniGene_inNodes();
}
