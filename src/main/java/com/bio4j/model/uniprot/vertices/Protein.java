package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Protein <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniProtGraph.UniProtVertex <
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      I, RV, RVT, RE, RET
    >
{

  public Protein(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType type) { super(vertex, type); }

  @Override
  public Protein<I, RV, RVT, RE, RET> self() { return this; }
}
