package com.bio4j.model.util;

import com.bio4j.model.nodes.citation.DB;

public interface DBRetriever extends NodeRetriever<DB> {

  public DB getDBByName(String dbName);

}
