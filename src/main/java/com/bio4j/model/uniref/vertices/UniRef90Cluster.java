package com.bio4j.model.uniref.vertices;

import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.edges.UniRef90Member;
import com.bio4j.model.uniprot_uniref.edges.UniRef90Representant;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class UniRef90Cluster <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniRefGraph.UniRefVertex<
  UniRef90Cluster<I, RV, RVT, RE, RET>,
  UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
  I, RV, RVT, RE, RET
  > {

  public UniRef90Cluster(RV vertex, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType type) {
  super(vertex, type);
  }

  @Override
  public UniRef90Cluster<I, RV, RVT, RE, RET> self() {
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
  //uniRef90Representant
  // ingoing
  public UniRef90Representant<I, RV, RVT, RE, RET> uniRef90Representant_in(){
  return inOne(graph().uniProtUniRefGraph().UniRef90Representant());
  }
  public Protein<I, RV, RVT, RE, RET> uniRef90Representant_inV(){
  return inOneV(graph().uniProtUniRefGraph().UniRef90Representant());
  }

  //uniRef90Member
  // ingoing
  public Stream<UniRef90Member<I, RV, RVT, RE, RET>> uniRef90Member_in(){
  return inMany(graph().uniProtUniRefGraph().UniRef90Member());
  }
  public Stream<Protein<I, RV, RVT, RE, RET>> uniRef90Member_inV(){
  return inManyV(graph().uniProtUniRefGraph().UniRef90Member());
  }




}
