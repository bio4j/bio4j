package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.GoGraph;

public final class HasPartOf<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends
  GoGraph.GoEdge<
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    HasPartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.HasPartOfType,
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    I,RV,RVT,RE,RET
  >
{

  public HasPartOf(RE edge, GoGraph<I,RV,RVT,RE,RET>.HasPartOfType type) {

    super(edge, type);
  }

  @Override
  public HasPartOf<I,RV,RVT,RE,RET> self() { return this; }
}













// package com.bio4j.model.go.relationships;

// import com.ohnosequences.typedGraphs.Relationship;
// import com.bio4j.model.go.nodes.GoTerm;
// import com.bio4j.model.go.GoGraph.*;

// *
//  *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 
// public interface HasPartOf <
//   S extends GoTerm<S,ST>, ST extends TermType<S,ST>,
//   R extends HasPartOf<S,ST,R,RT,T,TT>, RT extends HasPartOfType<S,ST,R,RT,T,TT>,
//   T extends GoTerm<T,TT>, TT extends TermType<T,TT>
// >
//   extends Relationship<S,ST,R,RT,T,TT>
// {}
