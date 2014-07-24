package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ProteinReference <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinReference<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinReferenceType<S, ST, R, RT, T, TT>,
		T extends Reference<T, TT>, TT extends UniprotGraph.ReferenceType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
