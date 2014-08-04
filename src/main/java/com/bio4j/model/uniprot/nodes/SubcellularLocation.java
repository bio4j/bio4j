package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinSubcellularLocation;
import com.bio4j.model.uniprot.relationships.SubcellularLocationParent;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class SubcellularLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		SubcellularLocation<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
		I, RV, RVT, RE, RET
		> {

	public SubcellularLocation(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.SubcellularLocationType type) {
		super(vertex, type);
	}

	@Override
	public SubcellularLocation<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinSubcellularLocation
	// ingoing
	public List<ProteinSubcellularLocation<I, RV, RVT, RE, RET>> proteinSubcellularLocation_in(){
		return inMany(graph().ProteinSubcellularLocation());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinSubcellularLocation_inNodes(){
		return inManyV(graph().ProteinSubcellularLocation());
	}

	// subcellularLocationParent
	// ingoing
	public List<SubcellularLocationParent<I, RV, RVT, RE, RET>> subcellularLocationParent_in(){
		return inMany(graph().SubcellularLocationParent());
	}
	public List<SubcellularLocation<I, RV, RVT, RE, RET>> subcellularLocationParent_inNodes(){
		return inManyV(graph().SubcellularLocationParent());
	}

	// subcellularLocationParent
	// outgoing
	public SubcellularLocationParent<I, RV, RVT, RE, RET> subcellularLocationParent_out(){
		return outOne(graph().SubcellularLocationParent());
	}
	public SubcellularLocation<I, RV, RVT, RE, RET> subcellularLocationParent_outNode(){
		return outOneV(graph().SubcellularLocationParent());
	}


}
