package com.bio4j.model.uniprot.nodes;

import com.ohnosequences.typedGraphs.Node;

/**
 * Created by ppareja on 6/18/2014.
 */
public class Protein<
		N extends Protein<N, NT>,
		NT extends ProteinType<N, NT>
		>
		extends Node<N, NT>  {
}
