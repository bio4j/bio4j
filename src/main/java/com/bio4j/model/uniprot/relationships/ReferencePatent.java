package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Patent;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferencePatent <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferencePatent<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferencePatentType<S, ST, R, RT, T, TT>,
		T extends Patent<T, TT>, TT extends UniprotGraph.PatentType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
