package com.bio4j.model.uniprot_uniref.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.UniprotUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniRef100Member <
		S extends UniRef100Cluster<S, ST>, ST extends UniRefGraph.UniRef100ClusterType<S, ST>,
		R extends UniRef100Member<S, ST, R, RT, T, TT>, RT extends UniprotUniRefGraph.UniRef100MemberType<S, ST, R, RT, T, TT>,
		T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
