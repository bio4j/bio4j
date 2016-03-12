package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Ensembl;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ProteinEnsembl <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
ProteinEnsembl<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinEnsemblType,
Ensembl<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.EnsemblType,
I, RV, RVT, RE, RET
> {

  public ProteinEnsembl(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinEnsemblType type) {

    super(edge, type);
  }

  @Override
  public ProteinEnsembl<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
