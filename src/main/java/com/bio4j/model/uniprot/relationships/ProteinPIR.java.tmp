package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.PIR;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface ProteinPIR<
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinPIR<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinPIRType<S, ST, R, RT, T, TT>,
		T extends PIR<T, TT>, TT extends UniprotGraph.PIRType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}

