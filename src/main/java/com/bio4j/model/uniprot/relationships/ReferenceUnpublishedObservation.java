package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Reference;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferenceUnpublishedObservation <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferenceUnpublishedObservation<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferenceUnpublishedObservationType<S, ST, R, RT, T, TT>,
		T extends UnpublishedObservation<T, TT>, TT extends UniprotGraph.UnpublishedObservationType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
