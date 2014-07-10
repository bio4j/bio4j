package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.UniprotGraph.ReactomeTermType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface ProteinReactomeTerm <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinReactomeTerm<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinReactomeTermType<S, ST, R, RT, T, TT>,
		T extends ReactomeTerm<T, TT>, TT extends ReactomeTermType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
