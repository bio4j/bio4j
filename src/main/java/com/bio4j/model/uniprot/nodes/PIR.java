package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinPIR;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class PIR <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		PIR<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.PIRType,
		I, RV, RVT, RE, RET
		> {

	public PIR(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.PIRType type) {
		super(vertex, type);
	}

	@Override
	public PIR<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
    public String entryName() { return get(type().entryName);}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinPIR
	// ingoing
	public List<ProteinPIR<I, RV, RVT, RE, RET>> proteinPIR_in(){
		return inMany(graph().ProteinPIR());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinPIR_inNodes(){
		return inManyV(graph().ProteinPIR());
	}


}
