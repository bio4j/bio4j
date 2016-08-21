package com.bio4j.model.uniprot_go;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.edges.GoAnnotation;
import com.bio4j.angulillos.*;

/*

# UniProt GO graph

This graph includes the connection between Proteins and Gene Ontology terms modelled via the edge type GOAnnotation

## data model

### GOAnnotation

Connecting `Protein` and `GOTerm` vertices.

 */
public abstract class UniProtGoGraph<
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  implements
  TypedGraph<
    UniProtGoGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    > {

  protected I raw = null;

  public UniProtGoGraph(I graph){
    raw = graph;
  }

  public I raw(){
    return raw;
  }

  public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();
  public abstract GoGraph<I, RV, RVT, RE, RET> goGraph();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // edges
  public abstract GoAnnotationType GoAnnotation();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types
  public final class GoAnnotationType
  extends
  UniProtGoEdgeType <
    // src
    Protein<I, RV, RVT, RE, RET>,
    UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
    UniProtGraph<I, RV, RVT, RE, RET>,
    // edge
    GoAnnotation<I, RV, RVT, RE, RET>,
    UniProtGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType,
    // tgt
    GoTerm<I, RV, RVT, RE, RET>,
    GoGraph<I, RV, RVT, RE, RET>.GoTermType,
    GoGraph<I, RV, RVT, RE, RET>
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public GoAnnotationType(RET raw) {

    super(
    UniProtGoGraph.this.uniProtGraph().Protein(),
    raw,
    UniProtGoGraph.this.goGraph().GoTerm()
    );
  }

  @Override
  public GoAnnotationType value() {
    return graph().GoAnnotation();
  }

  @Override
  public GoAnnotation<I, RV, RVT, RE, RET> from(RE edge) {
    return new GoAnnotation<I, RV, RVT, RE, RET>(edge, this);
  }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class UniProtGoVertexProperty<
    V extends UniProtGoVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtGoGraph<I, RV, RVT, RE, RET>.UniProtGoVertexType<V, VT>,
    P extends UniProtGoVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, UniProtGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniProtGoVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class UniProtGoVertex<
    V extends UniProtGoVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtGoGraph<I, RV, RVT, RE, RET>.UniProtGoVertexType<V, VT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedVertex<V, VT, UniProtGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RV vertex;
  private VT type;

  protected UniProtGoVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniProtGoGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniProtGoVertexType<
    V extends UniProtGoVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtGoGraph<I, RV, RVT, RE, RET>.UniProtGoVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniProtGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RVT raw;

  protected UniProtGoVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniProtGoGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtGoGraph.this;
  }
  }

  public abstract static class UniProtGoEdge<
  // src
  S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
  ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
  SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
  // edge
  E extends UniProtGoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
  ET extends UniProtGoGraph<I, RV, RVT, RE, RET>.UniProtGoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
  // tgt
  T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
  TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
  TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
  I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
  >
  implements
  TypedEdge<
    S, ST, SG,
    E, ET, UniProtGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
    T, TT, TG
  >
  {

  private RE edge;
  private ET type;

  protected UniProtGoEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public UniProtGoGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniProtGoEdgeType<
  // src
  S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
  ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
  SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
  // edge
  E extends UniProtGoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
  ET extends UniProtGoGraph<I, RV, RVT, RE, RET>.UniProtGoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
  // tgt
  T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
  TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
  TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
  >
  implements
  TypedEdge.Type<
    S, ST, SG,
    E, ET, UniProtGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
    T, TT, TG
  >
  {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected UniProtGoEdgeType(ST srcT, RET raw, TT tgtT) {

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
  public final UniProtGoGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtGoGraph.this;
  }
  }
}
