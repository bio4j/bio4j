package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.SubcellularLocation;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ProteinSubcellularLocation <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
ProteinSubcellularLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinSubcellularLocationType,
SubcellularLocation<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.SubcellularLocationType,
I, RV, RVT, RE, RET
> {

  // properties
  public String status() {
    return get(type().status);
  }
  public String topology() {  return get(type().topology);  }
  public String topologyStatus() {  return get(type().topologyStatus);  }
  public String evidence() {
    return get(type().evidence);
  }

  public ProteinSubcellularLocation(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinSubcellularLocationType type) {

    super(edge, type);
  }

  @Override
  public ProteinSubcellularLocation<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
