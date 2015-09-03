package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.BookCity;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class City <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  City<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.CityType,
  I, RV, RVT, RE, RET
  > {

  public City(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.CityType type) {
  super(vertex, type);
  }

  @Override
  public City<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // bookCity
  // ingoing
  public Stream<BookCity<I, RV, RVT, RE, RET>> bookCity_in(){
    return inMany(graph().BookCity());
  }
  public Stream<Book<I, RV, RVT, RE, RET>> bookCity_inV(){
    return inManyV(graph().BookCity());
  }


}
