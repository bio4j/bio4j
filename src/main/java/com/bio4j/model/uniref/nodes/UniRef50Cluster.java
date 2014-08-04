package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef50Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniRefGraph.UniprotVertex<
		UniRef50Cluster<I, RV, RVT, RE, RET>,
		UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
		I, RV, RVT, RE, RET
		> {

	public UniRef50Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType type) {
		super(vertex, type);
	}

	@Override
	public UniRef50Cluster<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships




}
