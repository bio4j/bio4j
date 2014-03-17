package com.bio4j.model.util;

import java.util.List;
import com.bio4j.model.nodes.citation.OnlineArticle;

public interface OnlineArticleRetriever extends NodeRetriever<OnlineArticle> {

  public List<? extends OnlineArticle> getOnlineArticlesByTitle(String onlineArticleTitle);

}
