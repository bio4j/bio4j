package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot.vertices.RefSeq;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class ProteinRefSeq <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    ProteinRefSeq<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinRefSeqType,
    RefSeq<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.RefSeqType,
    I, RV, RVT, RE, RET
    > {

  public ProteinRefSeq(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinRefSeqType type) {

  super(edge, type);
  }

  @Override
  public ProteinRefSeq<I, RV, RVT, RE, RET> self() {
  return this;
  }
}