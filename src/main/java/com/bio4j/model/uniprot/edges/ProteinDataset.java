package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Dataset;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinDataset <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniProtEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinDataset<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinDatasetType,
				Dataset<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.DatasetType,
				I, RV, RVT, RE, RET
				> {

	public ProteinDataset(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinDatasetType type) {

		super(edge, type);
	}

	@Override
	public ProteinDataset<I, RV, RVT, RE, RET> self() {
		return this;
	}
}