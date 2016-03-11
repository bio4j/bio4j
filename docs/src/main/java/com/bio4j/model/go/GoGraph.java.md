
```java
package com.bio4j.model.go;

import com.bio4j.model.go.vertices.GoSlims;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.model.go.edges.*;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.angulillos.*;
```



# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Basically there are `GoTerm` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.

### GoTerms

We have a `GoTerm` vertex which contains property data present for each term. Do note though that some of these properties are represented as edges.

##### [Essential elements](http://www.geneontology.org/GO.ontology.structure.shtml#essential)

- `id` property of the `GoTerm` rel
- `name` property of the `GoTerm` rel
- `definition` property of the `GoTerm` rel

The `namespace` is represented by relationships (one type per namespace) going out of the term node. There are three of them:

- cellular component
- biological process
- molecular function

##### [Optional extras](http://www.geneontology.org/GO.ontology.structure.shtml#opt)

- `secondary_ids` We drop this. It is just legacy data with no meaning.
- `synonyms` They are split into
+ `exact`
+ `broad`
+ `narrow`
+ `related`

We drop **all** of them **but** `exact`, and add an index over it.
- `cross_ref` an array of strings, property of the `GoTerm` rel. _TODO is this connected with the corresponding DBs?_
- `comment` a standard text field.
- `subset` an array of strings. Each of them corresponds to a particular GoSlim. As with namespaces, this is modeled as relations going from each term node to a `GoSlims` node. See [GO Slim](http://www.geneontology.org/GO.slims.shtml).
- `obsolete` GoTerms marked as obsolete are **dropped**.

### GO Relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

- is a
- part of
- has part of
- regulates
- negatively regulates 
- positively regulates



```java
public abstract class GoGraph <
  I extends UntypedGraph<RV,RVT,RE,RET>,
  RV,RVT,
  RE,RET
>
implements
  TypedGraph <
  GoGraph<I,RV,RVT,RE,RET>,
  I,RV,RVT,RE,RET
  >
{

  protected I raw = null;
  public GoGraph(I graph) { raw = graph; }

  public I raw() { return raw; }

  public abstract UniProtGoGraph<I,RV,RVT,RE,RET> uniProtGoGraph();

  public abstract TypedVertexIndex.Unique <
  GoTerm<I,RV,RVT,RE,RET>,GoTermType, 
  GoTermType.id, String, 
  GoGraph<I,RV,RVT,RE,RET>, 
  I,RV,RVT,RE,RET
  >  
  goTermIdIndex();

  public abstract TypedVertexIndex.Unique <
  SubOntologies<I,RV,RVT,RE,RET>, SubOntologiesType,
  SubOntologiesType.name, String, 
  GoGraph<I,RV,RVT,RE,RET>, 
  I,RV,RVT,RE,RET
  >
  subontologiesNameIndex();

  // types
  // vertices
  public abstract GoSlimsType GoSlims();
  public abstract GoTermType GoTerm();
  public abstract SubOntologiesType SubOntologies();
  // edges
  public abstract PartOfType PartOf();
  public abstract HasPartOfType HasPartOf();
  public abstract NegativelyRegulatesType NegativelyRegulates();
  public abstract PositivelyRegulatesType PositivelyRegulates();
  public abstract RegulatesType Regulates();
  public abstract IsAType IsA();
  public abstract SubOntologyType SubOntology();


  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Vertex types

  public final class GoSlimsType
  extends
  GoVertexType <
    GoSlims<I,RV,RVT,RE,RET>,
    GoGraph<I,RV,RVT,RE,RET>.GoSlimsType
  >
  {

  public GoSlimsType(RVT raw) { super(raw); }

  @Override
  public GoSlimsType value() { return graph().GoSlims(); }

  @Override
  public GoSlims<I,RV,RVT,RE,RET> from(RV vertex) { return new GoSlims<I,RV,RVT,RE,RET>(vertex, this); }
  }

  public final class GoTermType
  extends
  GoVertexType<
    GoTerm<I,RV,RVT,RE,RET>,
    GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  {

  public final id id = new id();
  public final name name = new name();
  public final comment comment = new comment();
  public final definition definition = new definition();
  public final obsolete obsolete = new obsolete();
  public final synonym synonym = new synonym();

  public GoTermType(RVT raw) { super(raw); }

  @Override
  public GoTermType value() { return graph().GoTerm(); }

  @Override
  public GoTerm<I,RV,RVT,RE,RET> from(RV vertex) { return new GoTerm<I,RV,RVT,RE,RET>(vertex, this); }

  public final class id
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,id,String>
  {  
    public id() { super(GoTermType.this); }
    public final Class<String> valueClass() { return String.class; }
  }

  public final class name
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,name,String>
  {  
    public name() { super(GoTermType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class definition
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,definition,String>
  {   
    public definition() { super(GoTermType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class comment
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,comment,String>
  {
    public comment() { super(GoTermType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class obsolete
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,obsolete,String>
  {
    public obsolete() { super(GoTermType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class synonym
  extends
    GoVertexProperty<GoTerm<I,RV,RVT,RE,RET>,GoTermType,synonym,String>
  {  
    public synonym() { super(GoTermType.this); }
    public Class<String> valueClass() { return String.class; }
  }
  }

  public final class SubOntologiesType
  extends
  GoVertexType<
    SubOntologies<I,RV,RVT,RE,RET>,
    GoGraph<I,RV,RVT,RE,RET>.SubOntologiesType
  >
  {
  public final name name = new name();

  public SubOntologiesType(RVT raw) { super(raw); }

  @Override
  public SubOntologiesType value() { return graph().SubOntologies(); }

  @Override
  public SubOntologies<I,RV,RVT,RE,RET> from(RV vertex) {

    return new SubOntologies<I,RV,RVT,RE,RET>(vertex, this);
  }

  public final class name
  extends
    GoVertexProperty<SubOntologies<I,RV,RVT,RE,RET>,SubOntologiesType,name,String>
  {
    public name() { super(SubOntologiesType.this); }
    public Class<String> valueClass() { return String.class; }
  }
  }


  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types

  public final class PartOfType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    PartOf<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.PartOfType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public PartOfType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public PartOfType value() { return graph().PartOf(); }

  @Override
  public PartOf<I,RV,RVT,RE,RET> from(RE edge) { return new PartOf<I,RV,RVT,RE,RET>(edge, this); }
  }

  public final class HasPartOfType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    HasPartOf<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.HasPartOfType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {
  
  public HasPartOfType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public HasPartOfType value() { return graph().HasPartOf(); }

  @Override
  public HasPartOf<I,RV,RVT,RE,RET> from(RE edge) { return new HasPartOf<I,RV,RVT,RE,RET>(edge, this); }
  }

  public final class IsAType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    IsA<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.IsAType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public IsAType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public IsAType value() { return graph().IsA(); }

  @Override
  public IsA<I,RV,RVT,RE,RET> from(RE edge) { return new IsA<I,RV,RVT,RE,RET>(edge, this); }
  }

  public final class NegativelyRegulatesType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    NegativelyRegulates<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.NegativelyRegulatesType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public NegativelyRegulatesType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public NegativelyRegulatesType value() { return graph().NegativelyRegulates(); }

  @Override
  public NegativelyRegulates<I,RV,RVT,RE,RET> from(RE edge) { 

    return new NegativelyRegulates<I,RV,RVT,RE,RET>(edge, this);
  }
  }

  public final class PositivelyRegulatesType
  extends
  GoEdgeType<
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    PositivelyRegulates<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.PositivelyRegulatesType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public PositivelyRegulatesType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public final PositivelyRegulatesType value() { return graph().PositivelyRegulates(); }

  @Override
  public final PositivelyRegulates<I,RV,RVT,RE,RET> from(RE edge) { 

    return new PositivelyRegulates<I,RV,RVT,RE,RET>(edge, this);
  }
  }

  public final class RegulatesType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    Regulates<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.RegulatesType,
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType
  >
  implements
  TypedEdge.Type.ManyToMany
  {

  public RegulatesType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm()); }

  @Override
  public final RegulatesType value() { return graph().Regulates(); }

  @Override
  public final Regulates<I,RV,RVT,RE,RET> from(RE edge) { 

    return new Regulates<I,RV,RVT,RE,RET>(edge, this);
  }
  }

  public final class SubOntologyType
  extends
  GoEdgeType <
    GoTerm<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.GoTermType,
    SubOntology<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.SubOntologyType,
    SubOntologies<I,RV,RVT,RE,RET>,GoGraph<I,RV,RVT,RE,RET>.SubOntologiesType
  >
  implements
  TypedEdge.Type.ManyToOne
  {

  public SubOntologyType(RET raw) { super(GoGraph.this.GoTerm(), raw, GoGraph.this.SubOntologies()); }

  @Override
  public SubOntologyType value() { return graph().SubOntology(); }

  @Override
  public SubOntology<I,RV,RVT,RE,RET> from(RE edge) {

    return new SubOntology<I,RV,RVT,RE,RET>(edge, this);
  }
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class GoVertexProperty<
    V extends GoVertex<V, VT, I,RV,RVT,RE,RET>,
    VT extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<V, VT>,
    P extends GoVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET> {

  protected GoVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class GoVertex<
    V extends GoVertex<V, VT, I,RV,RVT,RE,RET>,
    VT extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<V, VT>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
    >
    implements
    TypedVertex<V, VT, GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET> {

  private RV vertex;
  private VT type;

  protected GoVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public GoGraph<I,RV,RVT,RE,RET> graph() {
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

  abstract class GoVertexType<
    V extends GoVertex<V, VT, I,RV,RVT,RE,RET>,
    VT extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET> {

  private RVT raw;

  protected GoVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final GoGraph<I,RV,RVT,RE,RET> graph() {
    return GoGraph.this;
  }
  }

  public abstract static class GoEdge<
    S extends GoVertex<S, ST, I,RV,RVT,RE,RET>,
    ST extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<S, ST>,
    E extends GoEdge<S, ST, E, ET, T, TT, I,RV,RVT,RE,RET>,
    ET extends GoGraph<I,RV,RVT,RE,RET>.GoEdgeType<S, ST, E, ET, T, TT>,
    T extends GoVertex<T, TT, I,RV,RVT,RE,RET>,
    TT extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<T, TT>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
    >
    implements
    TypedEdge<
      S, ST, GoGraph<I,RV,RVT,RE,RET>,
      E, ET, GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET,
      T, TT, GoGraph<I,RV,RVT,RE,RET>
      > {

  private RE edge;
  private ET type;

  protected GoEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public GoGraph<I,RV,RVT,RE,RET> graph() {
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

  abstract class GoEdgeType<
    S extends GoVertex<S, ST, I,RV,RVT,RE,RET>,
    ST extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<S, ST>,
    E extends GoEdge<S, ST, E, ET, T, TT, I,RV,RVT,RE,RET>,
    ET extends GoGraph<I,RV,RVT,RE,RET>.GoEdgeType<S, ST, E, ET, T, TT>,
    T extends GoVertex<T, TT, I,RV,RVT,RE,RET>,
    TT extends GoGraph<I,RV,RVT,RE,RET>.GoVertexType<T, TT>
    >
    implements
    TypedEdge.Type<
      S, ST, GoGraph<I,RV,RVT,RE,RET>,
      E, ET, GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET,
      T, TT, GoGraph<I,RV,RVT,RE,RET>
      > {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected GoEdgeType(ST srcT, RET raw, TT tgtT) {

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
  public final GoGraph<I,RV,RVT,RE,RET> graph() {
    return GoGraph.this;
  }
  }
}

```




[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: programs/ImportGO.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: edges/SubOntology.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: edges/PartOf.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: GoGraph.java.md
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
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: ../uniprot/programs/XMLConstants.java.md
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