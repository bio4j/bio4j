package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.UniprotGraph.ProteinInterproType;
import com.bio4j.model.uniprot.nodes.Interpro;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface ProteinInterpro <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinInterpro<S, ST, R, RT, T, TT>, RT extends ProteinInterproType<S, ST, R, RT, T, TT>,
		T extends Interpro<T, TT>, TT extends UniprotGraph.InterproType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
