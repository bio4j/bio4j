package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinSequenceCaution;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class SequenceCaution <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  SequenceCaution<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.SequenceCautionType,
  I, RV, RVT, RE, RET
  > {

  public SequenceCaution(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.SequenceCautionType type) {
  super(vertex, type);
  }

  @Override
  public SequenceCaution<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // proteinSequenceCaution
  // ingoing
  public Stream<ProteinSequenceCaution<I, RV, RVT, RE, RET>> proteinSequenceCaution_in(){
  return inMany(graph().ProteinSequenceCaution());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>>  proteinSequenceCaution_inV(){
  return inManyV(graph().ProteinSequenceCaution());
  }


}
