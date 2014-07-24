package com.bio4j.model.uniprot_enzymedb;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/11/2014.
 */
public interface UniprotEnzymeDBGraph {

	public static interface EnzymaticActivityType<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends EnzymaticActivity<S, ST, R, RT, T, TT>, RT extends EnzymaticActivityType<S, ST, R, RT, T, TT>,
			T extends Enzyme<T, TT>, TT extends EnzymeDBGraph.EnzymeType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}
}
