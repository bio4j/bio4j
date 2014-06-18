package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.ohnosequences.typedGraphs.Node;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface Organism<
		N extends Organism<N, NT>,
		NT extends OrganismType<N, NT>
		>
		extends Node<N, NT> {
}
