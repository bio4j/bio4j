package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

public class ProteinIsoformInteraction<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniProtGraph.UniProtEdge<
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      ProteinIsoformInteraction<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinIsoformInteractionType,
      Isoform<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.IsoformType,
      I, RV, RVT, RE, RET
    >
{

  public ProteinIsoformInteraction(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinIsoformInteractionType type) {
    super(edge, type);
  }

  @Override
  public ProteinIsoformInteraction<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
