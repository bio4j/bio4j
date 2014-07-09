package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Taxon;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/9/2014.
 */
public interface TaxonParent <
		S extends Taxon<S, ST>, ST extends UniprotGraph.TaxonType<S, ST>,
		R extends TaxonParent<S, ST, R, RT, T, TT>, RT extends UniprotGraph.TaxonParentType<S, ST, R, RT, T, TT>,
		T extends Taxon<T, TT>, TT extends UniprotGraph.TaxonType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}