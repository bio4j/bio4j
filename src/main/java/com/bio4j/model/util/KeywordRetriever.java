package com.bio4j.model.util;

import com.bio4j.model.nodes.Keyword;

public interface KeywordRetriever extends NodeRetriever<Keyword>{

  public Keyword getKeywordById(String keywordId);
  public Keyword getKeywordByName(String keywordName);

}
