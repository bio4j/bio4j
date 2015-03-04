package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.edges.UniRef50Member;
import com.bio4j.model.uniprot_uniref.edges.UniRef50Representant;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef50Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniRefGraph.UniRefVertex<
		UniRef50Cluster<I, RV, RVT, RE, RET>,
		UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
		I, RV, RVT, RE, RET
		> {

	public UniRef50Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType type) {
		super(vertex, type);
	}

	@Override
	public UniRef50Cluster<I, RV, RVT, RE, RET> self() {
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
	public String representantAccession() { return get(type().representantAccession);}
	public String[] members() { return get(type().members);}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships
	//uniRef50Representant
	// ingoing
	public UniRef50Representant<I, RV, RVT, RE, RET> uniRef50Representant_in(){
		return inOne(graph().uniProtUniRefGraph().UniRef50Representant());
	}
	public Protein<I, RV, RVT, RE, RET> uniRef50Representant_inV(){
		return inOneV(graph().uniProtUniRefGraph().UniRef50Representant());
	}

	//uniRef50Member
	// ingoing
	public Stream<UniRef50Member<I, RV, RVT, RE, RET>> uniRef50Member_in(){
		return inMany(graph().uniProtUniRefGraph().UniRef50Member());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> uniRef50Member_inV(){
		return inManyV(graph().uniProtUniRefGraph().UniRef50Member());
	}



}
