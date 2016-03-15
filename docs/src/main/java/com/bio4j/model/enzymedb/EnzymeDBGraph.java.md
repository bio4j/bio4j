
```java
package com.bio4j.model.enzymedb;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.angulillos.*;
```



# ExPASy Enzyme DB graph

This graph includes all Enzyme terms that are included in the ExPASy ENZYME database but **not** those that have been either _transferred_ or _deleted_.

You can get more information about the Enzyme database from its [website](http://enzyme.expasy.org/), in particular

- **[Enzyme description and sample entry](http://enzyme.expasy.org/enzyme_details.html)**
- the **[User manual](http://enzyme.expasy.org/enzuser.txt)**, which contains a more precise description of the data model

This graph has only vertices, of `Enzyme` type; There are other graphs which add edges between `Enzyme`s and other entitities such as proteins.



```java
public abstract class EnzymeDBGraph <
  I extends UntypedGraph<RV,RVT,RE,RET>,
  RV,RVT,
  RE,RET
>
implements
  TypedGraph <
  EnzymeDBGraph<I,RV,RVT,RE,RET>,
  I,RV,RVT,RE,RET
  >
{
```



## Vertices

### Enzyme



```java
  public abstract EnzymeType Enzyme();

  public final class EnzymeType
  extends
    EnzymeDBVertexType <
      Enzyme<I,RV,RVT,RE,RET>,
      EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType
    >
  {
```


#### Enzyme id

The EnzymeDB id of this enzyme, as a `String`.


```java
  public final class id
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,id,String>
  {
    public id() { super(EnzymeType.this); }
    public final Class<String> valueClass() { return String.class; }
  }
```


#### Enzyme cofactors

The cofactors ids? for this enzyme, stored in an array of `String`s.


```java
  public final class cofactors
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,cofactors,String[]>
  {
    public cofactors() { super(EnzymeType.this); }
    public final Class<String[]> valueClass() { return String[].class; }
  }
```


#### Enzyme comments

Enzymes have sometimes a text comment; this property will have as value that comment stored in one `String`, which will be empty if there's no such comment.


```java
  public final class comment
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,comment,String>
  {
    public comment() { super(EnzymeType.this); }
    public final Class<String> valueClass() { return String.class; }
  }
```


#### Enzyme official name

_TO DO_ link to somewhere


```java
  public final class officialName
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,officialName,String>
  {
    public officialName() { super(EnzymeType.this); }
    public final Class<String> valueClass() { return String.class; }
  }
```


#### Enzyme alternate names

Sometimes enzymes have alternate names (synonyms?). They are available here as an array of `String`s.


```java
  public final class alternateNames
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,alternateNames,String[]>
  {
    public alternateNames() { super(EnzymeType.this); }
    public final Class<String[]> valueClass() { return String[].class; }
  }
```


#### Enzyme catalytic activity

_TO DO_ explain this.


```java
  public final class catalyticActivity
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,catalyticActivity,String>
  {
    public catalyticActivity() { super(EnzymeType.this); }
    public final Class<String> valueClass() { return String.class; }
  }
```


#### Enzyme cross-references to ProSite

_TO DO_ explain this.


```java
  public final class prositeCrossReferences
  extends
    EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,prositeCrossReferences,String[]>
  {
    public prositeCrossReferences() { super(EnzymeType.this); }
    public final Class<String[]> valueClass() { return String[].class; }
  }

  public final id id = new id();
  public final cofactors cofactors = new cofactors();
  public final comment comment = new comment();
  public final officialName officialName = new officialName();
  public final alternateNames alternateNames = new alternateNames();
  public final catalyticActivity catalyticActivity = new catalyticActivity();
  public final prositeCrossReferences prositeCrossReferences = new prositeCrossReferences();

  public EnzymeType(RVT raw) { super(raw); }

  public final EnzymeType value() { return graph().Enzyme(); }
  public final Enzyme<I,RV,RVT,RE,RET> from(RV vertex) { return new Enzyme<I,RV,RVT,RE,RET>(vertex, this); }
  }
```


----



  ## Indices

  `Enzyme`s are indexed for **unique** id matches.



```java
  public abstract TypedVertexIndex.Unique <
    Enzyme<I,RV,RVT,RE,RET>, EnzymeType,
    EnzymeType.id, String,
    EnzymeDBGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET
  >
  enzymeIdIndex();
```


----



## Graph extensions

### Link enzymes with UniProt proteins

You can extend the EnzymeDB graph with a graph adding an edge to UniProt proteins, representing _TO DO: What?_. See [UniProtEnzymeDBGraph](../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md) for more.



```java
  public abstract UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> uniProtEnzymeDBGraph();
```


----


## Auxiliary code and helper classes

This section is only relevant if you want to implement this graph and/or understand the code.


```java
  protected I raw = null;
  public I raw() { return raw; }

  public EnzymeDBGraph(I graph) {

  this.raw = graph;
  }
```



### Helper classes

These classes are used to bound all the types and implementation that is generic for every type of this graph: the `graph()` reference of types, vertex, property, edge types should be members of this graph, etc.



```java
  public abstract static class EnzymeDBVertex <
  V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
  VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>,
  I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET
  >
  implements
  TypedVertex<V,VT,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
  {

  private RV vertex;
  private VT type;

  protected EnzymeDBVertex(RV vertex, VT type) {
    this.vertex = vertex;
    this.type = type;
  }

  @Override
  public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }

  @Override
  public final RV raw() { return this.vertex; }

  @Override
  public final VT type() { return type; }
  }

  public abstract class EnzymeDBVertexType <
  V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
  VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>
  >
  implements
  TypedVertex.Type<V,VT,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
  {

  private RVT raw;

  protected EnzymeDBVertexType(RVT raw) { this.raw = raw; }

  @Override
  public final RVT raw() { return raw; }

  @Override
  public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return EnzymeDBGraph.this; }
  }



  public abstract class EnzymeDBVertexProperty <
  V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
  VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>,
  P extends EnzymeDBVertexProperty<V,VT,P,PV>,
  PV
  >
  implements
  Property<V,VT,P,PV,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
  {

  private VT type;

  protected EnzymeDBVertexProperty(VT type) { this.type = type; }

  @Override
  public final VT elementType() { return type; }
  }



  public abstract static class EnzymeDBEdge <
  S extends EnzymeDBVertex<S, ST,I,RV,RVT,RE,RET>,
  ST extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<S, ST>,
  E extends EnzymeDBEdge<S,ST,E,ET,T,TT,I,RV,RVT,RE,RET>,
  ET extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBEdgeType<S,ST,E,ET,T,TT>,
  T extends EnzymeDBVertex<T, TT,I,RV,RVT,RE,RET>,
  TT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<T, TT>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
  >
  implements
  TypedEdge <
    S,ST,EnzymeDBGraph<I,RV,RVT,RE,RET>,
    E,ET,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET,
    T,TT,EnzymeDBGraph<I,RV,RVT,RE,RET>
  >
  {

  private RE edge;
  private ET type;

  protected EnzymeDBEdge(RE edge, ET type) {

    this.edge = edge;
    this.type = type;
  }

  @Override
  public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }

  @Override
  public RE raw() { return this.edge; }

  @Override
  public ET type() { return type; }
  }

  public abstract class EnzymeDBEdgeType <
  S extends EnzymeDBVertex<S, ST,I,RV,RVT,RE,RET>,
  ST extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<S, ST>,
  E extends EnzymeDBEdge<S,ST,E,ET,T,TT,I,RV,RVT,RE,RET>,
  ET extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBEdgeType<S,ST,E,ET,T,TT>,
  T extends EnzymeDBVertex<T, TT,I,RV,RVT,RE,RET>,
  TT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<T, TT>
  >
  implements
  TypedEdge.Type<
    S,ST,EnzymeDBGraph<I,RV,RVT,RE,RET>,
    E,ET,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET,
    T,TT,EnzymeDBGraph<I,RV,RVT,RE,RET>
  >
  {

  private RET raw;
  private ST srcT;
  private TT tgtT;

  protected EnzymeDBEdgeType(ST srcT, RET raw, TT tgtT) {

    this.raw = raw;
    this.srcT = srcT;
    this.tgtT = tgtT;
  }

  @Override
  public final ST sourceType() { return srcT; }

  @Override
  public final TT targetType() { return tgtT; }

  @Override
  public final RET raw() { return raw; }

  @Override
  public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return EnzymeDBGraph.this; }
  }
}

```




[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: EnzymeDBGraph.java.md
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
[main/java/com/bio4j/model/go/GoGraph.java]: ../go/GoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../ncbiTaxonomy/edges/NCBITaxonParent.java.md
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