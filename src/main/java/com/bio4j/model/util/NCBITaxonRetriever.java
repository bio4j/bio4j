package com.bio4j.model.util;

import com.bio4j.model.nodes.NCBITaxon;

public interface NCBITaxonRetriever extends NodeRetriever<NCBITaxon>{

  public NCBITaxon getNCBITaxonByTaxId(String taxId);
  public NCBITaxon getNCBITaxonByGiId(String giId);

}
