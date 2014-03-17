package com.bio4j.model.util;

import com.bio4j.model.nodes.citation.Patent;

public interface PatentRetriever extends NodeRetriever<Patent> {

  public Patent getPatentByNumber(String patentNumber);

}
