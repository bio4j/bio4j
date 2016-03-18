package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class Submission <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Submission<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.SubmissionType,
I, RV, RVT, RE, RET
>  {

  public Submission(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.SubmissionType type) {
    super(vertex, type);
  }

  @Override
  public Submission<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
