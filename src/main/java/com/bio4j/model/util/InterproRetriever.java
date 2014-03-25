package com.bio4j.model.util;

import com.bio4j.model.nodes.Interpro;

public interface InterproRetriever extends NodeRetriever<Interpro>{

  public Interpro getInterproById(String interproId);

}
