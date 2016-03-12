package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ReferenceUnpublishedObservation;
import com.bio4j.angulillos.UntypedGraph;

/**
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class UnpublishedObservation<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
UnpublishedObservation<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType,
I, RV, RVT, RE, RET
>  {

  public UnpublishedObservation(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.UnpublishedObservationType type) {
    super(vertex, type);
  }

  @Override
  public UnpublishedObservation<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String scope() {
    return get(type().scope);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // referenceUnpublishedObservation
  // ingoing
  public ReferenceUnpublishedObservation<I, RV, RVT, RE, RET> referenceUnpublishedObservation_in(){
    return inOne(graph().ReferenceUnpublishedObservation());
  }
  public Reference<I, RV, RVT, RE, RET> referenceUnpublishedObservation_inV(){
    return inOneV(graph().ReferenceUnpublishedObservation());
  }
}
