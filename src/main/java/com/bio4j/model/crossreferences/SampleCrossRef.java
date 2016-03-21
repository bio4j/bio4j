package com.bio4j.model.crossreferences;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;

import com.bio4j.angulillos.*;

public class SampleCrossRef <
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  extends
    CrossReference <
      Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
      GoTerm<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.GoTermType,
      SampleCrossRef<I,RV,RVT,RE,RET>,
      UniProtGraph<I,RV,RVT,RE,RET>,
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public SampleCrossRef(
      UniProtGraph<I,RV,RVT,RE,RET>.ProteinType sourceVertexType,
      I raw,
      GoGraph<I,RV,RVT,RE,RET>.GoTermType targetVertexType
    )
    {
      super(
        sourceVertexType,
        TypedEdge.Type.Arity.manyToMany,
        raw,
        targetVertexType
      );
    }

    public final SampleCrossRef<I,RV,RVT,RE,RET> self() { return this; }

    // TODO this will be String at some point, thus making possible an arg-less constructor for LinkedToType
    public final LinkedToType LinkedTo() { return new LinkedToType(null); }

  }
