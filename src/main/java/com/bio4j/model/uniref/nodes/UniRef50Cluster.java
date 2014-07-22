package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniprot_uniref.relationships.UniRef50Member;
import com.bio4j.model.uniprot_uniref.relationships.UniRef50Representant;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;


/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniRef50Cluster <
		N extends UniRef50Cluster<N, NT>,
		NT extends UniRefGraph.UniRef50ClusterType<N, NT>
		>
		extends Node<N, NT> {

	public String id();

	// properties
	public static interface id<
			N extends UniRef50Cluster<N, NT>,
			NT extends UniRefGraph.UniRef50ClusterType<N, NT>,
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

	// uniRef50Representant
	// outgoing
	public <T extends UniRef50Representant> T  uniRef50Representant_out();
	public <T extends Protein> T uniRef50Representant_outNodes();

	// uniRef50Member
	// outgoing
	public List<? extends UniRef50Member> uniRef50Member_out();
	public List<? extends Protein> uniRef50Member_outNodes();
}
