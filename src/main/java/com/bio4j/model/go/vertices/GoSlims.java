package com.bio4j.model.go.vertices;

import com.bio4j.model.go.GoGraph;
import com.bio4j.angulillos.*;

public final class GoSlims<I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET>
extends GoGraph.GoVertex<
  GoSlims<I,RV,RVT,RE,RET>,
  GoGraph<I,RV,RVT,RE,RET>.GoSlimsType,
  I,RV,RVT,RE,RET
>
{

  public GoSlims(RV vertex, GoGraph<I,RV,RVT,RE,RET>.GoSlimsType type) {

  super(vertex, type);
  }

  @Override
  public GoSlims<I,RV,RVT,RE,RET> self() { return this; }
}










// package com.bio4j.model.go.nodes;

// import java.util.List;
// import java.util.stream.Stream;


// import com.bio4j.angulillos.Node;

// import com.bio4j.model.go.GoGraph.GoSlimsType;


// *
//  * Relationships into this singleton node represent GO Slims.
//  * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
//  * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>

// public interface GoSlims <
//   N extends GoSlims<N,NT>,
//   NT extends GoSlimsType<N,NT>
// >
//   extends Node<N,NT>
// {

//   // plantSlim
//   // incoming
//   // public Stream<? extends PlantSlim> plantSlim_in();
//   // public Stream<? extends Term> plantSlim_inNodes();
//   // TODO same for the other GoSlims
// }
