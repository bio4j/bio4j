package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Kegg;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface ProteinKegg <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinKegg<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinKeggType<S, ST, R, RT, T, TT>,
		T extends Kegg<T, TT>, TT extends UniprotGraph.KeggType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
