package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef90Member;
import com.bio4j.model.uniprot_uniref.relationships.UniRef90Representant;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniRef90Cluster <
		N extends UniRef90Cluster<N, NT>,
		NT extends UniRefGraph.UniRef90ClusterType<N, NT>
		>
		extends Node<N, NT> {

	public String id();

	// properties
	public static interface id<
			N extends UniRef90Cluster<N, NT>,
			NT extends UniRefGraph.UniRef90ClusterType<N, NT>,
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

	// uniRef90Representant
	// outgoing
	public <T extends UniRef90Representant> T  uniRef90Representant_out();
	public <T extends Protein> T uniRef90Representant_outNodes();

	// uniRef90Member
	// outgoing
	public List<? extends UniRef90Member> uniRef90Member_out();
	public List<? extends Protein> uniRef90Member_outNodes();
}

