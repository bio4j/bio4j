package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.SequenceCaution;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinSequenceCaution <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinSequenceCaution<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinSequenceCautionType,
				SequenceCaution<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.SequenceCautionType,
				I, RV, RVT, RE, RET
				> {

	// properties
	public String id() {    return get(type().id);	}
	public String evidence() {
		return get(type().evidence);
	}
	public String status() {
		return get(type().status);
	}
	public String text() {
		return get(type().text);
	}
	public String resource() {
		return get(type().resource);
	}
	public String version() {
		return get(type().version);
	}
	public String position() {  return get(type().position);}

	public ProteinSequenceCaution(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinSequenceCautionType type) {

		super(edge, type);
	}

	@Override
	public ProteinSequenceCaution<I, RV, RVT, RE, RET> self() {
		return this;
	}
}