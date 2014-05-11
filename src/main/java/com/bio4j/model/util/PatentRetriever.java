package com.bio4j.model.util;

import com.bio4j.model.uniprot.nodes.Patent;

public interface PatentRetriever extends NodeRetriever<Patent> {

  public Patent getPatentByNumber(String patentNumber);

}
