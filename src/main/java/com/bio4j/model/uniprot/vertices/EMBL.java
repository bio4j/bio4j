package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinEMBL;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
* Created by ppareja on 7/23/2014.
*/
public final class EMBL <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
EMBL<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.EMBLType,
I, RV, RVT, RE, RET
> {

  public EMBL(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.EMBLType type) {
    super(vertex, type);
  }

  @Override
  public EMBL<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String id() {
    return get(type().id);
  }
  public String proteinSequenceId() {
    return get(type().proteinSequenceId);
  }
  public String moleculeType() {
    return get(type().moleculeType);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // proteinEMBL
  // ingoing
  public Stream<ProteinEMBL<I, RV, RVT, RE, RET>> proteinEMBL_in(){
    return inMany(graph().ProteinEMBL());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> proteinEMBL_inV(){
    return inManyV(graph().ProteinEMBL());
  }


}
