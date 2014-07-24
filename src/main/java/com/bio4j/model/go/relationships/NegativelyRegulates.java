package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.GoGraph;

public final class NegativelyRegulates<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends
  GoGraph.GoEdge<
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    NegativelyRegulates<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.NegativelyRegulatesType,
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    I,RV,RVT,RE,RET
  >
{

  public NegativelyRegulates(RE edge, GoGraph<I,RV,RVT,RE,RET>.NegativelyRegulatesType type) {

    super(edge, type);
  }

  @Override
  public NegativelyRegulates<I,RV,RVT,RE,RET> self() { return this; }
}














// package com.bio4j.model.go.relationships;

// import com.bio4j.model.go.nodes.GoTerm;
// import com.ohnosequences.typedGraphs.Relationship;
// import com.bio4j.model.go.GoGraph.*;

// *
//  *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 
// public interface NegativelyRegulates <
//   S extends GoTerm<S,ST>, ST extends TermType<S,ST>,
//   R extends NegativelyRegulates<S,ST,R,RT,T,TT>, RT extends NegativelyRegulatesType<S,ST,R,RT,T,TT>,
//   T extends GoTerm<T,TT>, TT extends TermType<T,TT>
// >
//   extends Relationship<S,ST,R,RT,T,TT>
// {}