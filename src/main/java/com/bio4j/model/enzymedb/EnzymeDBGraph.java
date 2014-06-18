package com.bio4j.model.enzymedb;

import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Node;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/17/2014.
 */
public interface EnzymeDBGraph {

	public static interface EnzymeType<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface EnzymaticActivityType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends EnzymaticActivity<S, ST, R, RT, T, TT>, RT extends EnzymaticActivityType<S, ST, R, RT, T, TT>,
			T extends Enzyme<T, TT>, TT extends EnzymeType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}
}
