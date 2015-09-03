package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Disease;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinDisease <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    ProteinDisease<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinDiseaseType,
    Disease<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.DiseaseType,
    I, RV, RVT, RE, RET
    > {

  public String status() {
  return get(type().status);
  }
  public String text() {
  return get(type().text);
  }
  public String evidence() {
  return get(type().evidence);
  }

  public ProteinDisease(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinDiseaseType type) {

  super(edge, type);
  }

  @Override
  public ProteinDisease<I, RV, RVT, RE, RET> self() {
  return this;
  }
}