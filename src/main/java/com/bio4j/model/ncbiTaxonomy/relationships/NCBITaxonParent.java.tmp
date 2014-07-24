package com.bio4j.model.ncbiTaxonomy.relationships;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/11/2014.
 */
public interface NCBITaxonParent <
		S extends NCBITaxon<S, ST>, ST extends NCBITaxonomyGraph.NCBITaxonType<S, ST>,
		R extends NCBITaxonParent<S, ST, R, RT, T, TT>, RT extends NCBITaxonomyGraph.NCBITaxonParentType<S, ST, R, RT, T, TT>,
		T extends NCBITaxon<T, TT>, TT extends NCBITaxonomyGraph.NCBITaxonType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
