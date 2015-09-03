package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.edges.UniRef100Member;
import com.bio4j.model.uniprot_uniref.edges.UniRef100Representant;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef100Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniRefGraph.UniRefVertex<
  UniRef100Cluster<I, RV, RVT, RE, RET>,
  UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
  I, RV, RVT, RE, RET
  > {

  public UniRef100Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType type) {
  super(vertex, type);
  }

  @Override
  public UniRef100Cluster<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String id() {
  return get(type().id);
  }
  public String name() {
  return get(type().name);
  }
  public String updatedDate() {
  return get(type().updatedDate);
  }
  public String representantAccession() { return get(type().representantAccession);}
  public String[] members() { return get(type().members);}

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships
  //uniRef100Representant
  // ingoing
  public UniRef100Representant<I, RV, RVT, RE, RET> uniRef100Representant_in(){
  return inOne(graph().uniProtUniRefGraph().UniRef100Representant());
  }
  public Protein<I, RV, RVT, RE, RET> uniRef100Representant_inV(){
  return inOneV(graph().uniProtUniRefGraph().UniRef100Representant());
  }

  //uniRef100Member
  // ingoing
  public Stream<UniRef100Member<I, RV, RVT, RE, RET>> uniRef100Member_in(){
  return inMany(graph().uniProtUniRefGraph().UniRef100Member());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> uniRef100Member_inV(){
  return inManyV(graph().uniProtUniRefGraph().UniRef100Member());
  }




}
