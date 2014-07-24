package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.*;
import com.bio4j.model.go.nodes.*;
import com.bio4j.model.go.GoGraph;

public final class SubOntology<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends
  GoGraph.GoEdge<
    GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    SubOntology<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.SubOntologyType,
    SubOntologies<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.SubOntologiesType,
    I,RV,RVT,RE,RET
  >
{

  public SubOntology(RE edge, GoGraph<I,RV,RVT,RE,RET>.SubOntologyType type) {

    super(edge, type);
  }

  @Override
  public SubOntology<I,RV,RVT,RE,RET> self() { return this; }
}















// package com.bio4j.model.go.relationships;

// import com.bio4j.model.go.nodes.SubOntologies;
// import com.bio4j.model.go.nodes.GoTerm;
// import com.bio4j.model.go.GoGraph.*;
// import com.bio4j.model.go.GoGraph.SubOntologiesType;
// import com.ohnosequences.typedGraphs.Relationship;

// *
//  *
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 
// public interface SubOntology <
//         S extends GoTerm<S,ST>, ST extends TermType<S,ST>,
//         R extends SubOntology<S,ST,R,RT,T,TT>, RT extends SubOntologyType<S,ST,R,RT,T,TT>,
//         T extends SubOntologies<T,TT>, TT extends SubOntologiesType<T,TT>
//         >
//         extends Relationship<S,ST,R,RT,T,TT>
// {}
