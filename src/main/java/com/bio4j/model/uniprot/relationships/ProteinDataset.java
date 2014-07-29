package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public class ProteinDataset <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniprotGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinDataset<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinDatasetType,
				Dataset<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.DatasetType,
				I, RV, RVT, RE, RET
				> {

	public ProteinDataset(RE edge, UniprotGraph<I, RV, RVT, RE, RET>.ProteinDatasetType type) {

		super(edge, type);
	}

	@Override
	public ProteinDataset<I, RV, RVT, RE, RET> self() {
		return this;
	}
}