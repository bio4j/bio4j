package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.EMBL;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/3/2014.
 */
public interface ProteinEMBL  <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinEMBL<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinEMBLType<S, ST, R, RT, T, TT>,
		T extends EMBL<T, TT>, TT extends UniprotGraph.EMBLType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
