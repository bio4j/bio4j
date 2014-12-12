package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.CommentType;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinComment <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends
		UniProtGraph.UniprotEdge<
				Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
				ProteinComment<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinCommentType,
				CommentType<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType,
				I, RV, RVT, RE, RET
				> {

	// properties
	public String position() {
		return get(type().position);
	}
	public String mass() {    return get(type().mass);	}
	public String evidence() {
		return get(type().evidence);
	}
	public String method() {
		return get(type().method);
	}
	public int begin() {
		return get(type().begin);
	}
	public int end() {
		return get(type().end);
	}
	public String status() {
		return get(type().status);
	}
	public String text() {
		return get(type().text);
	}
	public String temperatureDependence() {
		return get(type().temperatureDependence);
	}
	public String phDependence() {
		return get(type().phDependence);
	}
	public String kineticsXML() {
		return get(type().kineticsXML);
	}
	public String absorptionMax() {
		return get(type().absorptionMax);
	}
	public String absorptionText() {
		return get(type().absorptionText);
	}
	public String redoxPotential() {
		return get(type().redoxPotential);
	}
	public String redoxPotentialEvidence() {
		return get(type().redoxPotentialEvidence);
	}

	public ProteinComment(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinCommentType type) {

		super(edge, type);
	}

	@Override
	public ProteinComment<I, RV, RVT, RE, RET> self() {
		return this;
	}
}