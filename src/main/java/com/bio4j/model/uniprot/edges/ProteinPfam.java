package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Pfam;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinPfam <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    ProteinPfam<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinPfamType,
    Pfam<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.PfamType,
    I, RV, RVT, RE, RET
    > {

  public ProteinPfam(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinPfamType type) {

  super(edge, type);
  }

  @Override
  public ProteinPfam<I, RV, RVT, RE, RET> self() {
  return this;
  }
}