package com.bio4j.model.ncbiTaxonomy.vertices;

import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.edges.NCBITaxonParent;
import com.bio4j.model.ncbiTaxonomy_geninfo.edges.GenInfoNCBITaxon;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class NCBITaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends NCBITaxonomyGraph.NCBITaxonomyVertex<
  NCBITaxon<I, RV, RVT, RE, RET>,
  NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
  I, RV, RVT, RE, RET
  > {

  public NCBITaxon(RV vertex, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType type) {
  super(vertex, type);
  }

  @Override
  public NCBITaxon<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String id() {
  return get(type().id);
  }

  public String name() {
  return get(type().name);
  }

  public String comment() {
  return get(type().comment);
  }

  public String scientificName() {
  return get(type().scientificName);
  }

  public String taxonomicRank() {
  return get(type().taxonomicRank);
  }

  //----ncbiTaxonParent-------
  // ingoing
  public Optional<NCBITaxonParent<I, RV, RVT, RE, RET>> ncbiTaxonParent_in(){
  return inOneOptional(graph().NCBITaxonParent());
  }
  public Optional<NCBITaxon<I, RV, RVT, RE, RET>> ncbiTaxonParent_inV(){
  return inOneOptionalV(graph().NCBITaxonParent());
  }

  //----ncbiTaxonParent-------
  // outgoing
  public Optional<Stream<NCBITaxonParent<I, RV, RVT, RE, RET>>> ncbiTaxonParent_out(){
  return outManyOptional(graph().NCBITaxonParent());
  }
  public Optional<Stream<NCBITaxon<I, RV, RVT, RE, RET>>> ncbiTaxonParent_outV(){
  return outManyOptionalV(graph().NCBITaxonParent());
  }

  //----proteinNCBITaxon-------
  // ingoing
  public Optional<Stream<ProteinNCBITaxon<I, RV, RVT, RE, RET>>> proteinNCBITaxon_in(){
  return inManyOptional(graph().uniProtNCBITaxonomyGraph().ProteinNCBITaxon());
  }
  public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> proteinNCBITaxon_inV(){
  return inManyOptionalV(graph().uniProtNCBITaxonomyGraph().ProteinNCBITaxon());
  }

  //-----genInfoNCBITaxon----
  // ingoing
  public Optional<Stream<GenInfoNCBITaxon<I, RV, RVT, RE, RET>>> genInfoNCBITaxon_in(){   return inManyOptional(graph().ncbiTaxonomyGenInfoGraph().GenInfoNCBITaxon());}
  public Optional<Stream<GenInfo<I, RV, RVT, RE, RET>>> genInfoNCBITaxon_inV(){   return inManyOptionalV(graph().ncbiTaxonomyGenInfoGraph().GenInfoNCBITaxon());}


}
