package com.bio4j.model.go.vertices;

// base types
import com.bio4j.model.go.GoGraph;
import com.ohnosequences.typedGraphs.*;

/**
 * Rels into this singleton node represent subontologies.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public final class SubOntologies<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
extends GoGraph.GoVertex<
  SubOntologies<I,RV,RVT,RE,RET>,
  GoGraph<I,RV,RVT,RE,RET>.SubOntologiesType,
  I,RV,RVT,RE,RET
> 
{

  public SubOntologies(RV vertex, GoGraph<I,RV,RVT,RE,RET>.SubOntologiesType type) {

    super(vertex, type);
  }

  @Override
  public SubOntologies<I,RV,RVT,RE,RET> self() { return this; }
}
