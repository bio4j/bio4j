package com.bio4j.model.enzymedb.relationships;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymaticActivityType;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface EnzymaticActivity <
		S extends Protein<S,ST>, ST extends ProteinType<S,ST>,
		R extends EnzymaticActivity<S,ST,R,RT,T,TT>, RT extends EnzymaticActivityType<S,ST,R,RT,T,TT>,
		T extends Enzyme<T,TT>, TT extends EnzymeType<T,TT>
		>
		extends Relationship<S,ST,R,RT,T,TT>
{}
