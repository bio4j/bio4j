package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.GoGraph;

public final class PartOf<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends
  GoGraph.GoEdge<
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    PartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.PartOfType,
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    I,RV,RVT,RE,RET
  >
{

  public PartOf(RE edge, GoGraph<I,RV,RVT,RE,RET>.PartOfType type) {

    super(edge, type);
  }

  @Override
  public PartOf<I,RV,RVT,RE,RET> self() { return this; }
}







// *
//  *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 
// public interface PartOf <
//   S extends GoTerm<S,ST>, ST extends TermType<S,ST>,
//   R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
//   T extends GoTerm<T,TT>, TT extends TermType<T,TT>
// >
//   extends Relationship<S,ST,R,RT,T,TT>
// {}
