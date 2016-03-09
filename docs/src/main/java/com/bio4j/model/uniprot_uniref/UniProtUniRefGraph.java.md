
```java
package com.bio4j.model.uniprot_uniref;


import com.bio4j.angulillos.*;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
```



# UniProt UniRef graph

This graph includes the connection between Proteins and UniRef clusters (50/90/100) modelled via the following relationships.

## data model

### UniRef50Member

Connecting `Protein` and `UniRef50Cluster` vertices, it links a protein and the UniRef 50 cluster to which it belongs.

### UniRef50Representant

Connecting `Protein` and `UniRef50Cluster` vertices, it links a protein and the UniRef 50 cluster it's the representant of.

### UniRef90Member

Connecting `Protein` and `UniRef90Cluster` vertices, it links a protein and the UniRef 90 cluster to which it belongs.

### UniRef90Representant

Connecting `Protein` and `UniRef90Cluster` vertices, it links a protein and the UniRef 90 cluster it's the representant of.

### UniRef100Member

Connecting `Protein` and `UniRef100Cluster` vertices, it links a protein and the UniRef 100 cluster to which it belongs.

### UniRef100Representant

Connecting `Protein` and `UniRef100Cluster` vertices, it links a protein and the UniRef 100 cluster it's the representant of.



```java
public abstract class UniProtUniRefGraph<
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  implements
  TypedGraph<
    UniProtUniRefGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    > {

  protected I raw = null;

  public UniProtUniRefGraph(I graph) {
  raw = graph;
  }

  public I raw() {
  return raw;
  }

  public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();

  public abstract UniRefGraph<I, RV, RVT, RE, RET> uniRefGraph();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // edges

  public abstract UniRef50MemberType UniRef50Member();

  public abstract UniRef90MemberType UniRef90Member();

  public abstract UniRef100MemberType UniRef100Member();

  public abstract UniRef100RepresentantType UniRef100Representant();

  public abstract UniRef90RepresentantType UniRef90Representant();

  public abstract UniRef50RepresentantType UniRef50Representant();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types
  public final class UniRef50MemberType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef50Member<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef50MemberType,
      // tgt
      UniRef50Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.ManyToOne {

  public UniRef50MemberType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef50Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef50MemberType value() {
    return graph().UniRef50Member();
  }

  @Override
  public UniRef50Member<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef50Member<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef50Member<I, RV, RVT, RE, RET>, UniRef50MemberType,
      // property
      proteinAccession, String,
      // tgt
      UniRef50Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef50MemberType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  public final class UniRef90MemberType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef90Member<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef90MemberType,
      // tgt
      UniRef90Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.ManyToOne {

  public UniRef90MemberType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef90Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef90MemberType value() {
    return graph().UniRef90Member();
  }

  @Override
  public UniRef90Member<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef90Member<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef90Member<I, RV, RVT, RE, RET>, UniRef90MemberType,
      // property
      proteinAccession, String,
      // tgt
      UniRef90Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef90MemberType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  public final class UniRef100MemberType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef100Member<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef100MemberType,
      // tgt
      UniRef100Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.ManyToOne {

  public UniRef100MemberType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef100Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef100MemberType value() {
    return graph().UniRef100Member();
  }

  @Override
  public UniRef100Member<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef100Member<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef100Member<I, RV, RVT, RE, RET>, UniRef100MemberType,
      // property
      proteinAccession, String,
      // tgt
      UniRef100Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef100MemberType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  public final class UniRef100RepresentantType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef100Representant<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef100RepresentantType,
      // tgt
      UniRef100Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.OneToOne {

  public UniRef100RepresentantType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef100Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef100RepresentantType value() {
    return graph().UniRef100Representant();
  }

  @Override
  public UniRef100Representant<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef100Representant<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef100Representant<I, RV, RVT, RE, RET>, UniRef100RepresentantType,
      // property
      proteinAccession, String,
      // tgt
      UniRef100Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef100RepresentantType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  public final class UniRef50RepresentantType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef50Representant<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef50RepresentantType,
      // tgt
      UniRef50Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.OneToOne {

  public UniRef50RepresentantType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef50Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef50RepresentantType value() {
    return graph().UniRef50Representant();
  }

  @Override
  public UniRef50Representant<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef50Representant<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef50Representant<I, RV, RVT, RE, RET>, UniRef50RepresentantType,
      // property
      proteinAccession, String,
      // tgt
      UniRef50Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef50RepresentantType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  public final class UniRef90RepresentantType
    extends
    UniProtUniRefEdgeType<
      // src
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef90Representant<I, RV, RVT, RE, RET>,
      UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniRef90RepresentantType,
      // tgt
      UniRef90Cluster<I, RV, RVT, RE, RET>,
      UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
      UniRefGraph<I, RV, RVT, RE, RET>
      >
    implements
    TypedEdge.Type.OneToOne {

  public UniRef90RepresentantType(RET raw) {

    super(
      UniProtUniRefGraph.this.uniProtGraph().Protein(),
      raw,
      UniProtUniRefGraph.this.uniRefGraph().UniRef90Cluster()
    );
  }

  public final proteinAccession proteinAccession = new proteinAccession();

  @Override
  public UniRef90RepresentantType value() {
    return graph().UniRef90Representant();
  }

  @Override
  public UniRef90Representant<I, RV, RVT, RE, RET> from(RE edge) {
    return new UniRef90Representant<I, RV, RVT, RE, RET>(edge, this);
  }

  public final class proteinAccession
    extends
    UniProtUniRefEdgeProperty<
      // src
      Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType, UniProtGraph<I, RV, RVT, RE, RET>,
      // edge
      UniRef90Representant<I, RV, RVT, RE, RET>, UniRef90RepresentantType,
      // property
      proteinAccession, String,
      // tgt
      UniRef90Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType, UniRefGraph<I, RV, RVT, RE, RET>
      > {
    public proteinAccession() {
    super(UniRef90RepresentantType.this);
    }

    public Class<String> valueClass() {
    return String.class;
    }
  }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes
  // TODO: add a Bio4j base vertex and edge interface
  public abstract class UniProtUniRefEdgeProperty<
    // src vertex and graph
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtUniRefEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
    ET extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefEdgeType<S, ST, SG, E, ET, T, TT, TG>,
    // property
    P extends UniProtUniRefEdgeProperty<S, ST, SG, E, ET, P, PV, T, TT, TG>,
    PV,
    // tgt vertex and graph
    T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
    >
    implements
    Property<E, ET, P, PV, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniProtUniRefEdgeProperty(ET type) {

    this.type = type;
  }

  private ET type;

  @Override
  public final ET elementType() {
    return type;
  }
  }


  public abstract class UniProtUniRefVertexProperty<
    V extends UniProtUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefVertexType<V, VT>,
    P extends UniProtUniRefVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniProtUniRefVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class UniProtUniRefVertex<
    V extends UniProtUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefVertexType<V, VT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedVertex<V, VT, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RV vertex;
  private VT type;

  protected UniProtUniRefVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniProtUniRefGraph<I, RV, RVT, RE, RET> graph() {
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

  public abstract class UniProtUniRefVertexType<
    V extends UniProtUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RVT raw;

  protected UniProtUniRefVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniProtUniRefGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtUniRefGraph.this;
  }
  }

  public abstract static class UniProtUniRefEdge<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtUniRefEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
    ET extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefEdgeType<S, ST, SG, E, ET, T, TT, TG>,
    // tgt
    T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedEdge<
      S, ST, SG,
      E, ET, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      > {

  private RE edge;
  private ET type;

  protected UniProtUniRefEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public UniProtUniRefGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniProtUniRefEdgeType<
    // src
    S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
    ST extends TypedVertex.Type<S, ST, SG, I, RV, RVT, RE, RET>,
    SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
    // edge
    E extends UniProtUniRefEdge<S, ST, SG, E, ET, T, TT, TG, I, RV, RVT, RE, RET>,
    ET extends UniProtUniRefGraph<I, RV, RVT, RE, RET>.UniProtUniRefEdgeType<S, ST, SG, E, ET, T, TT, TG>,
    // tgt
    T extends TypedVertex<T, TT, TG, I, RV, RVT, RE, RET>,
    TT extends TypedVertex.Type<T, TT, TG, I, RV, RVT, RE, RET>,
    TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
    >
    implements
    TypedEdge.Type<
      S, ST, SG,
      E, ET, UniProtUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TG
      > {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected UniProtUniRefEdgeType(ST srcT, RET raw, TT tgtT) {

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
  public final UniProtUniRefGraph<I, RV, RVT, RE, RET> graph() {
    return UniProtUniRefGraph.this;
  }
  }
}

```




[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]: ../go/edges/goSlims/PlantSlim.java.md
[main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]: ../go/edges/goSlims/GoSlim.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../go/GoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../uniprot/UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../uniprot/vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../uniprot/vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../uniprot/vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../uniprot/vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../uniprot/vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../uniprot/vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../uniprot/vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../uniprot/vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../uniprot/vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../uniprot/vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../uniprot/vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../uniprot/vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../uniprot/vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../uniprot/vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../uniprot/vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../uniprot/vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../uniprot/vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../uniprot/vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../uniprot/vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../uniprot/vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../uniprot/vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../uniprot/vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../uniprot/vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../uniprot/vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../uniprot/vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../uniprot/vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../uniprot/vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../uniprot/vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../uniprot/vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../uniprot/vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../uniprot/vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../uniprot/vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../uniprot/vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../uniprot/vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../uniprot/vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../uniprot/vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../uniprot/vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../uniprot/vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../uniprot/vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../uniprot/vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../uniprot/vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../uniprot/programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ../uniprot/programs/ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../uniprot/programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ../uniprot/programs/ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ../uniprot/programs/ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../uniprot/edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../uniprot/edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../uniprot/edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../uniprot/edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../uniprot/edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../uniprot/edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../uniprot/edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../uniprot/edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../uniprot/edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../uniprot/edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../uniprot/edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../uniprot/edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../uniprot/edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../uniprot/edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../uniprot/edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../uniprot/edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../uniprot/edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../uniprot/edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../uniprot/edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../uniprot/edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../uniprot/edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../uniprot/edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../uniprot/edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../uniprot/edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../uniprot/edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../uniprot/edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../uniprot/edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../uniprot/edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../uniprot/edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../uniprot/edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../uniprot/edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../uniprot/edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../uniprot/edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../uniprot/edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../uniprot/edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../uniprot/edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../uniprot/edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../uniprot/edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../uniprot/edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../uniprot/edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../uniprot/edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../uniprot/edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../uniprot/edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../uniprot/edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../uniprot/edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../uniprot/edges/ProteinGeneLocation.java.md