package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ProteinEnsembl;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Ensembl <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Ensembl<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.EnsemblType,
		I, RV, RVT, RE, RET
		> {

	public Ensembl(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.EnsemblType type) {
		super(vertex, type);
	}

	@Override
	public Ensembl<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String proteinSequenceId() {
		return get(type().proteinSequenceId);
	}
	public String moleculeId() {
		return get(type().moleculeId);
	}
	public String geneId() {
		return get(type().geneId);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinEnsembl
	// ingoing
	public Stream<ProteinEnsembl<I, RV, RVT, RE, RET>> proteinEnsembl_in(){
		return inMany(graph().ProteinEnsembl());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinEnsembl_inV(){
		return inManyV(graph().ProteinEnsembl());
	}


}