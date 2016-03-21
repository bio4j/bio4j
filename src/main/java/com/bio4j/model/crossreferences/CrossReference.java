package com.bio4j.model.crossreferences;

import com.bio4j.angulillos.*;

/*
  A graph which has only one edge, linking

  1. `SrcVtx` from one `SrcGph` to
  2. `TgtVtx` from `TgtGph`
*/
public abstract class CrossReference <
    SrcVtx    extends TypedVertex<SrcVtx,SrcVtxT,SrcGph,I,RV,RVT,RE,RET>,
    SrcVtxT   extends TypedVertex.Type<SrcVtx,SrcVtxT,SrcGph,I,RV,RVT,RE,RET>,
    TgtVtx    extends TypedVertex<TgtVtx,TgtVtxT,TgtGph,I,RV,RVT,RE,RET>,
    TgtVtxT   extends TypedVertex.Type<TgtVtx,TgtVtxT,TgtGph,I,RV,RVT,RE,RET>,

    CRef      extends CrossReference<SrcVtx,SrcVtxT,TgtVtx,TgtVtxT,CRef,SrcGph,TgtGph,I,RV,RVT,RE,RET>,
    SrcGph    extends TypedGraph<SrcGph,I,RV,RVT,RE,RET>,
    TgtGph    extends TypedGraph<TgtGph,I,RV,RVT,RE,RET>,
    // untyped graph
    I         extends UntypedGraph<RV,RVT,RE,RET>,
    // vertices
    RV, RVT,
    // edges
    RE, RET
  >
    implements
      TypedGraph<CRef,I,RV,RVT,RE,RET>
{

  private final I raw;
  private final SrcVtxT sourceVertexType;
  private final TgtVtxT targetVertexType;
  private final TypedEdge.Type.Arity arity;

  public CrossReference(
    SrcVtxT sourceVertexType,
    TypedEdge.Type.Arity arity, I raw,
    TgtVtxT targetVertexType
  )
  {
    this.sourceVertexType = sourceVertexType;
    this.raw              = raw;
    this.arity            = arity;
    this.targetVertexType = targetVertexType;
  }

  public TypedEdge.Type.Arity arity() { return arity; }
  public abstract LinkedToType LinkedTo();
  public abstract CRef self();

  public SrcGph sourceGraph() { return sourceVertexType().graph(); }
  public TgtGph targetGraph() { return targetVertexType().graph(); }

  public final SrcVtxT sourceVertexType() { return sourceVertexType; }
  public final TgtVtxT targetVertexType() { return targetVertexType; }

  public final I raw() { return raw; }

  public final class LinkedTo
    implements
      TypedEdge <
        SrcVtx,SrcVtxT,SrcGph,
        LinkedTo,LinkedToType,CRef,I,RV,RVT,RE,RET,
        TgtVtx,TgtVtxT,TgtGph
      >
  {
    private final RE raw;
    private final LinkedToType type;

    LinkedTo(RE raw, LinkedToType type) { this.raw = raw; this.type = type; }

    public final RE           raw()   { return raw;  }
    public final LinkedToType type()  { return type; }
    public final LinkedTo     self()  { return this; }
    public final CRef         graph() { return CrossReference.this.self(); }
  }

  public final class LinkedToType
    implements
      TypedEdge.Type <
        SrcVtx,SrcVtxT,SrcGph,
        LinkedTo,LinkedToType,CRef,I,RV,RVT,RE,RET,
        TgtVtx,TgtVtxT,TgtGph
      >
  {
    private final RET raw;
    public LinkedToType(RET raw) { this.raw = raw; }

    public final RET          raw()   { return raw;                         }
    public final CRef         graph() { return CrossReference.this.self();  }
    public final LinkedToType value() { return this;                        }
    public final TypedEdge.Type.Arity arity() { return CrossReference.this.arity(); }

    public final SrcVtxT sourceType() { return CrossReference.this.sourceVertexType(); }
    public final TgtVtxT targetType() { return CrossReference.this.targetVertexType(); }

    public LinkedTo from(RE raw) { return new LinkedTo(raw, this); }
  }
}
