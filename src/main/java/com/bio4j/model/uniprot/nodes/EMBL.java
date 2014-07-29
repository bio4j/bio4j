package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.model.uniprot.relationships.ProteinEMBL;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class EMBL <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		EMBL<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.EMBLType,
		I, RV, RVT, RE, RET
		> {

	public EMBL(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.EMBLType type) {
		super(vertex, type);
	}

	@Override
	public EMBL<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String proteinSequenceId() {
		return get(type().proteinSequenceId);
	}
	public String moleculeType() {
		return get(type().moleculeType);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinEMBL
	// ingoing
	public List<ProteinEMBL<I, RV, RVT, RE, RET>> proteinEMBL_in(){
		return inMany(graph().ProteinEMBL());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinEMBL_inNodes(){
		return inManyV(graph().ProteinEMBL());
	}


}
