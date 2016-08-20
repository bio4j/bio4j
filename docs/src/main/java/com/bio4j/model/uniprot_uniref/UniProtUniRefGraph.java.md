
```java
package com.bio4j.model.uniprot_uniref;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniref.vertices.*;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.angulillos.*;
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




[main/java/com/bio4j/model/enzymedb/ENZYMEGraph.java]: ../enzymedb/ENZYMEGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/go/GOGraph.java]: ../go/GOGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/unigene/UniGeneGraph.java]: ../unigene/UniGeneGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../uniprot/UniProtGraph.java.md