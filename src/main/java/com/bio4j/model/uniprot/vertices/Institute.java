package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ThesisInstitute;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
* Created by ppareja on 7/23/2014.
*/
public final class Institute <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Institute<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.InstituteType,
I, RV, RVT, RE, RET
> {

  public Institute(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.InstituteType type) {
    super(vertex, type);
  }

  @Override
  public Institute<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String name() {
    return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // thesisInstitute
  // ingoing
  public Stream<ThesisInstitute<I, RV, RVT, RE, RET>> thesisInstitute_in(){
    return inMany(graph().ThesisInstitute());
  }
  public Stream<Thesis<I, RV, RVT, RE, RET>> thesisInstitute_inV(){
    return inManyV(graph().ThesisInstitute());
  }


}
