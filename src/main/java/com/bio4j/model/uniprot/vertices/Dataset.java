package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinDataset;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Dataset <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Dataset<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.DatasetType,
		I, RV, RVT, RE, RET
		> {

	public Dataset(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.DatasetType type) {
		super(vertex, type);
	}

	@Override
	public Dataset<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinDataset
	// ingoing
	public List<ProteinDataset<I, RV, RVT, RE, RET>> proteinDataset_in(){
		return inMany(graph().ProteinDataset());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinDataset_inV(){
		return inManyV(graph().ProteinDataset());
	}


}
