package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.SubcellularLocation;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class SubcellularLocationParent <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
SubcellularLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
SubcellularLocationParent<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationParentType,
SubcellularLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
I, RV, RVT, RE, RET
> {

  public SubcellularLocationParent(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationParentType type) {

    super(edge, type);
  }

  @Override
  public SubcellularLocationParent<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
