package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinKeyword;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public class Keyword <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Keyword<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.KeywordType,
  I, RV, RVT, RE, RET
  > {

  public Keyword(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.KeywordType type) {
  super(vertex, type);
  }

  @Override
  public Keyword<I, RV, RVT, RE, RET> self() {
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

  // proteinKeyword
  // ingoing
  public Stream<ProteinKeyword<I, RV, RVT, RE, RET>> proteinKeyword_in(){
  return inMany(graph().ProteinKeyword());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> proteinKeyword_inV(){
  return inManyV(graph().ProteinKeyword());
  }


}
