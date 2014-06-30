package com.bio4j.model.go.relationships;

import com.bio4j.model.go.GoGraph.IsAType;
import com.bio4j.model.go.GoGraph.TermType;
import com.bio4j.model.go.nodes.GoTerm;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface IsA<
		S extends GoTerm<S, ST>, ST extends TermType<S, ST>,
		R extends IsA<S, ST, R, RT, T, TT>, RT extends IsAType<S, ST, R, RT, T, TT>,
		T extends GoTerm<T, TT>, TT extends TermType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
