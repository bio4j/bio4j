package com.bio4j.model;

import com.bio4j.angulillos.*;
import java.util.Set;

// TODO move to angulillos
public abstract class LinkGraph<
  G extends LinkGraph<G,RV,RE>,
  RV,RE
>
extends TypedGraph<G,RV,RE> {

  protected LinkGraph(UntypedGraph<RV,RE> raw) { super(raw); }

  // TODO these two classes are duplicated here because of them being private in angulillos
  private abstract class FunnyElement<
    F  extends     FunnyElement<F,FT, RF>,
    FT extends FunnyElementType<F,FT, RF>,
    RF
  > implements TypedElement<F,FT, G,RF> {

    private final RF raw;
    private final FT type;

    @Override public final RF raw()  { return this.raw; }
    @Override public final FT type() { return this.type; }

    // NOTE: we cannot do the same to `self`, because `super()` constructor cannot refer to `this`
    protected FunnyElement(RF raw, FT type) {
      this.raw  = raw;
      this.type = type;
    }
  }

  private abstract class FunnyElementType<
    F  extends     FunnyElement<F,FT, RF>,
    FT extends FunnyElementType<F,FT, RF>,
    RF
  > implements TypedElement.Type<F,FT, G,RF> {

    @Override public final G graph() { return LinkGraph.this.self(); }

    public abstract F fromRaw(RF raw);

    protected abstract FT self();

    /* This set stores all properties that are defined on this element type */
    private final Set<AnyProperty> properties = new java.util.HashSet<>();
    public  final Set<AnyProperty> properties() { return this.properties; }


    // public abstract class Property<X>
    // implements com.bio4j.angulillos.Property<FT,X> {
    //   // NOTE: this initializer block will be inherited and will add each vertex type to the set
    //   {
    //     if (
    //       ElementType.this.properties.removeIf( (AnyProperty p) ->
    //         p._label().equals( this._label() )
    //       )
    //     ) {
    //       throw new IllegalArgumentException(
    //         "Element type [" +
    //         ElementType.this._label() +
    //         "] contains duplicate property: " +
    //         this._label()
    //       );
    //     }
    //     ElementType.this.properties.add(this);
    //   }
    //
    //   private final Class<X> valueClass;
    //
    //   @Override public final FT elementType() { return self(); }
    //   @Override public final Class<X> valueClass() { return this.valueClass; }
    //
    //   protected Property(Class<X> valueClass) {
    //     this.valueClass  = valueClass;
    //   }
    // }
  }


  public abstract class LinkEdge<
    SG extends TypedGraph<SG,RV,RE>,
    S extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E extends LinkEdge<SG,S,E,TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends FunnyElement<E,LinkEdgeType<SG,S,E,TG,T>,RE>
    implements TypedEdge<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, LinkEdgeType<SG,S,E,TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
    >
  {
    protected LinkEdge(RE raw, LinkEdgeType<SG,S,E,TG,T> type) { super(raw, type); }
  }

  // TODO initializer block missing
  public abstract class LinkEdgeType<
    SG extends TypedGraph<SG,RV,RE>,
    S extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E extends LinkEdge<SG,S,E,TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends FunnyElementType<E, LinkEdgeType<SG,S,E,TG,T>, RE>
    implements TypedEdge.Type<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, LinkEdgeType<SG,S,E,TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
  >
  {

    protected LinkEdgeType<SG,S,E,TG,T> self() { return this; }

    private final TypedGraph<SG,RV,RE>.VertexType<S> sourceType;
    private final TypedGraph<TG,RV,RE>.VertexType<T> targetType;

    @Override public final TypedGraph<SG,RV,RE>.VertexType<S> sourceType() { return this.sourceType; }
    @Override public final TypedGraph<TG,RV,RE>.VertexType<T> targetType() { return this.targetType; }

    protected LinkEdgeType(TypedGraph<SG,RV,RE>.VertexType<S> sourceType, TypedGraph<TG,RV,RE>.VertexType<T> targetType) {

      this.sourceType = sourceType;
      this.targetType = targetType;

      // private access in angulillos
      // sourceType.outEdges.add( self() );
      // targetType.inEdges.add( self() );
    }
  }
}
