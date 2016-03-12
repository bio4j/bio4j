package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.model.uniprot.vertices.UnpublishedObservation;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ReferenceUnpublishedObservation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
ReferenceUnpublishedObservation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceUnpublishedObservationType,
UnpublishedObservation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType,
I, RV, RVT, RE, RET
> {

  public ReferenceUnpublishedObservation(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceUnpublishedObservationType type) {

    super(edge, type);
  }

  @Override
  public ReferenceUnpublishedObservation<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
