package com.bio4j.model.util;

import com.bio4j.model.uniprot.nodes.Taxon;

public interface TaxonRetriever extends NodeRetriever<Taxon>{

  public Taxon getTaxonByName(String taxonName);

}
