package com.bio4j.model.uniprot_go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/25/2014.
 */
public interface UniprotGoGraph {

	public static interface GoAnnotationType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends GoAnnotation<S, ST, R, RT, T, TT>, RT extends GoAnnotationType<S, ST, R, RT, T, TT>,
			T extends GoTerm<T, TT>, TT extends GoGraph.TermType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}
}
