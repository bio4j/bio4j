package com.bio4j.model.ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy.relationships.NCBITaxonParent;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/11/2014.
 */
public interface NCBITaxonomyGraph {

	public static interface NCBITaxonType<
			N extends NCBITaxon<N, NT>,
			NT extends NCBITaxonType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface NCBITaxonParentType<
			S extends NCBITaxon<S, ST>, ST extends NCBITaxonType<S, ST>,
			R extends NCBITaxonParent<S, ST, R, RT, T, TT>, RT extends NCBITaxonParentType<S, ST, R, RT, T, TT>,
			T extends NCBITaxon<T, TT>, TT extends NCBITaxonType<T, TT>
			>
			extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
	}
}
