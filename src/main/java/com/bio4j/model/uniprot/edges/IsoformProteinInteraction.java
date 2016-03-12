package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 9/23/2014.
*/
public class IsoformProteinInteraction<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Isoform<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.IsoformType,
IsoformProteinInteraction<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.IsoformProteinInteractionType,
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
I, RV, RVT, RE, RET
> {

  public IsoformProteinInteraction(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.IsoformProteinInteractionType type) {

    super(edge, type);
  }

  // properties
  public String experiments() {
    return get(type().experiments);
  }
  public String organismsDiffer() {  return get(type().organismsDiffer);  }
  public String intActId1() {
    return get(type().intActId1);
  }
  public String intActId2() {
    return get(type().intActId2);
  }

  @Override
  public IsoformProteinInteraction<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
