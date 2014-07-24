package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Reference;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferenceThesis <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferenceThesis<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferenceThesisType<S, ST, R, RT, T, TT>,
		T extends Thesis<T, TT>, TT extends UniprotGraph.ThesisType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
