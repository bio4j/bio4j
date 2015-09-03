package com.bio4j.model.geninfo.vertices;

import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.edges.GenInfoNCBITaxon;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GenInfo<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends GenInfoGraph.GenInfoVertex<
  GenInfo<I, RV, RVT, RE, RET>,
  GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType,
  I, RV, RVT, RE, RET
  > {

  public GenInfo(RV vertex, GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType type) {
  super(vertex, type);
  }

  @Override
  public GenInfo<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String id() {
  return get(type().id);
  }

  // rels

  //-----genInfoNCBITaxon----
  // outgoing
  public GenInfoNCBITaxon<I, RV, RVT, RE, RET> genInfoNCBITaxon_out(){   return outOne(graph().ncbiTaxonomyGenInfoGraph().GenInfoNCBITaxon());}
  public NCBITaxon<I, RV, RVT, RE, RET> genInfoNCBITaxon_outV(){   return outOneV(graph().ncbiTaxonomyGenInfoGraph().GenInfoNCBITaxon());}




}

