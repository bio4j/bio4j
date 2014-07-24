package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef100Member;
import com.bio4j.model.uniprot_uniref.relationships.UniRef100Representant;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniRef100Cluster <
		N extends UniRef100Cluster<N, NT>,
		NT extends UniRefGraph.UniRef100ClusterType<N, NT>
		>
		extends Node<N, NT> {

	public String id();

	// properties
	public static interface id<
			N extends UniRef100Cluster<N, NT>,
			NT extends UniRefGraph.UniRef100ClusterType<N, NT>,
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
	public <T extends UniRef100Representant> T  uniRef100Representant_out();
	public <T extends Protein> T uniRef100Representant_outNode();

	// uniRef90Member
	// outgoing
	public List<? extends UniRef100Member> uniRef100Member_out();
	public List<? extends Protein> uniRef100Member_outNodes();
}