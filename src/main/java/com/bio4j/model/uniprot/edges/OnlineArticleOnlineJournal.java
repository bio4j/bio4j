package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.OnlineArticle;
import com.bio4j.model.uniprot.vertices.OnlineJournal;
import com.bio4j.angulillos.UntypedGraph;

/**
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class OnlineArticleOnlineJournal<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
OnlineArticle<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
OnlineArticleOnlineJournal<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.OnlineArticleOnlineJournalType,
OnlineJournal<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.OnlineJournalType,
I, RV, RVT, RE, RET
> {

  public OnlineArticleOnlineJournal(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.OnlineArticleOnlineJournalType type) {

    super(edge, type);
  }

  // properties
  public String locator() {
    return get(type().locator);
  }

  @Override
  public OnlineArticleOnlineJournal<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
