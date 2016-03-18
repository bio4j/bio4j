package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class SequenceCaution <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
SequenceCaution<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.SequenceCautionType,
I, RV, RVT, RE, RET
> {

  public SequenceCaution(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.SequenceCautionType type) {
    super(vertex, type);
  }

  @Override
  public SequenceCaution<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
