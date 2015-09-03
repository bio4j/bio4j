package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.BookPublisher;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Publisher <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Publisher<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.PublisherType,
  I, RV, RVT, RE, RET
  > {

  public Publisher(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PublisherType type) {
  super(vertex, type);
  }

  @Override
  public Publisher<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // bookPublisher
  // ingoing
  public Stream<BookPublisher<I, RV, RVT, RE, RET>> bookPublisher_in(){
  return inMany(graph().BookPublisher());
  }
  public Stream<Book<I, RV, RVT, RE, RET>> bookPublisher_inV(){
  return inManyV(graph().BookPublisher());
  }


}
