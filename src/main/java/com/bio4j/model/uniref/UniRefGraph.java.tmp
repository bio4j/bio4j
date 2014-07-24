package com.bio4j.model.uniref;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/22/2014.
 */
public interface UniRefGraph {

	public static interface UniRef50ClusterType<
			N extends UniRef50Cluster<N, NT>,
			NT extends UniRef50ClusterType<N, NT>
			>
			extends Node.Type<N, NT> {
	}
	public static interface UniRef90ClusterType<
			N extends UniRef90Cluster<N, NT>,
			NT extends UniRef90ClusterType<N, NT>
			>
			extends Node.Type<N, NT> {
	}
	public static interface UniRef100ClusterType<
			N extends UniRef100Cluster<N, NT>,
			NT extends UniRef100ClusterType<N, NT>
			>
			extends Node.Type<N, NT> {
	}


}
