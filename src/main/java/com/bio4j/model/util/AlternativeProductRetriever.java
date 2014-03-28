package com.bio4j.model.util;

import com.bio4j.model.isoforms.nodes.AlternativeProduct;

public interface AlternativeProductRetriever extends NodeRetriever<AlternativeProduct> {

  public AlternativeProduct getAlternativeProductInitiationNode();
  public AlternativeProduct getAlternativeProductPromoterNode();
  public AlternativeProduct getAlternativeProductRibosomalFrameshiftingNode();
  public AlternativeProduct getAlternativeProductSplicingNode();

}
