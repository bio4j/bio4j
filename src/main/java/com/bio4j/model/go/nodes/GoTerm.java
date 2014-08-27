package com.bio4j.model.go.nodes;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.relationships.*;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

public final class GoTerm<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends GoGraph.GoVertex<
		GoTerm<I, RV, RVT, RE, RET>,
		GoGraph<I, RV, RVT, RE, RET>.GoTermType,
		I, RV, RVT, RE, RET
		> {

	public GoTerm(RV vertex, GoGraph<I, RV, RVT, RE, RET>.GoTermType type) {
		super(vertex, type);
	}

	@Override
	public GoTerm<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	public String name() {
		return get(type().name);
	}

	public String definition() {
		return get(type().definition);
	}

	public String comment() {
		return get(type().comment);
	}

    public String obsolete() { return get(type().obsolete);}

    public String synonym() { return get(type().synonym);}

	// rels

	//----part of---------
	public List<PartOf<I, RV, RVT, RE, RET>> partOf_in() {
		return inMany(graph().PartOf());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> partOf_inV() {
		return inManyV(graph().PartOf());
	}

	public List<PartOf<I, RV, RVT, RE, RET>> partOf_out() {
		return outMany(graph().PartOf());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> partOf_outV() {
		return outManyV(graph().PartOf());
	}

	//----has part of-------
	public List<HasPartOf<I, RV, RVT, RE, RET>> hasPartOf_in(){
		return inMany(graph().HasPartOf());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> hasPartOf_inV(){
		return inManyV(graph().HasPartOf());
	}
	public List<HasPartOf<I, RV, RVT, RE, RET>> hasPartOf_out(){
		return outMany(graph().HasPartOf());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> hasPartOf_outV(){
		return outManyV(graph().HasPartOf());
	}

	//----regulates-------
	public List<Regulates<I, RV, RVT, RE, RET>> regulates_in(){
		return inMany(graph().Regulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> regulates_inV(){
		return inManyV(graph().Regulates());
	}
	public List<Regulates<I, RV, RVT, RE, RET>> regulates_out(){
		return outMany(graph().Regulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> regulates_outV(){
		return outManyV(graph().Regulates());
	}

	//----positively regulates-------
	public List<PositivelyRegulates<I, RV, RVT, RE, RET>> positivelyRegulates_in(){
		return inMany(graph().PositivelyRegulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> positivelyRegulates_inV(){
		return inManyV(graph().PositivelyRegulates());
	}
	public List<PositivelyRegulates<I, RV, RVT, RE, RET>> positivelyRegulates_out(){
		return outMany(graph().PositivelyRegulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> positivelyRegulates_outV(){
		return outManyV(graph().PositivelyRegulates());
	}

	//----negatively regulates-------
	public List<NegativelyRegulates<I, RV, RVT, RE, RET>> negativelyRegulates_in(){
		return inMany(graph().NegativelyRegulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> negativelyRegulates_inV(){
		return inManyV(graph().NegativelyRegulates());
	}
	public List<NegativelyRegulates<I, RV, RVT, RE, RET>> negaitivelyRegulates_out(){
		return outMany(graph().NegativelyRegulates());
	}

	public List<GoTerm<I, RV, RVT, RE, RET>> negativelyRegulates_outV(){
		return outManyV(graph().NegativelyRegulates());
	}

	//----is a-------
	public List<IsA<I, RV, RVT, RE, RET>> isA_in(){
		return inMany(graph().IsA());
	}
	public List<GoTerm<I, RV, RVT, RE, RET>> isA_inV(){
		return inManyV(graph().IsA());
	}

	public List<IsA<I, RV, RVT, RE, RET>> isA_out(){
		return outMany(graph().IsA());
	}
	public List<GoTerm<I, RV, RVT, RE, RET>> isA_outV(){
		return outManyV(graph().IsA());
	}

	//-----subontology-----
	public SubOntology<I, RV, RVT, RE, RET> subontoloty_out(){
		return outOne(graph().SubOntology());
	}
	//-----subontology-----
	public SubOntologies<I, RV, RVT, RE, RET> subontoloty_outV(){
		return outOneV(graph().SubOntology());
	}

	//-----goAnnotation----
	public List<GoAnnotation<I, RV, RVT, RE, RET>> goAnnotation_in() {   

		return inMany( graph().uniprotGoGraph().GoAnnotation() );
	}
	public List<Protein<I, RV, RVT, RE, RET>> goAnnotation_inV(){   return inManyV(graph().uniprotGoGraph().GoAnnotation());}



}

