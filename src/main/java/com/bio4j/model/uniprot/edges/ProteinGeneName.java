package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.GeneName;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ProteinGeneName <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
ProteinGeneName<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinGeneNameType,
GeneName<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.GeneNameType,
I, RV, RVT, RE, RET
> {

  public ProteinGeneName(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinGeneNameType type) {
    super(edge, type);
  }

  // properties
  public String geneNameType() {
    return get(type().geneNameType);
  }


  @Override
  public ProteinGeneName<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
