package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.UniGene;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface ProteinUniGene <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinUniGene<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinUniGeneType<S, ST, R, RT, T, TT>,
		T extends UniGene<T, TT>, TT extends UniprotGraph.UniGeneType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}

