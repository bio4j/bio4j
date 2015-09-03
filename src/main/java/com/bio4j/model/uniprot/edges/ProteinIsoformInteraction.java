package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Isoform;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 9/23/2014.
 */
public class ProteinIsoformInteraction<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    ProteinIsoformInteraction<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinIsoformInteractionType,
    Isoform<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.IsoformType,
    I, RV, RVT, RE, RET
    > {

  public ProteinIsoformInteraction(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinIsoformInteractionType type) {

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
  public ProteinIsoformInteraction<I, RV, RVT, RE, RET> self() {
  return this;
  }
}

