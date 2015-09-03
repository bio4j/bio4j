package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ProteinDataset;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Dataset <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  Dataset<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.DatasetType,
  I, RV, RVT, RE, RET
  > {

  public Dataset(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.DatasetType type) {
  super(vertex, type);
  }

  @Override
  public Dataset<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // proteinDataset
  // ingoing
  public Stream<ProteinDataset<I, RV, RVT, RE, RET>> proteinDataset_in(){
  return inMany(graph().ProteinDataset());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> proteinDataset_inV(){
  return inManyV(graph().ProteinDataset());
  }


}
