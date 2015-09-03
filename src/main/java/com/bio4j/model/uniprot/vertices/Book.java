package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.BookCity;
import com.bio4j.model.uniprot.edges.BookPublisher;
import com.bio4j.model.uniprot.edges.ReferenceBook;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class Book<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Book<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.BookType,
  I, RV, RVT, RE, RET
  >  {

  public Book(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.BookType type) {
  super(vertex, type);
  }

  @Override
  public Book<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  //referenceBook
  // ingoing
  public ReferenceBook<I, RV, RVT, RE, RET> referenceBook_in(){
  return inOne(graph().ReferenceBook());
  }
  public Reference<I, RV, RVT, RE, RET> referenceBook_inV(){
  return inOneV(graph().ReferenceBook());
  }

  // bookCity
  // outgoing
  public Optional<BookCity<I, RV, RVT, RE, RET>> bookCity_out(){
  return outOneOptional(graph().BookCity());
  }
  public Optional<City<I, RV, RVT, RE, RET>> bookCity_outV(){
  return outOneOptionalV(graph().BookCity());
  }

  // bookPublisher
  // outgoing
  public Optional<BookPublisher<I, RV, RVT, RE, RET>> bookPublisher_out(){
    return outOneOptional(graph().BookPublisher());
  }
  public Optional<Publisher<I, RV, RVT, RE, RET>> bookPublisher_outV(){
    return outOneOptionalV(graph().BookPublisher());
  }
}
