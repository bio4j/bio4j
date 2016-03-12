package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinInterPro;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
* Created by ppareja on 7/29/2014.
*/
public final class InterPro <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
InterPro<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.InterProType,
I, RV, RVT, RE, RET
> {

  public InterPro(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.InterProType type) {
    super(vertex, type);
  }

  @Override
  public InterPro<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String id() {
    return get(type().id);
  }
  public String name() {
    return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // proteinInterPro
  // ingoing
  public Stream<ProteinInterPro<I, RV, RVT, RE, RET>> proteinInterPro_in(){
    return inMany(graph().ProteinInterPro());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> proteinInterPro_inV(){
    return inManyV(graph().ProteinInterPro());
  }


}
