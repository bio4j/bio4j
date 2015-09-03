package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ArticleJournal;
import com.bio4j.model.uniprot.edges.ArticlePubmed;
import com.bio4j.model.uniprot.edges.ReferenceArticle;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Article <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Article<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.ArticleType,
  I, RV, RVT, RE, RET
  >  {

  public Article(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ArticleType type) {
  super(vertex, type);
  }

  @Override
  public Article<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String title() {
  return get(type().title);
  }

  public String doId() {
  return get(type().doId);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // articlePubmed
  // outgoing
  public Optional<ArticlePubmed<I, RV, RVT, RE, RET>> articlePubmed_out(){
  return outOneOptional(graph().ArticlePubmed());
  }
  public Optional<Pubmed<I, RV, RVT, RE, RET>> articlePubmed_outV(){
  return outOneOptionalV(graph().ArticlePubmed());
  }
  // referenceArticle
  // ingoing
  public ReferenceArticle<I, RV, RVT, RE, RET> referenceArticle_in(){
  return inOne(graph().ReferenceArticle());
  }
  public Reference<I, RV, RVT, RE, RET> referenceArticle_inV(){
  return inOneV(graph().ReferenceArticle());
  }
  // articleJournal
  // outgoing
  public Optional<ArticleJournal<I, RV, RVT, RE, RET>> articleJournal_out(){
    return outOneOptional(graph().ArticleJournal());
  }
  public Optional<Journal<I, RV, RVT, RE, RET>> articleJournal_outV(){
    return outOneOptionalV(graph().ArticleJournal());
  }
}
