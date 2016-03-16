package com.bio4j.model.uniprot;

import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;
import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.angulillos.*;

import java.util.Date;

/*

# UniProt graph

This graph includes virtually all of the data from [UniProt KB](http://www.uniprot.org)

## data model

The hub of this graph are Protein vertices, having edges connecting them to all the different entities that may be found in UniProt KB.

### Proteins

The following properties are stored at the `Protein` vertex:

- `accession`
- `shortName`
- `name`
- `fullName`
- `sequence`
- `modifiedDate`
- `createdDate`
- `mass`
- `version`
- `length`

### Gene Locations

Protein gene locations are stored as edges with type `ProteinGeneLocatoin` linking to `GeneLocation` vertices. These `GeneLocation` vertices only have a property `name` representing each of the different gene location types that exist:
- Hydrogenosome
- Mitochondrion
- Nucleomorph
- Plasmid
- Chloroplast
- Non-photosynthetic plastid
- Organellar chromatophore
- Plastid

### Keywords

Connected to `Protein` vertices via `ProteinKeyword` edges.
The following properties are stored at the `Keyword` vertex:

- `id`
- `name`

### InterPro motifs

Connected to `Protein` vertices via `ProteinInterPro` edges.
The following properties are stored at the `InterPro` vertex:

- `id`
- `name`

### Pfam

Connected to `Protein` vertices via `ProteinPfam` edges.
The following properties are stored at the `Pfam` vertex:

- `id`
- `name`

### Dataset

These vertices represent the data set to which a protein belongs. There only exist two vertices in the database, one for Swiss-Prot and other for TrEMBL.
A protein is connected to its data set via an edge with type `ProteinDataset`

### EMBL

EMBL terms, including the following properties:

- `id`
- `proteinSequenceId`
- `moleculeType`

They are connected to `Protein` vertices via `ProteinEMBL` edges.

### Ensembl

Ensembl terms, including the following properties:

- `id`
- `proteinSequenceId`
- `moleculeId`
- `geneId`

They are connected to `Protein` vertices via `ProteinEnsembl` edges.

### Kegg

Connected to `Protein` vertices via `ProteinKegg` edges.
The following properties are stored at the `Kegg` vertex:

- `id`

### PIR

Connected to `Protein` vertices via `ProteinPIR` edges.
The following properties are stored at the `PIR` vertex:

- `id`
- `entryName`

### ReactomeTerms

Connected to `Protein` vertices via `ProteinReactomeTerm` edges.
The following properties are stored at the `ReactomeTerm` vertex:

- `id`
- `pathwayName`

### RefSeq

Connected to `Protein` vertices via `ProteinRefSeq` edges.
The following properties are stored at the `RefSeq` vertex:

- `id`
- `nucleotideSequenceId`

### UniGene

Connected to `Protein` vertices via `ProteinUniGene` edges.
The following properties are stored at the `UniGene` vertex:

- `id`


### Comments

Protein comments are modelled through `ProteinComment` edges linking to `CommentType` vertices. `CommentType` vertices only have one property `name` and there exists a vertex for each of the possible comment types.

#### ProteinComment properties

- `position`
- `mass`
- `evidence`
- `method`
- `begin`
- `end`
- `status`
- `text`
- `temperatureDependence`
- `phDependence`
- `kineticsXML`
- `absorptionMax`
- `absorptionText`
- `redoxPotential`
- `redoxPotentialEvidence`

Some of these properties are specific for a type of comment and thus will be empty for other comment types. We decided it was a better option to model it this way instead of creating many different edge types since otherwise we would have ended having a lot of edge type that would be almost equivalent.

There are though a few _exceptions_ where comments are promoted to be entities such as:

- `Alternative producs` --> This comment type actually includes information about isoforms which are modelled by `Isoform` vertices
- `Subcellular locations` --> Subcellular locations constitute a vertex type themselves since the hierarchy of locations included in the comment is modelled as a sort of taxonomy, being the protein connected only to the _leaf_ node of that taxonomy through an edge of the type `ProteinSubcellularLocation`.
- `Disease` --> Diseases have their own vertex type `Disease` to which proteins are connected via edges from the type `ProteinDisease`. `Disease` nodes have in turn the following properties:
+ `id`
+ `name`
+ `acronym`
+ `description`
`ProteinDisease` edges include the properties:
+ `text`
+ `evidence`
+ `status`

### Features

Protein features are modelled through `ProteinFeature` edges linking to `FeatureType` vertices. `FeatureType` vertices only have one property `name` and there exists a vertex for each of the possible feature types.

#### ProteinFeature properties

- `description`
- `evidence`
- `id`
- `status`
- `begin`
- `end`
- `original`
- `variation`
- `ref`

Some of these properties are specific for a type of feature and thus will be empty for other feature types. We decided it was a better option to model it this way instead of creating many different edge types since otherwise we would have ended having too many edge types for things that are almost equivalent.

### Isoforms

Modelled as `Isoform` vertices, include the following properties:

- `id`
- `name`
- `note`
- `sequence`

They are connected to proteins through edges from the type `ProteinIsoformInteraction`
They are also linked to `AlternativeProduct` vertices via edges of type `IsoformEventGenerator`.

### Organisms

Leaf vertices in the taxonomy tree, they are the taxonomic units linked to proteins through `ProteinOrganism` edges. They are connected to non-leaf vertices via `OrganismTaxon` edges.
The following properties are included:

- `scientificName`
- `commonName`
- `synonymName`

### Citations

#### References

All types of citations are connected to Proteins via intermediary vertices of type `Reference`. These vertices only include one property `date`. Edges linking `Protein` and `Reference` vertices are from the type `ProteinReference`.

#### Articles

`Article` properties:

- `title`
- `doId`

Other properties such as the following are stored via edges:

The journal where the `Article` was published is modelled through an edge of type `ArticleJournal` which links to a `Journal` node.
They are connected to `Protein` vertices via an intermediary `Reference` vertex. Articles are connected to `Reference` vertices via an edge of type `ReferenceArticle`.
Article authors are stored at the `Reference` level.
In the case where they have a `Pubmed` id, there exists an edge of type `ArticlePubmed` connecting it to the specific `Pubmed` vertex.

#### Online Articles

`OnlineArticle` properties:

- `title`

Other properties such as the following are stored via edges:

The online journal where the `OnlineArticle` was published is modelled through an edge of type `OnlineArticleOnlineJournal` which links to a `OnlineJournal` node.
They are connected to `Protein` vertices via an intermediary `Reference` vertex. Online articles are connected to `Reference` vertices via an edge of type `ReferenceOnlineArticle`.
`OnlineArticle` authors are stored at the `Reference` level.


#### Books

`Book` properties:

- `name`

Other properties such as the following are stored via edges:

The city where the book was published is modelled through an edge of type `BookCity` which links to a `City` node.
Book editors are modelled via `BookEditor` edges linking to `Person` vertices.
Book publishers are modelled through `BookPublisher` edges connecting to `Publisher` vertices.
Book authors are stored at the `Reference` level.

They are connected to `Protein` vertices via an intermediary `Reference` vertex. Books are connected to `Reference` vertices via an edge of type `ReferenceBook`.

#### Submissions

`Submission` properties:

- `title`

Other properties such as the following are stored via edges:

The database where a submission was submitted is modelled via an edge of type `SubmissionDB` which links to a `DB` vertex.

They are connected to `Protein` vertices via an intermediary `Reference` vertex. Submissions are connected to `Reference` vertices via an edge of type `ReferenceSubmission`.
Submission authors are stored at the `Reference` level.

#### Patents

`Patent` properties:

- `title`
- `number`

They are connected to `Protein` vertices via an intermediary `Reference` vertex. Patents are connected to `Reference` vertices via an edge of type `ReferencePatent`.
Patent authors are stored at the `Reference` level.

#### Theses

`Thesis` properties:

- `title`

Other properties such as the following are stored via edges:

The `Institute` associated to the `Thesis` is modelled via an edge of type `ThesisInstitute` which links to a `Thesis` vertex.

They are connected to `Protein` vertices via an intermediary `Reference` vertex. Theses are connected to `Reference` vertices via an edge of type `ReferenceThesis`.
Thesis authors are stored at the `Reference` level.

#### Unpublished Observations

They are connected to `Protein` vertices via an intermediary `Reference` vertex. Theses are connected to `Reference` vertices via an edge of type `ReferenceUnpublishedObservations`.
Unpublished observations authors are stored at the `Reference` level.

#### Journals

`Journal` properties:

- `name`

#### Online journals

`OnlineJournal` properties:

- `name`

#### Institutes

`Institute` properties:

- `name`

#### Publishers

`Publisher` properties:

- `name`

#### People

`Person` vertices include the following properties:

- `name`

They are linked to `Reference` vertices via edges of type `ReferenceAuthorPerson`.

#### Consortiums

`Consortium` vertices include the following properties:

- `name`

#### Countries

`Country` vertices have the properties:

- `name`

#### Cities

`City` vertices include the following properties:

- `name`

They are linked to `Reference` vertices via edges of type `ReferenceAuthorConsortium`.

### Subcellular Locations

`SubcellularLocation` vertices include the following properties:

- `name`

They are linked to `Protein` vertices via edges of type `ProteinSubcellularLocation`.
The hierarchical relationship among differnt subcellular location elements is expressed through edges of type `SubcellularLocationParent`.

### Sequence Caution

`SequenceCaution` vertices include the following properties:

- `name`

They are connected to `Protein` vertices via edges of type `ProteinSequenceCaution`.

### Alternative Products

`AlternativeProduct` vertices have the following properties:

- `name`

They are associated to `Isoform` vertices via edges of type `IsoformEventGenerator`.


*/
public abstract class UniProtGraph<
// untyped graph
I extends UntypedGraph<RV, RVT, RE, RET>,
// vertices
RV, RVT,
// edges
RE, RET
>
implements
TypedGraph<
UniProtGraph<I,RV,RVT,RE,RET>,
I, RV, RVT, RE, RET
> {

  protected I raw = null;

  public UniProtGraph(I graph){
    raw = graph;
  }

  public I raw(){
    return raw;
  }

  public abstract UniProtUniRefGraph<I,RV,RVT,RE,RET> uniProtUniRefGraph();
  public abstract UniProtGoGraph<I,RV,RVT,RE,RET> uniProtGoGraph();
  public abstract UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> uniProtEnzymeDBGraph();
  public abstract UniProtNCBITaxonomyGraph<I,RV,RVT,RE,RET> uniProtNCBITaxonomyGraph();


  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // indices
  public abstract TypedVertexIndex.Unique <
  // vertex
  AlternativeProduct<I,RV,RVT,RE,RET>, AlternativeProductType,
  // property
  AlternativeProductType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  alternativeProductNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  GeneLocation<I,RV,RVT,RE,RET>, GeneLocationType,
  // property
  GeneLocationType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  geneLocationNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  GeneName<I,RV,RVT,RE,RET>, GeneNameType,
  // property
  GeneNameType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  geneNameNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Disease<I,RV,RVT,RE,RET>, DiseaseType,
  // property
  DiseaseType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  diseaseIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Journal<I,RV,RVT,RE,RET>, JournalType,
  // property
  JournalType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  journalNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Article<I,RV,RVT,RE,RET>, ArticleType,
  // property
  ArticleType.title, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  articleTitleIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  OnlineJournal<I,RV,RVT,RE,RET>, OnlineJournalType,
  // property
  OnlineJournalType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  onlineJournalNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  OnlineArticle<I,RV,RVT,RE,RET>, OnlineArticleType,
  // property
  OnlineArticleType.title, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  onlineArticleTitleIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  City<I,RV,RVT,RE,RET>, CityType,
  // property
  CityType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  cityNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Publisher<I,RV,RVT,RE,RET>, PublisherType,
  // property
  PublisherType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  publisherNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Book<I,RV,RVT,RE,RET>, BookType,
  // property
  BookType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  bookNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  DB<I,RV,RVT,RE,RET>, DBType,
  // property
  DBType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  dbNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Country<I,RV,RVT,RE,RET>, CountryType,
  // property
  CountryType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  countryNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Patent<I,RV,RVT,RE,RET>, PatentType,
  // property
  PatentType.number, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  patentNumberIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
  // property
  SequenceCautionType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  sequenceCautionNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Submission<I,RV,RVT,RE,RET>, SubmissionType,
  // property
  SubmissionType.title, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  submissionTitleIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType,
  // property
  SubcellularLocationType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  subcellularLocationNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Institute<I,RV,RVT,RE,RET>, InstituteType,
  // property
  InstituteType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  instituteNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Isoform<I,RV,RVT,RE,RET>, IsoformType,
  // property
  IsoformType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  isoformIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Consortium<I,RV,RVT,RE,RET>, ConsortiumType,
  // property
  ConsortiumType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  consortiumNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Thesis<I,RV,RVT,RE,RET>, ThesisType,
  // property
  ThesisType.title, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  thesisTitleIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Person<I,RV,RVT,RE,RET>, PersonType,
  // property
  PersonType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  personNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Protein<I,RV,RVT,RE,RET>, ProteinType,
  // property
  ProteinType.accession, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  proteinAccessionIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Ensembl<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.EnsemblType,
  // property
  UniProtGraph<I,RV,RVT,RE,RET>.EnsemblType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  ensemblIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  PIR<I,RV,RVT,RE,RET>, PIRType,
  // property
  PIRType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  pIRIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Pubmed<I,RV,RVT,RE,RET>, PubmedType,
  // property
  PubmedType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  pubmedIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  UniGene<I,RV,RVT,RE,RET>, UniGeneType,
  // property
  UniGeneType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  uniGeneIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Kegg<I,RV,RVT,RE,RET>, KeggType,
  // property
  KeggType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  keggIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  EMBL<I,RV,RVT,RE,RET>, EMBLType,
  // property
  EMBLType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  eMBLIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  RefSeq<I,RV,RVT,RE,RET>, RefSeqType,
  // property
  RefSeqType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  refSeqIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  ReactomeTerm<I,RV,RVT,RE,RET>, ReactomeTermType,
  // property
  ReactomeTermType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  reactomeTermIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Dataset<I,RV,RVT,RE,RET>, DatasetType,
  // property
  DatasetType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  datasetNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Keyword<I,RV,RVT,RE,RET>, KeywordType,
  // property
  KeywordType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  keywordIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  InterPro<I,RV,RVT,RE,RET>, InterProType,
  // property
  InterProType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  interproIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Pfam<I,RV,RVT,RE,RET>, PfamType,
  // property
  PfamType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  pfamIdIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Organism<I,RV,RVT,RE,RET>, OrganismType,
  // property
  OrganismType.scientificName, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  organismScientificNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Taxon<I,RV,RVT,RE,RET>, TaxonType,
  // property
  TaxonType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  taxonNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
  // property
  FeatureTypeType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  featureTypeNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
  // property
  CommentTypeType.name, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  commentTypeNameIndex();
  public abstract TypedVertexIndex.Unique <
  // vertex
  Reference<I,RV,RVT,RE,RET>, ReferenceType,
  // property
  ReferenceType.id, String,
  // graph
  UniProtGraph<I,RV,RVT,RE,RET>,
  I, RV, RVT, RE, RET
  >
  referenceIdIndex();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // types
  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // vertices
  public abstract AlternativeProductType AlternativeProduct();

  public abstract ArticleType Article();

  public abstract BookType Book();

  public abstract CityType City();

  public abstract DiseaseType Disease();

  public abstract CommentTypeType CommentType();

  public abstract ConsortiumType Consortium();

  public abstract DatasetType Dataset();

  public abstract CountryType Country();

  public abstract DBType DB();

  public abstract EMBLType EMBL();

  public abstract EnsemblType Ensembl();

  public abstract FeatureTypeType FeatureType();

  public abstract GeneLocationType GeneLocation();

  public abstract GeneNameType GeneName();

  public abstract InstituteType Institute();

  public abstract InterProType InterPro();

  public abstract IsoformType Isoform();

  public abstract JournalType Journal();

  public abstract KeggType Kegg();

  public abstract KeywordType Keyword();

  public abstract OnlineArticleType OnlineArticle();

  public abstract OrganismType Organism();

  public abstract OnlineJournalType OnlineJournal();

  public abstract PatentType Patent();

  public abstract PersonType Person();

  public abstract SequenceCautionType SequenceCaution();

  public abstract PfamType Pfam();

  public abstract PIRType PIR();

  public abstract ProteinType Protein();

  public abstract PublisherType Publisher();

  public abstract PubmedType Pubmed();

  public abstract ReactomeTermType ReactomeTerm();

  public abstract ReferenceType Reference();

  public abstract RefSeqType RefSeq();

  public abstract SubcellularLocationType SubcellularLocation();

  public abstract SubmissionType Submission();

  public abstract TaxonType Taxon();

  public abstract ThesisType Thesis();

  public abstract UniGeneType UniGene();

  public abstract UnpublishedObservationType UnpublishedObservation();

  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // edges

  public abstract IsoformEventGeneratorType IsoformEventGenerator();

  public abstract ArticleJournalType ArticleJournal();

  public abstract ArticlePubmedType ArticlePubmed();

  public abstract BookCityType BookCity();

  public abstract BookPublisherType BookPublisher();

  public abstract BookEditorType BookEditor();

  public abstract InstituteCountryType InstituteCountry();

  public abstract OnlineArticleOnlineJournalType OnlineArticleOnlineJournal();

  public abstract OrganismTaxonType OrganismTaxon();

  public abstract ProteinCommentType ProteinComment();

  public abstract ProteinDatasetType ProteinDataset();

  public abstract ProteinDiseaseType ProteinDisease();

  public abstract ProteinEMBLType ProteinEMBL();

  public abstract ProteinEnsemblType ProteinEnsembl();

  public abstract ProteinFeatureType ProteinFeature();

  public abstract ProteinGeneLocationType ProteinGeneLocation();

  public abstract ProteinGeneNameType ProteinGeneName();

  public abstract ProteinInterProType ProteinInterPro();

  public abstract ProteinKeggType ProteinKegg();

  public abstract ProteinKeywordType ProteinKeyword();

  public abstract ProteinOrganismType ProteinOrganism();

  public abstract ProteinPfamType ProteinPfam();

  public abstract ProteinPIRType ProteinPIR();

  public abstract ProteinProteinInteractionType ProteinProteinInteraction();

  public abstract ProteinIsoformInteractionType ProteinIsoformInteraction();

  public abstract ProteinIsoformType ProteinIsoform();

  public abstract ProteinReactomeTermType ProteinReactomeTerm();

  public abstract ProteinSubcellularLocationType ProteinSubcellularLocation();

  public abstract ProteinUniGeneType ProteinUniGene();

  public abstract ProteinRefSeqType ProteinRefSeq();

  public abstract ProteinReferenceType ProteinReference();

  public abstract ProteinSequenceCautionType ProteinSequenceCaution();

  public abstract ReferenceAuthorPersonType ReferenceAuthorPerson();

  public abstract ReferenceAuthorConsortiumType ReferenceAuthorConsortium();

  public abstract ReferenceArticleType ReferenceArticle();

  public abstract ReferenceBookType ReferenceBook();

  public abstract ReferenceOnlineArticleType ReferenceOnlineArticle();

  public abstract ReferencePatentType ReferencePatent();

  public abstract ReferenceThesisType ReferenceThesis();

  public abstract ReferenceSubmissionType ReferenceSubmission();

  public abstract ReferenceUnpublishedObservationType ReferenceUnpublishedObservation();

  public abstract SubmissionDBType SubmissionDB();

  public abstract SubcellularLocationParentType SubcellularLocationParent();

  public abstract TaxonParentType TaxonParent();

  public abstract ThesisInstituteType ThesisInstitute();



  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Vertex types

  public final class AlternativeProductType
  extends
  UniProtVertexType<
  AlternativeProduct<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.AlternativeProductType
  > {

    public final name name = new name();

    public AlternativeProductType(RVT raw) {
      super(raw);
    }

    @Override
    public AlternativeProductType value() {
      return graph().AlternativeProduct();
    }

    @Override
    public AlternativeProduct<I,RV,RVT,RE,RET> from(RV vertex) {
      return new AlternativeProduct<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<AlternativeProduct<I,RV,RVT,RE,RET>, AlternativeProductType, name, String> {
      public name() {
        super(AlternativeProductType.this);
      }

      public final Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class ArticleType
  extends
  UniProtVertexType<
  Article<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.ArticleType
  > {

    public final doId doId = new doId();
    public final title title = new title();

    public ArticleType(RVT raw) {
      super(raw);
    }

    @Override
    public ArticleType value() {
      return graph().Article();
    }

    @Override
    public Article<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Article<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class doId
    extends
    UniProtVertexProperty<Article<I,RV,RVT,RE,RET>, ArticleType, doId, String> {
      public doId() {
        super(ArticleType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

    public final class title
    extends
    UniProtVertexProperty<Article<I,RV,RVT,RE,RET>, ArticleType, title, String> {
      public title() {
        super(ArticleType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }


  public final class BookType
  extends
  UniProtVertexType<
  Book<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.BookType
  > {

    public final name name = new name();

    public BookType(RVT raw) {
      super(raw);
    }

    @Override
    public BookType value() {
      return graph().Book();
    }

    @Override
    public Book<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Book<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Book<I,RV,RVT,RE,RET>, BookType, name, String> {
      public name() {
        super(BookType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class CityType
  extends
  UniProtVertexType<
  City<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.CityType
  > {

    public final name name = new name();

    public CityType(RVT raw) {
      super(raw);
    }

    @Override
    public CityType value() {
      return graph().City();
    }

    @Override
    public City<I,RV,RVT,RE,RET> from(RV vertex) {
      return new City<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<City<I,RV,RVT,RE,RET>, CityType, name, String> {
      public name() {
        super(CityType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class ConsortiumType
  extends
  UniProtVertexType<
  Consortium<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.ConsortiumType
  > {

    public final name name = new name();

    public ConsortiumType(RVT raw) {
      super(raw);
    }

    @Override
    public ConsortiumType value() {
      return graph().Consortium();
    }

    @Override
    public Consortium<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Consortium<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Consortium<I,RV,RVT,RE,RET>, ConsortiumType, name, String> {
      public name() {
        super(ConsortiumType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class CommentTypeType
  extends
  UniProtVertexType<
  CommentType<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.CommentTypeType
  > {

    public final name name = new name();

    public CommentTypeType(RVT raw) {
      super(raw);
    }

    @Override
    public CommentTypeType value() {
      return graph().CommentType();
    }

    @Override
    public CommentType<I,RV,RVT,RE,RET> from(RV vertex) {
      return new CommentType<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<CommentType<I,RV,RVT,RE,RET>, CommentTypeType, name, String> {
      public name() {
        super(CommentTypeType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class CountryType
  extends
  UniProtVertexType<
  Country<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.CountryType
  > {

    public final name name = new name();

    public CountryType(RVT raw) {
      super(raw);
    }

    @Override
    public CountryType value() {
      return graph().Country();
    }

    @Override
    public Country<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Country<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Country<I,RV,RVT,RE,RET>, CountryType, name, String> {
      public name() {
        super(CountryType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class DatasetType
  extends
  UniProtVertexType<
  Dataset<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.DatasetType
  > {

    public final name name = new name();

    public DatasetType(RVT raw) {
      super(raw);
    }

    @Override
    public DatasetType value() {
      return graph().Dataset();
    }

    @Override
    public Dataset<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Dataset<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Dataset<I,RV,RVT,RE,RET>, DatasetType, name, String> {
      public name() {
        super(DatasetType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class DBType
  extends
  UniProtVertexType<
  DB<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.DBType
  > {

    public final name name = new name();

    public DBType(RVT raw) {
      super(raw);
    }

    @Override
    public DBType value() {
      return graph().DB();
    }

    @Override
    public DB<I,RV,RVT,RE,RET> from(RV vertex) {
      return new DB<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<DB<I,RV,RVT,RE,RET>, DBType, name, String> {
      public name() {
        super(DBType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class DiseaseType
  extends
  UniProtVertexType<
  Disease<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.DiseaseType
  > {

    public final name name = new name();
    public final id id = new id();
    public final acronym acronym = new acronym();
    public final description description = new description();

    public DiseaseType(RVT raw) {
      super(raw);
    }

    @Override
    public DiseaseType value() {
      return graph().Disease();
    }

    @Override
    public Disease<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Disease<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Disease<I,RV,RVT,RE,RET>, DiseaseType, name, String> {
      public name() {
        super(DiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtVertexProperty<Disease<I,RV,RVT,RE,RET>, DiseaseType, id, String> {
      public id() {
        super(DiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class acronym
    extends
    UniProtVertexProperty<Disease<I,RV,RVT,RE,RET>, DiseaseType, acronym, String> {
      public acronym() {
        super(DiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class description
    extends
    UniProtVertexProperty<Disease<I,RV,RVT,RE,RET>, DiseaseType, description, String> {
      public description() {
        super(DiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class EMBLType
  extends
  UniProtVertexType<
  EMBL<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.EMBLType
  > {

    public final id id = new id();
    public final proteinSequenceId proteinSequenceId = new proteinSequenceId();
    public final moleculeType moleculeType = new moleculeType();

    public EMBLType(RVT raw) {
      super(raw);
    }

    @Override
    public EMBLType value() {
      return graph().EMBL();
    }

    @Override
    public EMBL<I,RV,RVT,RE,RET> from(RV vertex) {
      return new EMBL<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<EMBL<I,RV,RVT,RE,RET>, EMBLType, id, String> {
      public id() {
        super(EMBLType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class proteinSequenceId
    extends
    UniProtVertexProperty<EMBL<I,RV,RVT,RE,RET>, EMBLType, proteinSequenceId, String> {
      public proteinSequenceId() {
        super(EMBLType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class moleculeType
    extends
    UniProtVertexProperty<EMBL<I,RV,RVT,RE,RET>, EMBLType, moleculeType, String> {
      public moleculeType() {
        super(EMBLType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class EnsemblType
  extends
  UniProtVertexType<
  Ensembl<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.EnsemblType
  > {

    public final id id = new id();
    public final proteinSequenceId proteinSequenceId = new proteinSequenceId();
    public final moleculeId moleculeId = new moleculeId();
    public final geneId geneId = new geneId();

    public EnsemblType(RVT raw) {
      super(raw);
    }

    @Override
    public EnsemblType value() {
      return graph().Ensembl();
    }

    @Override
    public Ensembl<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Ensembl<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<Ensembl<I,RV,RVT,RE,RET>, EnsemblType, id, String> {
      public id() {
        super(EnsemblType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class proteinSequenceId
    extends
    UniProtVertexProperty<Ensembl<I,RV,RVT,RE,RET>, EnsemblType, proteinSequenceId, String> {
      public proteinSequenceId() {
        super(EnsemblType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class moleculeId
    extends
    UniProtVertexProperty<Ensembl<I,RV,RVT,RE,RET>, EnsemblType, moleculeId, String> {
      public moleculeId() {
        super(EnsemblType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class geneId
    extends
    UniProtVertexProperty<Ensembl<I,RV,RVT,RE,RET>, EnsemblType, geneId, String> {
      public geneId() {
        super(EnsemblType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class FeatureTypeType
  extends
  UniProtVertexType<
  FeatureType<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.FeatureTypeType
  > {

    public final name name = new name();

    public FeatureTypeType(RVT raw) {
      super(raw);
    }

    @Override
    public FeatureTypeType value() {
      return graph().FeatureType();
    }

    @Override
    public FeatureType<I,RV,RVT,RE,RET> from(RV vertex) {
      return new FeatureType<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType, name, String> {
      public name() {
        super(FeatureTypeType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class GeneLocationType
  extends
  UniProtVertexType<
  GeneLocation<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.GeneLocationType
  > {

    public final name name = new name();

    public GeneLocationType(RVT raw) {
      super(raw);
    }

    @Override
    public GeneLocationType value() {
      return graph().GeneLocation();
    }

    @Override
    public GeneLocation<I,RV,RVT,RE,RET> from(RV vertex) {
      return new GeneLocation<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<GeneLocation<I,RV,RVT,RE,RET>, GeneLocationType, name, String> {
      public name() {
        super(GeneLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class GeneNameType
  extends
  UniProtVertexType<
  GeneName<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.GeneNameType
  > {

    public final name name = new name();

    public GeneNameType(RVT raw) {
      super(raw);
    }

    @Override
    public GeneNameType value() {
      return graph().GeneName();
    }

    @Override
    public GeneName<I,RV,RVT,RE,RET> from(RV vertex) {
      return new GeneName<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<GeneName<I,RV,RVT,RE,RET>, GeneNameType, name, String> {
      public name() {
        super(GeneNameType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class InterProType
  extends
  UniProtVertexType<
  InterPro<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.InterProType
  > {

    public final name name = new name();
    public final id id = new id();

    public InterProType(RVT raw) {
      super(raw);
    }

    @Override
    public InterProType value() {
      return graph().InterPro();
    }

    @Override
    public InterPro<I,RV,RVT,RE,RET> from(RV vertex) {
      return new InterPro<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<InterPro<I,RV,RVT,RE,RET>, InterProType, name, String> {
      public name() {
        super(InterProType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtVertexProperty<InterPro<I,RV,RVT,RE,RET>, InterProType, id, String> {
      public id() {
        super(InterProType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class InstituteType
  extends
  UniProtVertexType<
  Institute<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.InstituteType
  > {

    public final name name = new name();

    public InstituteType(RVT raw) {
      super(raw);
    }

    @Override
    public InstituteType value() {
      return graph().Institute();
    }

    @Override
    public Institute<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Institute<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Institute<I,RV,RVT,RE,RET>, InstituteType, name, String> {
      public name() {
        super(InstituteType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class IsoformType
  extends
  UniProtVertexType<
  Isoform<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.IsoformType
  > {

    public final name name = new name();
    public final id id = new id();
    public final note note = new note();
    public final sequence sequence = new sequence();

    public IsoformType(RVT raw) {
      super(raw);
    }

    @Override
    public IsoformType value() {
      return graph().Isoform();
    }

    @Override
    public Isoform<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Isoform<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class sequence
    extends
    UniProtVertexProperty<Isoform<I,RV,RVT,RE,RET>, IsoformType, sequence, String> {
      public sequence() {
        super(IsoformType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class note
    extends
    UniProtVertexProperty<Isoform<I,RV,RVT,RE,RET>, IsoformType, note, String> {
      public note() {
        super(IsoformType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtVertexProperty<Isoform<I,RV,RVT,RE,RET>, IsoformType, id, String> {
      public id() {
        super(IsoformType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class name
    extends
    UniProtVertexProperty<Isoform<I,RV,RVT,RE,RET>, IsoformType, name, String> {
      public name() {
        super(IsoformType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class JournalType
  extends
  UniProtVertexType<
  Journal<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.JournalType
  > {

    public final name name = new name();

    public JournalType(RVT raw) {
      super(raw);
    }

    @Override
    public JournalType value() {
      return graph().Journal();
    }

    @Override
    public Journal<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Journal<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Journal<I,RV,RVT,RE,RET>, JournalType, name, String> {
      public name() {
        super(JournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class KeggType
  extends
  UniProtVertexType<
  Kegg<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.KeggType
  > {

    public final id id = new id();

    public KeggType(RVT raw) {
      super(raw);
    }

    @Override
    public KeggType value() {
      return graph().Kegg();
    }

    @Override
    public Kegg<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Kegg<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<Kegg<I,RV,RVT,RE,RET>, KeggType, id, String> {
      public id() {
        super(KeggType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class KeywordType
  extends
  UniProtVertexType<
  Keyword<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.KeywordType
  > {

    public final name name = new name();
    public final id id = new id();

    public KeywordType(RVT raw) {
      super(raw);
    }

    @Override
    public KeywordType value() {
      return graph().Keyword();
    }

    @Override
    public Keyword<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Keyword<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Keyword<I,RV,RVT,RE,RET>, KeywordType, name, String> {
      public name() {
        super(KeywordType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtVertexProperty<Keyword<I,RV,RVT,RE,RET>, KeywordType, id, String> {
      public id() {
        super(KeywordType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class OnlineArticleType
  extends
  UniProtVertexType<
  OnlineArticle<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.OnlineArticleType
  > {

    public final title title = new title();

    public OnlineArticleType(RVT raw) {
      super(raw);
    }

    @Override
    public OnlineArticleType value() {
      return graph().OnlineArticle();
    }

    @Override
    public OnlineArticle<I,RV,RVT,RE,RET> from(RV vertex) {
      return new OnlineArticle<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class title
    extends
    UniProtVertexProperty<OnlineArticle<I,RV,RVT,RE,RET>, OnlineArticleType, title, String> {
      public title() {
        super(OnlineArticleType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class OnlineJournalType
  extends
  UniProtVertexType<
  OnlineJournal<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.OnlineJournalType
  > {

    public final name name = new name();

    public OnlineJournalType(RVT raw) {
      super(raw);
    }

    @Override
    public OnlineJournalType value() {
      return graph().OnlineJournal();
    }

    @Override
    public OnlineJournal<I,RV,RVT,RE,RET> from(RV vertex) {
      return new OnlineJournal<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<OnlineJournal<I,RV,RVT,RE,RET>, OnlineJournalType, name, String> {
      public name() {
        super(OnlineJournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class OrganismType
  extends
  UniProtVertexType<
  Organism<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.OrganismType
  > {

    public final scientificName scientificName = new scientificName();
    public final commonName commonName = new commonName();
    public final synonymName synonymName = new synonymName();

    public OrganismType(RVT raw) {
      super(raw);
    }

    @Override
    public OrganismType value() {
      return graph().Organism();
    }

    @Override
    public Organism<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Organism<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class scientificName
    extends
    UniProtVertexProperty<Organism<I,RV,RVT,RE,RET>, OrganismType, scientificName, String> {
      public scientificName() {
        super(OrganismType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class commonName
    extends
    UniProtVertexProperty<Organism<I,RV,RVT,RE,RET>, OrganismType, commonName, String> {
      public commonName() {
        super(OrganismType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class synonymName
    extends
    UniProtVertexProperty<Organism<I,RV,RVT,RE,RET>, OrganismType, synonymName, String> {
      public synonymName() {
        super(OrganismType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class PatentType
  extends
  UniProtVertexType<
  Patent<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PatentType
  > {

    public final title title = new title();
    public final number number = new number();

    public PatentType(RVT raw) {
      super(raw);
    }

    @Override
    public PatentType value() {
      return graph().Patent();
    }

    @Override
    public Patent<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Patent<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class title
    extends
    UniProtVertexProperty<Patent<I,RV,RVT,RE,RET>, PatentType, title, String> {
      public title() {
        super(PatentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

    public final class number
    extends
    UniProtVertexProperty<Patent<I,RV,RVT,RE,RET>, PatentType, number, String> {
      public number() {
        super(PatentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class PersonType
  extends
  UniProtVertexType<
  Person<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PersonType
  > {

    public final name name = new name();

    public PersonType(RVT raw) {
      super(raw);
    }

    @Override
    public PersonType value() {
      return graph().Person();
    }

    @Override
    public Person<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Person<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Person<I,RV,RVT,RE,RET>, PersonType, name, String> {
      public name() {
        super(PersonType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class PfamType
  extends
  UniProtVertexType<
  Pfam<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PfamType
  > {

    public final name name = new name();
    public final id id = new id();

    public PfamType(RVT raw) {
      super(raw);
    }

    @Override
    public PfamType value() {
      return graph().Pfam();
    }

    @Override
    public Pfam<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Pfam<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Pfam<I,RV,RVT,RE,RET>, PfamType, name, String> {
      public name() {
        super(PfamType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtVertexProperty<Pfam<I,RV,RVT,RE,RET>, PfamType, id, String> {
      public id() {
        super(PfamType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class PubmedType
  extends
  UniProtVertexType<
  Pubmed<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PubmedType
  > {

    public final id id = new id();

    public PubmedType(RVT raw) {
      super(raw);
    }

    @Override
    public PubmedType value() {
      return graph().Pubmed();
    }

    @Override
    public Pubmed<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Pubmed<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<Pubmed<I,RV,RVT,RE,RET>, PubmedType, id, String> {
      public id() {
        super(PubmedType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class PIRType
  extends
  UniProtVertexType<
  PIR<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PIRType
  > {

    public final id id = new id();
    public final entryName entryName = new entryName();

    public PIRType(RVT raw) {
      super(raw);
    }

    @Override
    public PIRType value() {
      return graph().PIR();
    }

    @Override
    public PIR<I,RV,RVT,RE,RET> from(RV vertex) {
      return new PIR<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<PIR<I,RV,RVT,RE,RET>, PIRType, id, String> {
      public id() {
        super(PIRType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class entryName
    extends
    UniProtVertexProperty<PIR<I,RV,RVT,RE,RET>, PIRType, entryName, String> {
      public entryName() {
        super(PIRType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }


  public final class ProteinType
    extends
      UniProtVertexType <
        Protein<I,RV,RVT,RE,RET>,
        UniProtGraph<I,RV,RVT,RE,RET>.ProteinType
      >
  {

    public final accession accession  = new accession();
    public final entryName entryName  = new entryName();
    public final shortName shortName  = new shortName();
    public final fullName fullName    = new fullName();
    public final sequence sequence    = new sequence();
    public final length length        = new length();
    public final mass mass            = new mass();

    public final version version            = new version();
    public final createdDate createdDate    = new createdDate();
    public final modifiedDate modifiedDate  = new modifiedDate();

    public final uniRef100ClusterId uniRef100ClusterId  = new uniRef100ClusterId();
    public final uniRef90ClusterId uniRef90ClusterId    = new uniRef90ClusterId();
    public final uniRef50ClusterId uniRef50ClusterId    = new uniRef50ClusterId();

    public ProteinType(RVT raw) {
      super(raw);
    }

    @Override
    public final ProteinType value() {
      return graph().Protein();
    }

    @Override
    public final Protein<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Protein<I,RV,RVT,RE,RET>(vertex, this);
    }

    /* This property is always present, and identifies the protein. */
    public final class accession
      extends
        UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, accession, String>
    {
      public accession() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    /* This property is **optional**. */
    public final class shortName
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, shortName, String> {
      public shortName() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class sequence
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, sequence, String> {
      public sequence() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    /* This property is **always present**. It is normally larger and more descriptive than `name`. */
    public final class fullName
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, fullName, String> {
      public fullName() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class entryName
      extends
        UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, entryName, String>
    {
      public entryName() { super(ProteinType.this); }
      public Class<String> valueClass() { return String.class; }
    }
    public final class modifiedDate
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, modifiedDate, Date> {
      public modifiedDate() {
        super(ProteinType.this);
      }

      public Class<Date> valueClass() {
        return Date.class;
      }
    }
    public final class createdDate
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, createdDate, Date> {
      public createdDate() {
        super(ProteinType.this);
      }

      public Class<Date> valueClass() {
        return Date.class;
      }
    }
    public final class mass
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, mass, Integer> {
      public mass() {
        super(ProteinType.this);
      }
      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class version
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, version, Integer> {
      public version() {
        super(ProteinType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class length
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, length, Integer> {
      public length() {
        super(ProteinType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class uniRef100ClusterId
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, uniRef100ClusterId, String> {
      public uniRef100ClusterId() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class uniRef90ClusterId
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, uniRef90ClusterId, String> {
      public uniRef90ClusterId() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class uniRef50ClusterId
    extends
    UniProtVertexProperty<Protein<I,RV,RVT,RE,RET>, ProteinType, uniRef50ClusterId, String> {
      public uniRef50ClusterId() {
        super(ProteinType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class PublisherType
  extends
  UniProtVertexType<
  Publisher<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.PublisherType
  > {

    public final name name = new name();

    public PublisherType(RVT raw) {
      super(raw);
    }

    @Override
    public PublisherType value() {
      return graph().Publisher();
    }

    @Override
    public Publisher<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Publisher<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Publisher<I,RV,RVT,RE,RET>, PublisherType, name, String> {
      public name() {
        super(PublisherType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ReactomeTermType
  extends
  UniProtVertexType<
  ReactomeTerm<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.ReactomeTermType
  > {

    public final id id = new id();
    public final pathwayName pathwayName = new pathwayName();

    public ReactomeTermType(RVT raw) {
      super(raw);
    }

    @Override
    public ReactomeTermType value() {
      return graph().ReactomeTerm();
    }

    @Override
    public ReactomeTerm<I,RV,RVT,RE,RET> from(RV vertex) {
      return new ReactomeTerm<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<ReactomeTerm<I,RV,RVT,RE,RET>, ReactomeTermType, id, String> {
      public id() {
        super(ReactomeTermType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class pathwayName
    extends
    UniProtVertexProperty<ReactomeTerm<I,RV,RVT,RE,RET>, ReactomeTermType, pathwayName, String> {
      public pathwayName() {
        super(ReactomeTermType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ReferenceType
  extends
  UniProtVertexType<
  Reference<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType
  > {

    public final id id = new id();
    public final date date = new date();

    public ReferenceType(RVT raw) {
      super(raw);
    }

    @Override
    public ReferenceType value() {
      return graph().Reference();
    }

    @Override
    public Reference<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Reference<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<Reference<I,RV,RVT,RE,RET>, ReferenceType, id, String> {
      public id() {
        super(ReferenceType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class date
    extends
    UniProtVertexProperty<Reference<I,RV,RVT,RE,RET>, ReferenceType, date, String> {
      public date() {
        super(ReferenceType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class RefSeqType
  extends
  UniProtVertexType<
  RefSeq<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.RefSeqType
  > {

    public final id id = new id();
    public final nucleotideSequenceId nucleotideSequenceId = new nucleotideSequenceId();

    public RefSeqType(RVT raw) {
      super(raw);
    }

    @Override
    public RefSeqType value() {
      return graph().RefSeq();
    }

    @Override
    public RefSeq<I,RV,RVT,RE,RET> from(RV vertex) {
      return new RefSeq<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<RefSeq<I,RV,RVT,RE,RET>, RefSeqType, id, String> {
      public id() {
        super(RefSeqType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class nucleotideSequenceId
    extends
    UniProtVertexProperty<RefSeq<I,RV,RVT,RE,RET>, RefSeqType, nucleotideSequenceId, String> {
      public nucleotideSequenceId() {
        super(RefSeqType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class SequenceCautionType
  extends
  UniProtVertexType<
  SequenceCaution<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.SequenceCautionType
  > {

    public final name name = new name();

    public SequenceCautionType(RVT raw) {
      super(raw);
    }

    @Override
    public SequenceCautionType value() {
      return graph().SequenceCaution();
    }

    @Override
    public SequenceCaution<I,RV,RVT,RE,RET> from(RV vertex) {
      return new SequenceCaution<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType, name, String> {
      public name() {
        super(SequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class SubcellularLocationType
  extends
  UniProtVertexType<
  SubcellularLocation<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.SubcellularLocationType
  > {

    public final name name = new name();

    public SubcellularLocationType(RVT raw) {
      super(raw);
    }

    @Override
    public SubcellularLocationType value() {
      return graph().SubcellularLocation();
    }

    @Override
    public SubcellularLocation<I,RV,RVT,RE,RET> from(RV vertex) {
      return new SubcellularLocation<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType, name, String> {
      public name() {
        super(SubcellularLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class SubmissionType
  extends
  UniProtVertexType<
  Submission<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.SubmissionType
  > {

    public final title title = new title();

    public SubmissionType(RVT raw) {
      super(raw);
    }

    @Override
    public SubmissionType value() {
      return graph().Submission();
    }

    @Override
    public Submission<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Submission<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class title
    extends
    UniProtVertexProperty<Submission<I,RV,RVT,RE,RET>, SubmissionType, title, String> {
      public title() {
        super(SubmissionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class TaxonType
  extends
  UniProtVertexType<
  Taxon<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.TaxonType
  > {

    public final name name = new name();

    public TaxonType(RVT raw) {
      super(raw);
    }

    @Override
    public TaxonType value() {
      return graph().Taxon();
    }

    @Override
    public Taxon<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Taxon<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class name
    extends
    UniProtVertexProperty<Taxon<I,RV,RVT,RE,RET>, TaxonType, name, String> {
      public name() {
        super(TaxonType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class ThesisType
  extends
  UniProtVertexType<
  Thesis<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.ThesisType
  > {

    public final title title = new title();

    public ThesisType(RVT raw) {
      super(raw);
    }

    @Override
    public ThesisType value() {
      return graph().Thesis();
    }

    @Override
    public Thesis<I,RV,RVT,RE,RET> from(RV vertex) {
      return new Thesis<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class title
    extends
    UniProtVertexProperty<Thesis<I,RV,RVT,RE,RET>, ThesisType, title, String> {
      public title() {
        super(ThesisType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class UniGeneType
  extends
  UniProtVertexType<
  UniGene<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.UniGeneType
  > {

    public final id id = new id();

    public UniGeneType(RVT raw) {
      super(raw);
    }

    @Override
    public UniGeneType value() {
      return graph().UniGene();
    }

    @Override
    public UniGene<I,RV,RVT,RE,RET> from(RV vertex) {
      return new UniGene<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class id
    extends
    UniProtVertexProperty<UniGene<I,RV,RVT,RE,RET>, UniGeneType, id, String> {
      public id() {
        super(UniGeneType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class UnpublishedObservationType
  extends
  UniProtVertexType<
  UnpublishedObservation<I,RV,RVT,RE,RET>,
  UniProtGraph<I,RV,RVT,RE,RET>.UnpublishedObservationType
  > {

    public final scope scope = new scope();

    public UnpublishedObservationType(RVT raw) {
      super(raw);
    }

    @Override
    public UnpublishedObservationType value() {
      return graph().UnpublishedObservation();
    }

    @Override
    public UnpublishedObservation<I,RV,RVT,RE,RET> from(RV vertex) {
      return new UnpublishedObservation<I,RV,RVT,RE,RET>(vertex, this);
    }

    public final class scope
    extends
    UniProtVertexProperty<UnpublishedObservation<I,RV,RVT,RE,RET>, UnpublishedObservationType, scope, String> {
      public scope() {
        super(UnpublishedObservationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }


  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Edge types

  public final class ArticleJournalType
  extends
  UniProtEdgeType<
  Article<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ArticleType,
  ArticleJournal<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ArticleJournalType,
  Journal<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.JournalType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public ArticleJournalType(RET raw) {
      super(UniProtGraph.this.Article(), raw, UniProtGraph.this.Journal());
    }

    @Override
    public ArticleJournalType value() {
      return graph().ArticleJournal();
    }

    @Override
    public ArticleJournal<I,RV,RVT,RE,RET> from(RE edge) {
      return new ArticleJournal<I,RV,RVT,RE,RET>(edge, this);
    }

    public final volume volume = new volume();
    public final first first = new first();
    public final last last = new last();

    public final class volume
    extends
    UniProtEdgeProperty<
    Article<I,RV,RVT,RE,RET>, ArticleType,
    ArticleJournal<I,RV,RVT,RE,RET>, ArticleJournalType,
    Journal<I,RV,RVT,RE,RET>, JournalType,
    volume, String
    >
    {
      public volume() {
        super(ArticleJournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class first
    extends
    UniProtEdgeProperty<
    Article<I,RV,RVT,RE,RET>, ArticleType,
    ArticleJournal<I,RV,RVT,RE,RET>, ArticleJournalType,
    Journal<I,RV,RVT,RE,RET>, JournalType,
    first, String
    >
    {
      public first() {
        super(ArticleJournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class last
    extends
    UniProtEdgeProperty<
    Article<I,RV,RVT,RE,RET>, ArticleType,
    ArticleJournal<I,RV,RVT,RE,RET>, ArticleJournalType,
    Journal<I,RV,RVT,RE,RET>, JournalType,
    last, String
    >
    {
      public last() {
        super(ArticleJournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class ArticlePubmedType
  extends
  UniProtEdgeType<
  Article<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ArticleType,
  ArticlePubmed<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ArticlePubmedType,
  Pubmed<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PubmedType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ArticlePubmedType(RET raw) {
      super(UniProtGraph.this.Article(), raw, UniProtGraph.this.Pubmed());
    }

    @Override
    public ArticlePubmedType value() {
      return graph().ArticlePubmed();
    }

    @Override
    public ArticlePubmed<I,RV,RVT,RE,RET> from(RE edge) {
      return new ArticlePubmed<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class BookCityType
  extends
  UniProtEdgeType<
  Book<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookType,
  BookCity<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookCityType,
  City<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.CityType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public BookCityType(RET raw) {
      super(UniProtGraph.this.Book(), raw, UniProtGraph.this.City());
    }

    @Override
    public BookCityType value() {
      return graph().BookCity();
    }

    @Override
    public BookCity<I,RV,RVT,RE,RET> from(RE edge) {
      return new BookCity<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class BookEditorType
  extends
  UniProtEdgeType<
  Book<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookType,
  BookEditor<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookEditorType,
  Person<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PersonType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public BookEditorType(RET raw) {
      super(UniProtGraph.this.Book(), raw, UniProtGraph.this.Person());
    }

    @Override
    public BookEditorType value() {
      return graph().BookEditor();
    }

    @Override
    public BookEditor<I,RV,RVT,RE,RET> from(RE edge) {
      return new BookEditor<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class BookPublisherType
  extends
  UniProtEdgeType<
  Book<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookType,
  BookPublisher<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookPublisherType,
  Publisher<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PublisherType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public BookPublisherType(RET raw) {
      super(UniProtGraph.this.Book(), raw, UniProtGraph.this.Publisher());
    }

    @Override
    public BookPublisherType value() {
      return graph().BookPublisher();
    }

    @Override
    public BookPublisher<I,RV,RVT,RE,RET> from(RE edge) {
      return new BookPublisher<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class InstituteCountryType
  extends
  UniProtEdgeType<
  Institute<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.InstituteType,
  InstituteCountry<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.InstituteCountryType,
  Country<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.CountryType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public InstituteCountryType(RET raw) {
      super(UniProtGraph.this.Institute(), raw, UniProtGraph.this.Country());
    }

    @Override
    public InstituteCountryType value() {
      return graph().InstituteCountry();
    }

    @Override
    public InstituteCountry<I,RV,RVT,RE,RET> from(RE edge) {
      return new InstituteCountry<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class OnlineArticleOnlineJournalType
  extends
  UniProtEdgeType<
  OnlineArticle<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OnlineArticleType,
  OnlineArticleOnlineJournal<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OnlineArticleOnlineJournalType,
  OnlineJournal<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OnlineJournalType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public OnlineArticleOnlineJournalType(RET raw) {
      super(UniProtGraph.this.OnlineArticle(), raw, UniProtGraph.this.OnlineJournal());
    }

    @Override
    public OnlineArticleOnlineJournalType value() {
      return graph().OnlineArticleOnlineJournal();
    }

    @Override
    public OnlineArticleOnlineJournal<I,RV,RVT,RE,RET> from(RE edge) {
      return new OnlineArticleOnlineJournal<I,RV,RVT,RE,RET>(edge, this);
    }

    public final locator locator = new locator();

    public final class locator
    extends
    UniProtEdgeProperty<
    OnlineArticle<I,RV,RVT,RE,RET>, OnlineArticleType,
    OnlineArticleOnlineJournal<I,RV,RVT,RE,RET>, OnlineArticleOnlineJournalType,
    OnlineJournal<I,RV,RVT,RE,RET>, OnlineJournalType,
    locator, String
    >
    {
      public locator() {
        super(OnlineArticleOnlineJournalType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class OrganismTaxonType
  extends
  UniProtEdgeType<
  Organism<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OrganismType,
  OrganismTaxon<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OrganismTaxonType,
  Taxon<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.TaxonType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public OrganismTaxonType(RET raw) {
      super(UniProtGraph.this.Organism(), raw, UniProtGraph.this.Taxon());
    }

    @Override
    public OrganismTaxonType value() {
      return graph().OrganismTaxon();
    }

    @Override
    public OrganismTaxon<I,RV,RVT,RE,RET> from(RE edge) {
      return new OrganismTaxon<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinCommentType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinComment<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinCommentType,
  CommentType<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.CommentTypeType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinCommentType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.CommentType());
    }

    @Override
    public ProteinCommentType value() {
      return graph().ProteinComment();
    }

    @Override
    public ProteinComment<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinComment<I,RV,RVT,RE,RET>(edge, this);
    }

    public final redoxPotentialEvidence redoxPotentialEvidence = new redoxPotentialEvidence();
    public final redoxPotential redoxPotential = new redoxPotential();
    public final absorptionText absorptionText = new absorptionText();
    public final absorptionMax absorptionMax = new absorptionMax();
    public final kineticsXML kineticsXML = new kineticsXML();
    public final phDependence phDependence = new phDependence();
    public final temperatureDependence temperatureDependence = new temperatureDependence();
    public final text text = new text();
    public final status status = new status();
    public final evidence evidence = new evidence();
    public final begin begin = new begin();
    public final end end = new end();
    public final method method = new method();
    public final mass mass = new mass();
    public final position position = new position();


    public final class redoxPotentialEvidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    redoxPotentialEvidence, String
    >
    {
      public redoxPotentialEvidence() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class redoxPotential
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    redoxPotential, String
    >
    {
      public redoxPotential() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class absorptionText
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    absorptionText, String
    >
    {
      public absorptionText() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class absorptionMax
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    absorptionMax, String
    >
    {
      public absorptionMax() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class kineticsXML
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    kineticsXML, String
    >
    {
      public kineticsXML() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class phDependence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    phDependence, String
    >
    {
      public phDependence() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class temperatureDependence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    temperatureDependence, String
    >
    {
      public temperatureDependence() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class position
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    position, String
    >
    {
      public position() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class mass
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    mass, String
    >
    {
      public mass() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class method
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    method, String
    >
    {
      public method() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class end
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    end, Integer
    >
    {
      public end() {
        super(ProteinCommentType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class begin
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    begin, Integer
    >
    {
      public begin() {
        super(ProteinCommentType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class text
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    text, String
    >
    {
      public text() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class status
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    status, String
    >
    {
      public status() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class evidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinComment<I,RV,RVT,RE,RET>, ProteinCommentType,
    CommentType<I,RV,RVT,RE,RET>, CommentTypeType,
    evidence, String
    >
    {
      public evidence() {
        super(ProteinCommentType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinDatasetType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinDataset<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinDatasetType,
  Dataset<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.DatasetType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public ProteinDatasetType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Dataset());
    }

    @Override
    public ProteinDatasetType value() {
      return graph().ProteinDataset();
    }

    @Override
    public ProteinDataset<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinDataset<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinDiseaseType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinDisease<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinDiseaseType,
  Disease<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.DiseaseType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final text text = new text();
    public final status status = new status();
    public final evidence evidence = new evidence();

    public ProteinDiseaseType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Disease());
    }

    @Override
    public ProteinDiseaseType value() {
      return graph().ProteinDisease();
    }

    @Override
    public ProteinDisease<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinDisease<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class text
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinDisease<I,RV,RVT,RE,RET>, ProteinDiseaseType,
    Disease<I,RV,RVT,RE,RET>, DiseaseType,
    text, String
    >
    {
      public text() {
        super(ProteinDiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class status
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinDisease<I,RV,RVT,RE,RET>, ProteinDiseaseType,
    Disease<I,RV,RVT,RE,RET>, DiseaseType,
    status, String
    >
    {
      public status() {
        super(ProteinDiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class evidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinDisease<I,RV,RVT,RE,RET>, ProteinDiseaseType,
    Disease<I,RV,RVT,RE,RET>, DiseaseType,
    evidence, String
    >
    {
      public evidence() {
        super(ProteinDiseaseType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinEMBLType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinEMBL<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinEMBLType,
  EMBL<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.EMBLType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinEMBLType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.EMBL());
    }

    @Override
    public ProteinEMBLType value() {
      return graph().ProteinEMBL();
    }

    @Override
    public ProteinEMBL<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinEMBL<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinEnsemblType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinEnsembl<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinEnsemblType,
  Ensembl<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.EnsemblType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinEnsemblType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Ensembl());
    }

    @Override
    public ProteinEnsemblType value() {
      return graph().ProteinEnsembl();
    }

    @Override
    public ProteinEnsembl<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinEnsembl<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinFeatureType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinFeature<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinFeatureType,
  FeatureType<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.FeatureTypeType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final description description = new description();
    public final id id = new id();
    public final evidence evidence = new evidence();
    public final status status = new status();
    public final begin begin = new begin();
    public final end end = new end();
    public final original original = new original();
    public final variation variation = new variation();
    public final ref ref = new ref();

    public ProteinFeatureType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.FeatureType());
    }

    @Override
    public ProteinFeatureType value() {
      return graph().ProteinFeature();
    }

    @Override
    public ProteinFeature<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinFeature<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class description
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    description, String
    >
    {
      public description() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    id, String
    >
    {
      public id() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class evidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    evidence, String
    >
    {
      public evidence() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class status
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    status, String
    >
    {
      public status() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class begin
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    begin, Integer
    >
    {
      public begin() {
        super(ProteinFeatureType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class end
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    end, Integer
    >
    {
      public end() {
        super(ProteinFeatureType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class original
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    original, String
    >
    {
      public original() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class variation
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    variation, String
    >
    {
      public variation() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class ref
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinFeature<I,RV,RVT,RE,RET>, ProteinFeatureType,
    FeatureType<I,RV,RVT,RE,RET>, FeatureTypeType,
    ref, String
    >
    {
      public ref() {
        super(ProteinFeatureType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinGeneLocationType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinGeneLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinGeneLocationType,
  GeneLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.GeneLocationType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final name name = new name();

    public ProteinGeneLocationType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.GeneLocation());
    }

    @Override
    public ProteinGeneLocationType value() {
      return graph().ProteinGeneLocation();
    }

    @Override
    public ProteinGeneLocation<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinGeneLocation<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class name
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinGeneLocation<I,RV,RVT,RE,RET>, ProteinGeneLocationType,
    GeneLocation<I,RV,RVT,RE,RET>, GeneLocationType,
    name, String
    >
    {
      public name() {
        super(ProteinGeneLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class ProteinGeneNameType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinGeneName<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinGeneNameType,
  GeneName<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.GeneNameType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final geneNameType geneNameType = new geneNameType();

    public ProteinGeneNameType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.GeneName());
    }

    @Override
    public ProteinGeneNameType value() {
      return graph().ProteinGeneName();
    }

    @Override
    public ProteinGeneName<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinGeneName<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class geneNameType
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinGeneName<I,RV,RVT,RE,RET>, ProteinGeneNameType,
    GeneName<I,RV,RVT,RE,RET>, GeneNameType,
    geneNameType, String
    >
    {
      public geneNameType() {
        super(ProteinGeneNameType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

  }

  public final class IsoformEventGeneratorType
  extends
  UniProtEdgeType<
  Isoform<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.IsoformType,
  IsoformEventGenerator<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.IsoformEventGeneratorType,
  AlternativeProduct<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.AlternativeProductType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public IsoformEventGeneratorType(RET raw) {
      super(UniProtGraph.this.Isoform(), raw, UniProtGraph.this.AlternativeProduct());
    }

    @Override
    public IsoformEventGeneratorType value() {
      return graph().IsoformEventGenerator();
    }

    @Override
    public IsoformEventGenerator<I,RV,RVT,RE,RET> from(RE edge) {
      return new IsoformEventGenerator<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinInterProType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinInterPro<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinInterProType,
  InterPro<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.InterProType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinInterProType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.InterPro());
    }

    @Override
    public ProteinInterProType value() {
      return graph().ProteinInterPro();
    }

    @Override
    public ProteinInterPro<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinInterPro<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinKeggType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinKegg<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinKeggType,
  Kegg<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.KeggType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinKeggType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Kegg());
    }

    @Override
    public ProteinKeggType value() {
      return graph().ProteinKegg();
    }

    @Override
    public ProteinKegg<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinKegg<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinKeywordType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinKeyword<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinKeywordType,
  Keyword<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.KeywordType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinKeywordType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Keyword());
    }

    @Override
    public ProteinKeywordType value() {
      return graph().ProteinKeyword();
    }

    @Override
    public ProteinKeyword<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinKeyword<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinOrganismType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinOrganism<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinOrganismType,
  Organism<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OrganismType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public ProteinOrganismType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Organism());
    }

    @Override
    public ProteinOrganismType value() {
      return graph().ProteinOrganism();
    }

    @Override
    public ProteinOrganism<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinOrganism<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinPfamType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinPfam<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinPfamType,
  Pfam<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PfamType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinPfamType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Pfam());
    }

    @Override
    public ProteinPfamType value() {
      return graph().ProteinPfam();
    }

    @Override
    public ProteinPfam<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinPfam<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinPIRType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinPIR<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinPIRType,
  PIR<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PIRType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinPIRType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.PIR());
    }

    @Override
    public ProteinPIRType value() {
      return graph().ProteinPIR();
    }

    @Override
    public ProteinPIR<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinPIR<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  /*
    This edge corresponds to a protein-protein interaction, as found in the comments section of the UniProt XML.

    By direct inspection of UniProt XML file I have concluded that:

    1. the source id is always a protein, I don't know why
    2. target can be either an isoform or a protein, and the id is in `interactant/id`. Isoform ids *look* to be `${protein.id}-{number}`

    #### Remark

    Again based on manual inspection it looks like protein-protein interactions are **not** *duplicated* in the UniProt XML file. I do not know if this implies a semantic direction or not. So, if you want to get all proteins with which a given protein interacts, you need to get both inV and outV of protein-protein interaction edges.
  */
  public final class ProteinProteinInteractionType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinProteinInteraction<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinProteinInteractionType,
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinProteinInteractionType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Protein());
    }

    @Override
    public ProteinProteinInteractionType value() {
      return graph().ProteinProteinInteraction();
    }

    @Override
    public ProteinProteinInteraction<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinProteinInteraction<I,RV,RVT,RE,RET>(edge, this);
    }

    public final experiments experiments = new experiments();
    public final organismsDiffer organismsDiffer = new organismsDiffer();
    public final intActId1 intActId1 = new intActId1();
    public final intActId2 intActId2 = new intActId2();

    public final class experiments
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinProteinInteraction<I,RV,RVT,RE,RET>, ProteinProteinInteractionType,
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    experiments, Integer
    >
    {
      public experiments() {
        super(ProteinProteinInteractionType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class organismsDiffer
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinProteinInteraction<I,RV,RVT,RE,RET>, ProteinProteinInteractionType,
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    organismsDiffer, Boolean
    >
    {
      public organismsDiffer() {
        super(ProteinProteinInteractionType.this);
      }

      public Class<Boolean> valueClass() {
        return Boolean.class;
      }
    }
    public final class intActId1
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinProteinInteraction<I,RV,RVT,RE,RET>, ProteinProteinInteractionType,
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    intActId1, String
    >
    {
      public intActId1() {
        super(ProteinProteinInteractionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class intActId2
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinProteinInteraction<I,RV,RVT,RE,RET>, ProteinProteinInteractionType,
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    intActId2, String
    >
    {
      public intActId2() {
        super(ProteinProteinInteractionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinIsoformType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinIsoform<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinIsoformType,
  Isoform<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.IsoformType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinIsoformType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Isoform());
    }

    @Override
    public ProteinIsoformType value() {
      return graph().ProteinIsoform();
    }

    @Override
    public ProteinIsoform<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinIsoform<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  /*
    This edge corresponds to a protein-isoform interaction, as found in the comments section of the UniProt XML.

    By direct inspection of UniProt XML file we have concluded that:

    1. the source id is always a protein, I don't know why
    2. target can be either an isoform or a protein, and the id is in `interactant/id`. Isoform ids *look* to be `${protein.id}-{number}`

    We don't know if there are interactions between isoforms.
  */
  public final class ProteinIsoformInteractionType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinIsoformInteraction<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinIsoformInteractionType,
  Isoform<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.IsoformType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinIsoformInteractionType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Isoform());
    }

    @Override
    public ProteinIsoformInteractionType value() {
      return graph().ProteinIsoformInteraction();
    }

    @Override
    public ProteinIsoformInteraction<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinIsoformInteraction<I,RV,RVT,RE,RET>(edge, this);
    }

    public final experiments experiments = new experiments();
    public final organismsDiffer organismsDiffer = new organismsDiffer();
    public final intActId1 intActId1 = new intActId1();
    public final intActId2 intActId2 = new intActId2();

    public final class experiments
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinIsoformInteraction<I,RV,RVT,RE,RET>, ProteinIsoformInteractionType,
    Isoform<I,RV,RVT,RE,RET>, IsoformType,
    experiments, Integer
    >
    {
      public experiments() {
        super(ProteinIsoformInteractionType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
    public final class organismsDiffer
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinIsoformInteraction<I,RV,RVT,RE,RET>, ProteinIsoformInteractionType,
    Isoform<I,RV,RVT,RE,RET>, IsoformType,
    organismsDiffer, Boolean
    >
    {
      public organismsDiffer() {
        super(ProteinIsoformInteractionType.this);
      }

      public Class<Boolean> valueClass() {
        return Boolean.class;
      }
    }
    public final class intActId1
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinIsoformInteraction<I,RV,RVT,RE,RET>, ProteinIsoformInteractionType,
    Isoform<I,RV,RVT,RE,RET>, IsoformType,
    intActId1, String
    >
    {
      public intActId1() {
        super(ProteinIsoformInteractionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class intActId2
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinIsoformInteraction<I,RV,RVT,RE,RET>, ProteinIsoformInteractionType,
    Isoform<I,RV,RVT,RE,RET>, IsoformType,
    intActId2, String
    >
    {
      public intActId2() {
        super(ProteinIsoformInteractionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinReactomeTermType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinReactomeTerm<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinReactomeTermType,
  ReactomeTerm<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReactomeTermType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinReactomeTermType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.ReactomeTerm());
    }

    @Override
    public ProteinReactomeTermType value() {
      return graph().ProteinReactomeTerm();
    }

    @Override
    public ProteinReactomeTerm<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinReactomeTerm<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinReferenceType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinReference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinReferenceType,
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinReferenceType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.Reference());
    }

    @Override
    public ProteinReferenceType value() {
      return graph().ProteinReference();
    }

    @Override
    public ProteinReference<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinReference<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinRefSeqType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinRefSeq<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinRefSeqType,
  RefSeq<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.RefSeqType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinRefSeqType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.RefSeq());
    }

    @Override
    public ProteinRefSeqType value() {
      return graph().ProteinRefSeq();
    }

    @Override
    public ProteinRefSeq<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinRefSeq<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinSequenceCautionType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinSequenceCaution<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinSequenceCautionType,
  SequenceCaution<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SequenceCautionType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final evidence evidence = new evidence();
    public final status status = new status();
    public final text text = new text();
    public final id id = new id();
    public final resource resource = new resource();
    public final version version = new version();
    public final position position = new position();

    public ProteinSequenceCautionType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.SequenceCaution());
    }

    @Override
    public ProteinSequenceCautionType value() {
      return graph().ProteinSequenceCaution();
    }

    @Override
    public ProteinSequenceCaution<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinSequenceCaution<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class position
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    position, String
    >
    {
      public position() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class version
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    version, String
    >
    {
      public version() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class resource
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    resource, String
    >
    {
      public resource() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class id
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    id, String
    >
    {
      public id() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class evidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    evidence, String
    >
    {
      public evidence() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class status
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    status, String
    >
    {
      public status() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class text
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSequenceCaution<I,RV,RVT,RE,RET>, ProteinSequenceCautionType,
    SequenceCaution<I,RV,RVT,RE,RET>, SequenceCautionType,
    text, String
    >
    {
      public text() {
        super(ProteinSequenceCautionType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
  }

  public final class ProteinSubcellularLocationType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinSubcellularLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinSubcellularLocationType,
  SubcellularLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubcellularLocationType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public final evidence evidence = new evidence();
    public final status status = new status();
    public final topology topology = new topology();
    public final topologyStatus topologyStatus = new topologyStatus();

    public final class topologyStatus
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSubcellularLocation<I,RV,RVT,RE,RET>, ProteinSubcellularLocationType,
    SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType,
    topologyStatus, String
    >
    {
      public topologyStatus() {
        super(ProteinSubcellularLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class topology
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSubcellularLocation<I,RV,RVT,RE,RET>, ProteinSubcellularLocationType,
    SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType,
    topology, String
    >
    {
      public topology() {
        super(ProteinSubcellularLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class evidence
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSubcellularLocation<I,RV,RVT,RE,RET>, ProteinSubcellularLocationType,
    SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType,
    evidence, String
    >
    {
      public evidence() {
        super(ProteinSubcellularLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }
    public final class status
    extends
    UniProtEdgeProperty<
    Protein<I,RV,RVT,RE,RET>, ProteinType,
    ProteinSubcellularLocation<I,RV,RVT,RE,RET>, ProteinSubcellularLocationType,
    SubcellularLocation<I,RV,RVT,RE,RET>, SubcellularLocationType,
    status, String
    >
    {
      public status() {
        super(ProteinSubcellularLocationType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

    public ProteinSubcellularLocationType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.SubcellularLocation());
    }

    @Override
    public ProteinSubcellularLocationType value() {
      return graph().ProteinSubcellularLocation();
    }

    @Override
    public ProteinSubcellularLocation<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinSubcellularLocation<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ProteinUniGeneType
  extends
  UniProtEdgeType<
  Protein<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinType,
  ProteinUniGene<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ProteinUniGeneType,
  UniGene<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.UniGeneType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ProteinUniGeneType(RET raw) {
      super(UniProtGraph.this.Protein(), raw, UniProtGraph.this.UniGene());
    }

    @Override
    public ProteinUniGeneType value() {
      return graph().ProteinUniGene();
    }

    @Override
    public ProteinUniGene<I,RV,RVT,RE,RET> from(RE edge) {
      return new ProteinUniGene<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceAuthorConsortiumType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceAuthorConsortium<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceAuthorConsortiumType,
  Consortium<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ConsortiumType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ReferenceAuthorConsortiumType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Consortium());
    }

    @Override
    public ReferenceAuthorConsortiumType value() {
      return graph().ReferenceAuthorConsortium();
    }

    @Override
    public ReferenceAuthorConsortium<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceAuthorConsortium<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceAuthorPersonType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceAuthorPerson<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceAuthorPersonType,
  Person<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PersonType
  >
  implements
  TypedEdge.Type.ManyToMany {

    public ReferenceAuthorPersonType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Person());
    }

    @Override
    public ReferenceAuthorPersonType value() {
      return graph().ReferenceAuthorPerson();
    }

    @Override
    public ReferenceAuthorPerson<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceAuthorPerson<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceArticleType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceArticle<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceArticleType,
  Article<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ArticleType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferenceArticleType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Article());
    }

    @Override
    public ReferenceArticleType value() {
      return graph().ReferenceArticle();
    }

    @Override
    public ReferenceArticle<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceArticle<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceBookType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceBook<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceBookType,
  Book<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.BookType
  >
  implements
  TypedEdge.Type.OneToOne {

    public final title title = new title();
    public final first first = new first();
    public final last last = new last();
    public final volume volume = new volume();

    public ReferenceBookType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Book());
    }

    @Override
    public ReferenceBookType value() {
      return graph().ReferenceBook();
    }

    @Override
    public ReferenceBook<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceBook<I,RV,RVT,RE,RET>(edge, this);
    }

    public final class title
    extends
    UniProtEdgeProperty<
    Reference<I,RV,RVT,RE,RET>, ReferenceType,
    ReferenceBook<I,RV,RVT,RE,RET>, ReferenceBookType,
    Book<I,RV,RVT,RE,RET>, BookType,
    title, String
    >
    {
      public title() {
        super(ReferenceBookType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

    public final class volume
    extends
    UniProtEdgeProperty<
    Reference<I,RV,RVT,RE,RET>, ReferenceType,
    ReferenceBook<I,RV,RVT,RE,RET>, ReferenceBookType,
    Book<I,RV,RVT,RE,RET>, BookType,
    volume, String
    >
    {
      public volume() {
        super(ReferenceBookType.this);
      }

      public Class<String> valueClass() {
        return String.class;
      }
    }

    public final class first
    extends
    UniProtEdgeProperty<
    Reference<I,RV,RVT,RE,RET>, ReferenceType,
    ReferenceBook<I,RV,RVT,RE,RET>, ReferenceBookType,
    Book<I,RV,RVT,RE,RET>, BookType,
    first, Integer
    >
    {
      public first() {
        super(ReferenceBookType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }

    public final class last
    extends
    UniProtEdgeProperty<
    Reference<I,RV,RVT,RE,RET>, ReferenceType,
    ReferenceBook<I,RV,RVT,RE,RET>, ReferenceBookType,
    Book<I,RV,RVT,RE,RET>, BookType,
    last, Integer
    >
    {
      public last() {
        super(ReferenceBookType.this);
      }

      public Class<Integer> valueClass() {
        return Integer.class;
      }
    }
  }

  public final class ReferencePatentType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferencePatent<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferencePatentType,
  Patent<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.PatentType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferencePatentType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Patent());
    }

    @Override
    public ReferencePatentType value() {
      return graph().ReferencePatent();
    }

    @Override
    public ReferencePatent<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferencePatent<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceThesisType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceThesis<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceThesisType,
  Thesis<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ThesisType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferenceThesisType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Thesis());
    }

    @Override
    public ReferenceThesisType value() {
      return graph().ReferenceThesis();
    }

    @Override
    public ReferenceThesis<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceThesis<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceSubmissionType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceSubmission<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceSubmissionType,
  Submission<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubmissionType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferenceSubmissionType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.Submission());
    }

    @Override
    public ReferenceSubmissionType value() {
      return graph().ReferenceSubmission();
    }

    @Override
    public ReferenceSubmission<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceSubmission<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceUnpublishedObservationType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceUnpublishedObservation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceUnpublishedObservationType,
  UnpublishedObservation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.UnpublishedObservationType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferenceUnpublishedObservationType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.UnpublishedObservation());
    }

    @Override
    public ReferenceUnpublishedObservationType value() {
      return graph().ReferenceUnpublishedObservation();
    }

    @Override
    public ReferenceUnpublishedObservation<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceUnpublishedObservation<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ReferenceOnlineArticleType
  extends
  UniProtEdgeType<
  Reference<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceType,
  ReferenceOnlineArticle<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ReferenceOnlineArticleType,
  OnlineArticle<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.OnlineArticleType
  >
  implements
  TypedEdge.Type.OneToOne {

    public ReferenceOnlineArticleType(RET raw) {
      super(UniProtGraph.this.Reference(), raw, UniProtGraph.this.OnlineArticle());
    }

    @Override
    public ReferenceOnlineArticleType value() {
      return graph().ReferenceOnlineArticle();
    }

    @Override
    public ReferenceOnlineArticle<I,RV,RVT,RE,RET> from(RE edge) {
      return new ReferenceOnlineArticle<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class SubcellularLocationParentType
  extends
  UniProtEdgeType<
  SubcellularLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubcellularLocationType,
  SubcellularLocationParent<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubcellularLocationParentType,
  SubcellularLocation<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubcellularLocationType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public SubcellularLocationParentType(RET raw) {
      super(UniProtGraph.this.SubcellularLocation(), raw, UniProtGraph.this.SubcellularLocation());
    }

    @Override
    public SubcellularLocationParentType value() {
      return graph().SubcellularLocationParent();
    }

    @Override
    public SubcellularLocationParent<I,RV,RVT,RE,RET> from(RE edge) {
      return new SubcellularLocationParent<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class SubmissionDBType
  extends
  UniProtEdgeType<
  Submission<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubmissionType,
  SubmissionDB<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.SubmissionDBType,
  DB<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.DBType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public SubmissionDBType(RET raw) {
      super(UniProtGraph.this.Submission(), raw, UniProtGraph.this.DB());
    }

    @Override
    public SubmissionDBType value() {
      return graph().SubmissionDB();
    }

    @Override
    public SubmissionDB<I,RV,RVT,RE,RET> from(RE edge) {
      return new SubmissionDB<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class TaxonParentType
  extends
  UniProtEdgeType<
  Taxon<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.TaxonType,
  TaxonParent<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.TaxonParentType,
  Taxon<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.TaxonType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public TaxonParentType(RET raw) {
      super(UniProtGraph.this.Taxon(), raw, UniProtGraph.this.Taxon());
    }

    @Override
    public TaxonParentType value() {
      return graph().TaxonParent();
    }

    @Override
    public TaxonParent<I,RV,RVT,RE,RET> from(RE edge) {
      return new TaxonParent<I,RV,RVT,RE,RET>(edge, this);
    }
  }

  public final class ThesisInstituteType
  extends
  UniProtEdgeType<
  Thesis<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ThesisType,
  ThesisInstitute<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.ThesisInstituteType,
  Institute<I,RV,RVT,RE,RET>, UniProtGraph<I,RV,RVT,RE,RET>.InstituteType
  >
  implements
  TypedEdge.Type.ManyToOne {

    public ThesisInstituteType(RET raw) {
      super(UniProtGraph.this.Thesis(), raw, UniProtGraph.this.Institute());
    }

    @Override
    public ThesisInstituteType value() {
      return graph().ThesisInstitute();
    }

    @Override
    public ThesisInstitute<I,RV,RVT,RE,RET> from(RE edge) {
      return new ThesisInstitute<I,RV,RVT,RE,RET>(edge, this);
    }
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // helper classes

  public abstract class UniProtVertexProperty<
    V extends UniProtVertex<V, VT, I, RV, RVT, RE, RET>,
    VT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<V, VT>,
    P extends UniProtVertexProperty<V, VT, P, PV>,
    PV
  >
  implements
    Property<V, VT, P, PV, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> {

    protected UniProtVertexProperty(VT type) { this.type = type; }

    private VT type;

    @Override
    public final VT elementType() { return type; }
  }

  public abstract class UniProtEdgeProperty<
  S extends UniProtVertex<S, ST, I, RV, RVT, RE, RET>,
  ST extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<S, ST>,
  E extends UniProtEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
  ET extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtEdgeType<S, ST, E, ET, T, TT>,
  T extends UniProtVertex<T, TT, I, RV, RVT, RE, RET>,
  TT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<T, TT>,
  P extends UniProtEdgeProperty <
  S, ST,
  E, ET,
  T, TT,
  P, PV
  >,
  PV
  >
  implements
  Property<E, ET, P, PV, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> {

    protected UniProtEdgeProperty(ET type) {

      this.type = type;
    }

    private ET type;

    @Override
    public final ET elementType() {
      return type;
    }
  }


  public abstract static class UniProtVertex<
  V extends UniProtVertex<V, VT, I, RV, RVT, RE, RET>,
  VT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<V, VT>,
  I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
  >
  implements
  TypedVertex<V, VT, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> {

    private RV vertex;
    private VT type;

    protected UniProtVertex(RV vertex, VT type) {

      this.vertex = vertex;
      this.type = type;
    }

    @Override
    public UniProtGraph<I,RV,RVT,RE,RET> graph() {
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

  abstract class UniProtVertexType<
  V extends UniProtVertex<V, VT, I, RV, RVT, RE, RET>,
  VT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<V, VT>
  >
  implements
  TypedVertex.Type<V, VT, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> {

    private RVT raw;

    protected UniProtVertexType(RVT raw) {
      this.raw = raw;
    }

    @Override
    public final RVT raw() {
      return raw;
    }

    @Override
    public final UniProtGraph<I,RV,RVT,RE,RET> graph() {
      return UniProtGraph.this;
    }
  }

  public abstract static class UniProtEdge<
  S extends UniProtVertex<S, ST, I, RV, RVT, RE, RET>,
  ST extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<S, ST>,
  E extends UniProtEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
  ET extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtEdgeType<S, ST, E, ET, T, TT>,
  T extends UniProtVertex<T, TT, I, RV, RVT, RE, RET>,
  TT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<T, TT>,
  I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
  >
  implements
  TypedEdge<
  S, ST, UniProtGraph<I,RV,RVT,RE,RET>,
  E, ET, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET,
  T, TT, UniProtGraph<I,RV,RVT,RE,RET>
  > {

    private RE edge;
    private ET type;

    protected UniProtEdge(RE edge, ET type) {

      this.edge = edge;
      this.type = type;
    }

    @Override
    public UniProtGraph<I,RV,RVT,RE,RET> graph() {
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

  abstract class UniProtEdgeType<
  S extends UniProtVertex<S, ST, I, RV, RVT, RE, RET>,
  ST extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<S, ST>,
  E extends UniProtEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
  ET extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtEdgeType<S, ST, E, ET, T, TT>,
  T extends UniProtVertex<T, TT, I, RV, RVT, RE, RET>,
  TT extends UniProtGraph<I,RV,RVT,RE,RET>.UniProtVertexType<T, TT>
  >
  implements
  TypedEdge.Type<
  S, ST, UniProtGraph<I,RV,RVT,RE,RET>,
  E, ET, UniProtGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET,
  T, TT, UniProtGraph<I,RV,RVT,RE,RET>
  > {

    private RET raw;
    private ST srcT;
    private TT tgtT;

    protected UniProtEdgeType(ST srcT, RET raw, TT tgtT) {

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
    public final UniProtGraph<I,RV,RVT,RE,RET> graph() {
      return UniProtGraph.this;
    }
  }


}
