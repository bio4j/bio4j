package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinPIR;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
* Created by ppareja on 7/23/2014.
*/
public final class PIR <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
PIR<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.PIRType,
I, RV, RVT, RE, RET
> {
  
  public PIR(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.PIRType type) {
    super(vertex, type);
  }

  @Override
  public PIR<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String id() {
    return get(type().id);
  }
  public String entryName() { return get(type().entryName);}

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // proteinPIR
  // ingoing
  public Stream<ProteinPIR<I, RV, RVT, RE, RET>> proteinPIR_in(){
    return inMany(graph().ProteinPIR());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> proteinPIR_inV(){
    return inManyV(graph().ProteinPIR());
  }


}
