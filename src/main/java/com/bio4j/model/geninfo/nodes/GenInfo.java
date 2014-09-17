package com.bio4j.model.geninfo.nodes;

import com.bio4j.model.geninfo.GenInfoGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GenInfo<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends GenInfoGraph.GenInfoVertex<
		GenInfo<I, RV, RVT, RE, RET>,
		GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType,
		I, RV, RVT, RE, RET
		> {

	public GenInfo(RV vertex, GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType type) {
		super(vertex, type);
	}

	@Override
	public GenInfo<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	// rels



}

