package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.FeatureType;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ProteinFeature <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
ProteinFeature<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinFeatureType,
FeatureType<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.FeatureTypeType,
I, RV, RVT, RE, RET
> {

  public ProteinFeature(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinFeatureType type) {
    super(edge, type);
  }

  @Override
  public ProteinFeature<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
