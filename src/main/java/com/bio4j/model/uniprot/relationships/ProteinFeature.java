package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.FeatureType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/17/2014.
 */
public interface ProteinFeature <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinFeatureType<S, ST, R, RT, T, TT>,
		T extends FeatureType<T, TT>, TT extends UniprotGraph.FeatureTypeType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
