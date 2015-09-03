package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Taxon;
import com.bio4j.angulillos.UntypedGraph;

/**
 * Created by ppareja on 7/28/2014.
 */
public final class TaxonParent <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtGraph.UniProtEdge<
    Taxon<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.TaxonType,
    TaxonParent<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.TaxonParentType,
    Taxon<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.TaxonType,
    I, RV, RVT, RE, RET
    > {

  public TaxonParent(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.TaxonParentType type) {

  super(edge, type);
  }

  @Override
  public TaxonParent<I, RV, RVT, RE, RET> self() {
  return this;
  }
}