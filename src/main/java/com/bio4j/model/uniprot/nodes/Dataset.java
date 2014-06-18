package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.DatasetType;
import com.ohnosequences.typedGraphs.Node;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface Dataset<
		N extends Dataset<N, NT>,
		NT extends DatasetType<N, NT>
		>
		extends Node<N, NT> {
}
