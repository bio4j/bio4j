package com.bio4j.model.enzymedb;

import com.ohnosequences.typedGraphs.Node;

import com.bio4j.model.enzymedb.nodes.Enzyme;

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
}
