package com.bio4j.model.util;

import com.bio4j.model.nodes.refseq.GenomeElement;

public interface GenomeElementRetriever extends NodeRetriever<GenomeElement>{

  public GenomeElement getGenomeElementByVersion(String version);
  
}
