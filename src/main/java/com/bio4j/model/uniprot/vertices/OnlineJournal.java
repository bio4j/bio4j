package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class OnlineJournal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
OnlineJournal<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.OnlineJournalType,
I, RV, RVT, RE, RET
> {

  public OnlineJournal(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.OnlineJournalType type) {
    super(vertex, type);
  }

  @Override
  public OnlineJournal<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
