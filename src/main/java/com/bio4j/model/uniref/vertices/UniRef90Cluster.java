package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.edges.UniRef90Member;
import com.bio4j.model.uniprot_uniref.edges.UniRef90Representant;
import com.bio4j.model.uniref.UniRefGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef90Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniRefGraph.UniRefVertex<
		UniRef90Cluster<I, RV, RVT, RE, RET>,
		UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
		I, RV, RVT, RE, RET
		> {

	public UniRef90Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType type) {
		super(vertex, type);
	}

	@Override
	public UniRef90Cluster<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}
	public String name() {
		return get(type().name);
	}
	public String updatedDate() {
		return get(type().updatedDate);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships
	//uniRef90Representant
	// ingoing
	public UniRef90Representant<I, RV, RVT, RE, RET> uniRef90Representant_in(){
		return inOne(graph().uniprotUniRefGraph().UniRef90Representant());
	}
	public Protein<I, RV, RVT, RE, RET> uniRef90Representant_inNode(){
		return inOneV(graph().uniprotUniRefGraph().UniRef90Representant());
	}

	//uniRef90Member
	// ingoing
	public List<UniRef90Member<I, RV, RVT, RE, RET>> uniRef90Member_in(){
		return inMany(graph().uniprotUniRefGraph().UniRef90Member());
	}
	public List<Protein<I, RV, RVT, RE, RET>> uniRef90Member_inNode(){
		return inManyV(graph().uniprotUniRefGraph().UniRef90Member());
	}




}
