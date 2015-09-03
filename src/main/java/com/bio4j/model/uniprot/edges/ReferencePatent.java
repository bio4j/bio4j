package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Patent;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ReferencePatent <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
    ReferencePatent<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferencePatentType,
    Patent<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.PatentType,
    I, RV, RVT, RE, RET
    > {

  public ReferencePatent(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferencePatentType type) {

  super(edge, type);
  }

  @Override
  public ReferencePatent<I, RV, RVT, RE, RET> self() {
  return this;
  }
}