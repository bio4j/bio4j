package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.RefSeq;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/14/2014.
 */
public interface ProteinRefSeq <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinRefSeq<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinRefSeqType<S, ST, R, RT, T, TT>,
		T extends RefSeq<T, TT>, TT extends UniprotGraph.RefSeqType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
