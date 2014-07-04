package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Ensembl;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface ProteinEnsembl  <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinEnsembl<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinEnsemblType<S, ST, R, RT, T, TT>,
		T extends Ensembl<T, TT>, TT extends UniprotGraph.EnsemblType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}

