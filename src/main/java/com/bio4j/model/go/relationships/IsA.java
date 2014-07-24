package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.GoGraph;

public final class IsA<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends
  GoGraph.GoEdge<
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    IsA<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.IsAType,
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    I,RV,RVT,RE,RET
  >
{

  public IsA(RE edge, GoGraph<I,RV,RVT,RE,RET>.IsAType type) {

    super(edge, type);
  }

  @Override
  public IsA<I,RV,RVT,RE,RET> self() { return this; }
}
















// package com.bio4j.model.go.relationships;

// import com.bio4j.model.go.GoGraph.IsAType;
// import com.bio4j.model.go.GoGraph.TermType;
// import com.bio4j.model.go.nodes.GoTerm;
// import com.ohnosequences.typedGraphs.Relationship;

// *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 
// public interface IsA<
// 		S extends GoTerm<S, ST>, ST extends TermType<S, ST>,
// 		R extends IsA<S, ST, R, RT, T, TT>, RT extends IsAType<S, ST, R, RT, T, TT>,
// 		T extends GoTerm<T, TT>, TT extends TermType<T, TT>
// 		>
// 		extends Relationship<S, ST, R, RT, T, TT> {
// }
