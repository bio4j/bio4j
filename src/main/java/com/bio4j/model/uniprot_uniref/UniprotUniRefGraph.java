package com.bio4j.model.uniprot_uniref;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniprotUniRefGraph {

	public static interface UniRef50RepresentantType<
			S extends UniRef50Cluster<S, ST>, ST extends UniRefGraph.UniRef50ClusterType<S, ST>,
			R extends UniRef50Representant<S, ST, R, RT, T, TT>, RT extends UniRef50RepresentantType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
	public static interface UniRef90RepresentantType<
			S extends UniRef90Cluster<S, ST>, ST extends UniRefGraph.UniRef90ClusterType<S, ST>,
			R extends UniRef90Representant<S, ST, R, RT, T, TT>, RT extends UniRef90RepresentantType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
	public static interface UniRef100RepresentantType<
			S extends UniRef100Cluster<S, ST>, ST extends UniRefGraph.UniRef100ClusterType<S, ST>,
			R extends UniRef100Representant<S, ST, R, RT, T, TT>, RT extends UniRef100RepresentantType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
	public static interface UniRef50MemberType<
			S extends UniRef50Cluster<S, ST>, ST extends UniRefGraph.UniRef50ClusterType<S, ST>,
			R extends UniRef50Member<S, ST, R, RT, T, TT>, RT extends UniRef50MemberType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
	public static interface UniRef90MemberType<
			S extends UniRef90Cluster<S, ST>, ST extends UniRefGraph.UniRef90ClusterType<S, ST>,
			R extends UniRef90Member<S, ST, R, RT, T, TT>, RT extends UniRef90MemberType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
	public static interface UniRef100MemberType<
			S extends UniRef100Cluster<S, ST>, ST extends UniRefGraph.UniRef100ClusterType<S, ST>,
			R extends UniRef100Member<S, ST, R, RT, T, TT>, RT extends UniRef100MemberType<S, ST, R, RT, T, TT>,
			T extends Protein<T, TT>, TT extends UniprotGraph.ProteinType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
}
