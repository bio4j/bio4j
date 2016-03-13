package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Book;
import com.bio4j.model.uniprot.vertices.Reference;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ReferenceBook <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Reference<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceType,
ReferenceBook<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceBookType,
Book<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.BookType,
I, RV, RVT, RE, RET
> {

  // properties
  public String title() {
    return get(type().title);
  }
  public int first() {  return get(type().first);  }
  public int last() {
    return get(type().last);
  }
  public String volume() {
    return get(type().volume);
  }

  public ReferenceBook(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ReferenceBookType type) {

    super(edge, type);
  }

  @Override
  public ReferenceBook<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
