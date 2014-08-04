package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef90Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniRefGraph.UniRefVertex<
		UniRef90Cluster<I, RV, RVT, RE, RET>,
		UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
		I, RV, RVT, RE, RET
		> {

	public UniRef90Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType type) {
		super(vertex, type);
	}

	@Override
	public UniRef90Cluster<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships




}
