// package com.bio4j.model.enzymedb.vertices;
//
// import com.bio4j.model.enzymedb.EnzymeDBGraph;
// import com.bio4j.angulillos.UntypedGraph;
//
// public final class Enzyme<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
//   extends
//     EnzymeDBGraph.EnzymeDBVertex <
//       Enzyme<I,RV,RVT,RE,RET>,
//       EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType,
//       I,RV,RVT,RE,RET
//     >
// {
//
//   public Enzyme(RV vertex, EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType type) { super(vertex, type); }
//
//   @Override
//   public Enzyme<I,RV,RVT,RE,RET> self() { return this; }
// }
