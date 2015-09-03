package com.bio4j.model.uniprot_uniref.edges;


import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class UniRef100Member<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
  UniProtUniRefGraph.UniProtUniRefEdge<
    // src
    Protein<I, RV, RVT, RE, RET>,
    UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    UniProtGraph<I, RV, RVT, RE, RET>,
    // edge
    UniRef100Member<I, RV, RVT, RE, RET>,
    UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef100MemberType,
    //tgt
    UniRef100Cluster<I, RV, RVT, RE, RET>,
    UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
    UniRefGraph<I, RV, RVT, RE, RET>,
    // raw stuff
    I, RV, RVT, RE, RET
    > {

  public UniRef100Member(RE edge, UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef100MemberType type) {

  super(edge, type);
  }

  // properties
  public String proteinAccession() {
  return get(type().proteinAccession);
  }

  @Override
  public UniRef100Member<I, RV, RVT, RE, RET> self() {
  return this;
  }
}