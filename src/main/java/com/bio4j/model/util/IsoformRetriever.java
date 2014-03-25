package com.bio4j.model.util;

import com.bio4j.model.nodes.Isoform;

public interface IsoformRetriever extends NodeRetriever<Isoform>{

  public Isoform getIsoformById(String isoformId);

}
