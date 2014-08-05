package com.bio4j.model.uniprot_uniref.relationships;


import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class UniRef50Representant<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotUniRefGraph.UniprotUniRefEdge<
				// src
				Protein<I, RV, RVT, RE, RET>,
				UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				UniprotGraph<I, RV, RVT, RE, RET>,
				// edge
				UniRef50Representant<I, RV, RVT, RE, RET>,
				UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef50RepresentantType,
				//tgt
				UniRef50Cluster<I, RV, RVT, RE, RET>,
				UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
				UniRefGraph<I, RV, RVT, RE, RET>,
				// raw stuff
				I, RV, RVT, RE, RET
				> {

	public UniRef50Representant(RE edge, UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef50RepresentantType type) {

		super(edge, type);
	}

	@Override
	public UniRef50Representant<I, RV, RVT, RE, RET> self() {
		return this;
	}
}