package com.bio4j.model.uniref.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.relationships.UniRef100Representant;
import com.bio4j.model.uniref.UniRefGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef100Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniRefGraph.UniRefVertex<
		UniRef100Cluster<I, RV, RVT, RE, RET>,
		UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
		I, RV, RVT, RE, RET
		> {

	public UniRef100Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType type) {
		super(vertex, type);
	}

	@Override
	public UniRef100Cluster<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String id() {
		return get(type().id);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships
	//uniRef100Representant
	// ingoing
	public UniRef100Representant<I, RV, RVT, RE, RET> uniRef100Representant_in(){
		return inOne(graph().uniprotUniRefGraph().UniRef100Representant());
	}
	public Protein<I, RV, RVT, RE, RET> uniRef100Representant_inNode(){
		return inOneV(graph().uniprotUniRefGraph().UniRef100Representant());
	}

	//uniRef100Member
	// ingoing
	public List<UniRef100Member<I, RV, RVT, RE, RET>> uniRef100Member_in(){
		return inMany(graph().uniprotUniRefGraph().UniRef100Member());
	}
	public List<Protein<I, RV, RVT, RE, RET>> uniRef100Member_inNode(){
		return inManyV(graph().uniprotUniRefGraph().UniRef100Member());
	}




}
