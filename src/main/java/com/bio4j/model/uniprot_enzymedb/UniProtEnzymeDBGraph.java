package com.bio4j.model.uniprot_enzymedb;


import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_enzymedb.edges.EnzymaticActivity;
import com.bio4j.angulillos.*;

/*

# UniProt Enzyme DB graph

This graph includes the connection between Enzymes and Proteins modelled via the edge type EnzymaticActivity

## data model

### EnzymaticActivity

Connecting `Protein` and `Enzyme` vertices.

 */
public abstract class UniProtEnzymeDBGraph<
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  implements
  TypedGraph<
    UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    > {

  protected I raw = null;

  public UniProtEnzymeDBGraph(I graph){
    raw = graph;
  }

  public I raw(){
    return raw;
  }

  public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();
  public abstract EnzymeDBGraph<I, RV, RVT, RE, RET> enzymeDBGraph();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // edges
  public abstract EnzymaticActivityType EnzymaticActivity();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types
  public final class EnzymaticActivityType
    extends
    UniProtEnzymeDBEdgeType <
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      EnzymaticActivity<I, RV, RVT, RE, RET>,
      UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymaticActivityType,
      // tgt
      Enzyme<I, RV, RVT, RE, RET>,
      EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeType,
      EnzymeDBGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.ManyToMany
  {

  public EnzymaticActivityType(RET raw) {

    super(
      UniProtEnzymeDBGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtEnzymeDBGraph.this.enzymeDBGraph().Enzyme()
    );
  }

  @Override
  public EnzymaticActivityType value() {
    return graph().EnzymaticActivity();
  }

  @Override
  public EnzymaticActivity<I, RV, RVT, RE, RET> from(RE edge) {
    return new EnzymaticActivity<I, RV, RVT, RE, RET>(edge, this);
  }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class UniProtEnzymeDBVertexProperty<
    V extends UniProtEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.UniProtEnzymeDBVertexType<V, VT>,
    P extends UniProtEnzymeDBVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniProtEnzymeDBVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class UniProtEnzymeDBVertex<
    V extends UniProtEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.UniProtEnzymeDBVertexType<V, VT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedVertex<V, VT, UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RV vertex;
  private VT type;

  protected UniProtEnzymeDBVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniProtEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniProtEnzymeDBVertexType<
    V extends UniProtEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.UniProtEnzymeDBVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RVT raw;

  protected UniProtEnzymeDBVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniProtEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtEnzymeDBGraph.this;
  }
  }

  public abstract static class UniProtEnzymeDBEdge<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtEnzymeDBEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
    ET extends UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.UniProtEnzymeDBEdgeType<S,ST,SG, E,ET, T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedEdge<
      S, ST, SG,
      E, ET, UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      >
  {

  private RE edge;
  private ET type;

  protected UniProtEnzymeDBEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public UniProtEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniProtEnzymeDBEdgeType<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtEnzymeDBEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
    ET extends UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>.UniProtEnzymeDBEdgeType<S,ST,SG, E,ET, T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
    >
    implements
    TypedEdge.Type<
      S, ST, SG,
      E, ET, UniProtEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      >
  {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected UniProtEnzymeDBEdgeType(ST srcT, RET raw, TT tgtT) {

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
  public final UniProtEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtEnzymeDBGraph.this;
  }
  }
}
