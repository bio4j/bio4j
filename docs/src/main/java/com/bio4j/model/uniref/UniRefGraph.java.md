
```java
package com.bio4j.model.uniref;

import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.*;
```



# UniRef graph

This graph includes virtually all data from UniRef clusters including 50, 90 and 100.
You can check more information about UniRef data [here](http://www.uniprot.org/help/uniref)

## data model

It only consists of three different vertex types, one for each type of UniRefCluster:

- `UniRef50Cluster`
- `UniRef90Cluster`
- `UniRef100Cluster`


##### UniRef clusters' properties stored

- `id`
- `name`
- `updatedDate`

_There no specific property for any of the three different types of cluster_



```java
public abstract class UniRefGraph<
  // untyped graph
  I extends UntypedGraph<RV, RVT, RE, RET>,
  // vertices
  RV, RVT,
  // edges
  RE, RET
  >
  implements
  TypedGraph<
    UniRefGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    > {

  protected I raw = null;

  public UniRefGraph(I graph){
    raw = graph;
  }

  public I raw(){
    return raw;
  }

  public abstract UniProtUniRefGraph<I, RV, RVT, RE, RET> uniProtUniRefGraph();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // indices
  public abstract TypedVertexIndex.Unique <
    // vertex
    UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType,
    // property
    UniRef50ClusterType.id, String,
    // graph
    UniRefGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    >
  uniRef50ClusterIdIndex();
  public abstract TypedVertexIndex.Unique <
    // vertex
    UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType,
    // property
    UniRef90ClusterType.id, String,
    // graph
    UniRefGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    >
  uniRef90ClusterIdIndex();
  public abstract TypedVertexIndex.Unique <
    // vertex
    UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType,
    // property
    UniRef100ClusterType.id, String,
    // graph
    UniRefGraph<I, RV, RVT, RE, RET>,
    I, RV, RVT, RE, RET
    >
  uniRef100ClusterIdIndex();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // types
  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // vertices
  public abstract UniRef50ClusterType UniRef50Cluster();

  public abstract UniRef90ClusterType UniRef90Cluster();

  public abstract UniRef100ClusterType UniRef100Cluster();



  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Vertex types

  public final class UniRef50ClusterType
  extends
    UniRefVertexType<
    UniRef50Cluster<I, RV, RVT, RE, RET>,
    UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType
    >
  {

  public final id id = new id();
  public final name name = new name();
  public final updatedDate updatedDate = new updatedDate();
  public final representantAccession representantAccession = new representantAccession();
  public final members members = new members();

  public UniRef50ClusterType(RVT raw) { super(raw); }

  @Override
  public UniRef50ClusterType value() { return graph().UniRef50Cluster(); }

  @Override
  public UniRef50Cluster<I, RV, RVT, RE, RET> from(RV vertex) {
    return new UniRef50Cluster<I, RV, RVT, RE, RET>(vertex, this);
  }

  public final class id
    extends
    UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, id, String>
  {
    public id() { super(UniRef50ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class name
    extends
    UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, name, String> 
  {  
    public name() { super(UniRef50ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class updatedDate
    extends
    UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, updatedDate, String>
  {
    public updatedDate() { super(UniRef50ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class representantAccession
    extends
    UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, representantAccession, String>
  {
    public representantAccession() { super(UniRef50ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class members
    extends
    UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, members, String[]>
  {
    public members() { super(UniRef50ClusterType.this); }
    public Class<String[]> valueClass() { return String[].class; }
  }
  }

  public final class UniRef90ClusterType
  extends
    UniRefVertexType<
    UniRef90Cluster<I, RV, RVT, RE, RET>,
    UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType
    >
  {

  public final id id = new id();
  public final name name = new name();
  public final updatedDate updatedDate = new updatedDate();
  public final representantAccession representantAccession = new representantAccession();
  public final members members = new members();

  public UniRef90ClusterType(RVT raw) { super(raw); }

  @Override
  public UniRef90ClusterType value() { return graph().UniRef90Cluster(); }

  @Override
  public UniRef90Cluster<I, RV, RVT, RE, RET> from(RV vertex) { 

    return new UniRef90Cluster<I, RV, RVT, RE, RET>(vertex, this);
  }

  public final class id
    extends
    UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, id, String> 
  {

    public id() { super(UniRef90ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class name
    extends
    UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, name, String> 
  {
    public name() { super(UniRef90ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class updatedDate
    extends
    UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, updatedDate, String> 
  {
    public updatedDate() { super(UniRef90ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class representantAccession
    extends
    UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, representantAccession, String>
  {
    public representantAccession() { super(UniRef90ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class members
    extends
    UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, members, String[]>
  {
    public members() { super(UniRef90ClusterType.this); }
    public Class<String[]> valueClass() { return String[].class; }
  }
  }

  public final class UniRef100ClusterType
  extends
    UniRefVertexType<
    UniRef100Cluster<I, RV, RVT, RE, RET>,
    UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType
    >
  {
  public final id id = new id();
  public final name name = new name();
  public final updatedDate updatedDate = new updatedDate();
  public final representantAccession representantAccession = new representantAccession();
  public final members members = new members();

  public UniRef100ClusterType(RVT raw) { super(raw); }

  @Override
  public UniRef100ClusterType value() { return graph().UniRef100Cluster(); }

  @Override
  public UniRef100Cluster<I, RV, RVT, RE, RET> from(RV vertex) {

    return new UniRef100Cluster<I, RV, RVT, RE, RET>(vertex, this);
  }

  public final class id
    extends
    UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, id, String>
  {
    public id() { super(UniRef100ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class name
    extends
    UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, name, String> 
  {
    public name() { super(UniRef100ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class updatedDate
    extends
    UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, updatedDate, String>
  {  
    public updatedDate() { super(UniRef100ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class representantAccession
    extends
    UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, representantAccession, String>
  {
    public representantAccession() { super(UniRef100ClusterType.this); }
    public Class<String> valueClass() { return String.class; }
  }

  public final class members
    extends
    UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, members, String[]>
  {
    public members() { super(UniRef100ClusterType.this); }
    public Class<String[]> valueClass() { return String[].class; }
  }
  }



  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class UniRefVertexProperty<
    V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
    P extends UniRefVertexProperty<V, VT, P, PV>,
    PV
    >
    implements
    Property<V, VT, P, PV, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  protected UniRefVertexProperty(VT type) {

    this.type = type;
  }

  private VT type;

  @Override
  public final VT elementType() {
    return type;
  }
  }

  public abstract static class UniRefVertex<
    V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedVertex<V, VT, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RV vertex;
  private VT type;

  protected UniRefVertex(RV vertex, VT type) {

    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public UniRefGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniRefVertexType<
    V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>
    >
    implements
    TypedVertex.Type<V, VT, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

  private RVT raw;

  protected UniRefVertexType(RVT raw) {
    this.raw = raw;
  }

  @Override
  public final RVT raw() {
    return raw;
  }

  @Override
  public final UniRefGraph<I, RV, RVT, RE, RET> graph() {
    return UniRefGraph.this;
  }
  }

  public abstract static class UniRefEdge<
    S extends UniRefVertex<S, ST, I, RV, RVT, RE, RET>,
    ST extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
    E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
    ET extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
    T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
    TT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>,
    I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
    >
    implements
    TypedEdge<
      S, ST, UniRefGraph<I, RV, RVT, RE, RET>,
      E, ET, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, UniRefGraph<I, RV, RVT, RE, RET>
      > {

  private RE edge;
  private ET type;

  protected UniRefEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public UniRefGraph<I, RV, RVT, RE, RET> graph() {
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

  abstract class UniRefEdgeType<
    S extends UniRefVertex<S, ST, I, RV, RVT, RE, RET>,
    ST extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
    E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
    ET extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
    T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
    TT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>
    >
    implements
    TypedEdge.Type<
      S, ST, UniRefGraph<I, RV, RVT, RE, RET>,
      E, ET, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, UniRefGraph<I, RV, RVT, RE, RET>
      > {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected UniRefEdgeType(ST srcT, RET raw, TT tgtT) {

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
  public final UniRefGraph<I, RV, RVT, RE, RET> graph() {
    return UniRefGraph.this;
  }
  }


}

```




[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]: ../go/edges/goSlims/GoSlim.java.md
[main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]: ../go/edges/goSlims/PlantSlim.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../go/GoGraph.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../uniprot/edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../uniprot/edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../uniprot/edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../uniprot/edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../uniprot/edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../uniprot/edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../uniprot/edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../uniprot/edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../uniprot/edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../uniprot/edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../uniprot/edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../uniprot/edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../uniprot/edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../uniprot/edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../uniprot/edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../uniprot/edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../uniprot/edges/ProteinGeneLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../uniprot/edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../uniprot/edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../uniprot/edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../uniprot/edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../uniprot/edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../uniprot/edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../uniprot/edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../uniprot/edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../uniprot/edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../uniprot/edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../uniprot/edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../uniprot/edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../uniprot/edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../uniprot/edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../uniprot/edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../uniprot/edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../uniprot/edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../uniprot/edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../uniprot/edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../uniprot/edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../uniprot/edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../uniprot/edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../uniprot/edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../uniprot/edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../uniprot/edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../uniprot/edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../uniprot/edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../uniprot/edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../uniprot/edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../uniprot/programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../uniprot/programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ../uniprot/programs/ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ../uniprot/programs/ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ../uniprot/programs/ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../uniprot/UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../uniprot/vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../uniprot/vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../uniprot/vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../uniprot/vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../uniprot/vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../uniprot/vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../uniprot/vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../uniprot/vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../uniprot/vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../uniprot/vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../uniprot/vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../uniprot/vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../uniprot/vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../uniprot/vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../uniprot/vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../uniprot/vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../uniprot/vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../uniprot/vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../uniprot/vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../uniprot/vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../uniprot/vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../uniprot/vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../uniprot/vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../uniprot/vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../uniprot/vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../uniprot/vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../uniprot/vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../uniprot/vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../uniprot/vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../uniprot/vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../uniprot/vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../uniprot/vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../uniprot/vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../uniprot/vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../uniprot/vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../uniprot/vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../uniprot/vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../uniprot/vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../uniprot/vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../uniprot/vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../uniprot/vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: programs/ImportUniRef.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: vertices/UniRef90Cluster.java.md