package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.PfamType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinPfamType;
import com.bio4j.model.uniprot.nodes.Pfam;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/1/2014.
 */
public interface ProteinPfam <
		S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
		R extends ProteinPfam<S, ST, R, RT, T, TT>, RT extends ProteinPfamType<S, ST, R, RT, T, TT>,
		T extends Pfam<T, TT>, TT extends PfamType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
