package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ReferenceAuthorConsortium;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Consortium <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Consortium<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.ConsortiumType,
  I, RV, RVT, RE, RET
  > {

  public Consortium(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ConsortiumType type) {
  super(vertex, type);
  }

  @Override
  public Consortium<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // referenceAuthorConsortium
  // ingoing
  public Stream<ReferenceAuthorConsortium<I, RV, RVT, RE, RET>> referenceAuthorConsortium_in(){
  return inMany(graph().ReferenceAuthorConsortium());
  }
  public Stream<Reference<I, RV, RVT, RE, RET>> referenceAuthorConsortium_inNodes(){
  return inManyV(graph().ReferenceAuthorConsortium());
  }

}
