package com.bio4j.model.uniprot_ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.angulillos.*;

/*

# UniProt NCBI taxonomy graph

This graph includes the connection between Proteins and NCBI taxonmic units modelled via the edge type ProteinNCBITaxon

## data model

### ProteinNCBITaxon

Connecting `Protein` and `NCBITaxon` vertices.

 */
public abstract class UniProtNCBITaxonomyGraph<
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  implements
  TypedGraph<
    UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    > {

  protected I raw = null;

  public UniProtNCBITaxonomyGraph(I graph){
    raw = graph;
  }

  public I raw(){
    return raw;
  }

  public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();
  public abstract NCBITaxonomyGraph<I, RV, RVT, RE, RET> ncbiTaxonomyGraph();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // edges
  public abstract ProteinNCBITaxonType ProteinNCBITaxon();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types
  public final class ProteinNCBITaxonType
    extends
    UniProtNCBITaxonomyEdgeType <
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      ProteinNCBITaxon<I, RV, RVT, RE, RET>,
      UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.ProteinNCBITaxonType,
      // tgt
      NCBITaxon<I, RV, RVT, RE, RET>,
      NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
      NCBITaxonomyGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.ManyToOne
  {

  public ProteinNCBITaxonType(RET raw) {

    super(
      UniProtNCBITaxonomyGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtNCBITaxonomyGraph.this.ncbiTaxonomyGraph().NCBITaxon()
    );
  }

  @Override
  public ProteinNCBITaxonType value() {
    return graph().ProteinNCBITaxon();
  }

  @Override
  public ProteinNCBITaxon<I, RV, RVT, RE, RET> from(RE edge) {
    return new ProteinNCBITaxon<I, RV, RVT, RE, RET>(edge, this);
  }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class UniProtNCBITaxonomyVertexProperty<
    V extends UniProtNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniProtNCBITaxonomyVertexType<V, VT>,
    P extends UniProtNCBITaxonomyVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniProtNCBITaxonomyVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class UniProtNCBITaxonomyVertex<
    V extends UniProtNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniProtNCBITaxonomyVertexType<V, VT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedVertex<V, VT, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RV vertex;
  private VT type;

  protected UniProtNCBITaxonomyVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
    return type().graph();
  }

  @Override
  public RV raw() {
    return this.vertex;
  }

  @Override
  public VT type() {
    return type;
  }
  }

  abstract class UniProtNCBITaxonomyVertexType<
    V extends UniProtNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniProtNCBITaxonomyVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RVT raw;

  protected UniProtNCBITaxonomyVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtNCBITaxonomyGraph.this;
  }
  }

  public abstract static class UniProtNCBITaxonomyEdge<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtNCBITaxonomyEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
    ET extends UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniProtNCBITaxonomyEdgeType<S,ST,SG, E,ET, T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedEdge<
      S, ST, SG,
      E, ET, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      >
  {

  private RE edge;
  private ET type;

  protected UniProtNCBITaxonomyEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
    return type().graph();
  }

  @Override
  public RE raw() {
    return this.edge;
  }

  @Override
  public ET type() {
    return type;
  }
  }

  abstract class UniProtNCBITaxonomyEdgeType<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtNCBITaxonomyEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
    ET extends UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniProtNCBITaxonomyEdgeType<S,ST,SG, E,ET, T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
    >
    implements
    TypedEdge.Type<
      S, ST, SG,
      E, ET, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      >
  {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected UniProtNCBITaxonomyEdgeType(ST srcT, RET raw, TT tgtT) {

    this.raw = raw;
    this.srcT = srcT;
    this.tgtT = tgtT;
  }

  @Override
  public final ST sourceType() {
    return srcT;
  }

  @Override
  public final TT targetType() {
    return tgtT;
  }

  @Override
  public final RET raw() {
    return raw;
  }

  @Override
  public final UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtNCBITaxonomyGraph.this;
  }
  }
}
