
```java
package com.bio4j.model.uniprot.programs;

public final class XMLConstants {

  public static final String ENTRY_TAG_NAME                 = "entry";
  public static final String ENTRY_ACCESSION_TAG_NAME       = "accession";
  public static final String ENTRY_NAME_TAG_NAME            = "name";
  public static final String ENTRY_MODIFIED_DATE_ATTRIBUTE  = "modified";
  public static final String ENTRY_CREATED_DATE_ATTRIBUTE   = "created";
  public static final String ENTRY_VERSION_ATTRIBUTE        = "version";
  public static final String ENTRY_DATASET_ATTRIBUTE        = "dataset";
  public static final String ENTRY_SEQUENCE_TAG_NAME        = "sequence";

  public static final String KEYWORD_TAG_NAME     = "keyword";
  public static final String KEYWORD_ID_ATTRIBUTE = "id";

  public static final String REFERENCE_TAG_NAME = "reference";
  public static final String CITATION_TAG_NAME  = "citation";

  public static final String GENE_LOCATION_TAG_NAME = "geneLocation";

  public static final String ORGANISM_TAG_NAME              = "organism";
  public static final String ORGANISM_NAME_TAG_NAME         = "name";
  public static final String ORGANISM_NAME_TYPE_ATTRIBUTE   = "type";
  public static final String ORGANISM_SCIENTIFIC_NAME_TYPE  = "scientific";
  public static final String ORGANISM_COMMON_NAME_TYPE      = "common";
  public static final String ORGANISM_SYNONYM_NAME_TYPE     = "synonym";

  public static final String DB_REFERENCE_TAG_NAME          = "dbReference";
  public static final String DB_REFERENCE_TYPE_ATTRIBUTE    = "type";
  public static final String DB_REFERENCE_ID_ATTRIBUTE      = "id";
  public static final String DB_REFERENCE_VALUE_ATTRIBUTE   = "value";
  public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

  public static final String INTERPRO_DB_REFERENCE_TYPE = "InterPro";
  public static final String INTERPRO_ENTRY_NAME        = "entry name";

  public static final String GO_DB_REFERENCE_TYPE     = "GO";
  public static final String EVIDENCE_TYPE_ATTRIBUTE  = "evidence";

  public static final String SEQUENCE_MASS_ATTRIBUTE    = "mass";
  public static final String SEQUENCE_LENGTH_ATTRIBUTE  = "length";

  public static final String PROTEIN_TAG_NAME                   = "protein";
  public static final String PROTEIN_RECOMMENDED_NAME_TAG_NAME  = "recommendedName";
  public static final String PROTEIN_FULL_NAME_TAG_NAME         = "fullName";
  public static final String PROTEIN_SHORT_NAME_TAG_NAME        = "shortName"
  ;
  public static final String GENE_TAG_NAME        = "gene";
  public static final String GENE_NAME_TAG_NAME   = "name";

  public static final String COMMENT_TAG_NAME                   = "comment";
  public static final String COMMENT_TYPE_ATTRIBUTE             = "type";
  public static final String COMMENT_ALTERNATIVE_PRODUCTS_TYPE  = "alternative products";
  public static final String COMMENT_SEQUENCE_CAUTION_TYPE      = "sequence caution";
  public static final String SUBCELLULAR_LOCATION_TAG_NAME      = "subcellularLocation";

  public static final String LOCATION_TAG_NAME                    = "location";
  public static final String COMMENT_TEXT_TAG_NAME                = "text";
  public static final String FEATURE_TAG_NAME                     = "feature";
  public static final String FEATURE_TYPE_ATTRIBUTE               = "type";
  public static final String FEATURE_DESCRIPTION_ATTRIBUTE        = "description";
  public static final String STATUS_ATTRIBUTE                     = "status";
  public static final String FEATURE_REF_ATTRIBUTE                = "ref";
  public static final String FEATURE_ID_ATTRIBUTE                 = "id";
  public static final String EVIDENCE_ATTRIBUTE                   = "evidence";
  public static final String FEATURE_LOCATION_TAG_NAME            = "location";
  public static final String FEATURE_ORIGINAL_TAG_NAME            = "original";
  public static final String FEATURE_VARIATION_TAG_NAME           = "variation";
  public static final String FEATURE_POSITION_TAG_NAME            = "position";
  public static final String FEATURE_LOCATION_BEGIN_TAG_NAME      = "begin";
  public static final String FEATURE_LOCATION_END_TAG_NAME        = "end";
  public static final String FEATURE_LOCATION_POSITION_ATTRIBUTE  = "position";
  public static final String FEATURE_POSITION_POSITION_ATTRIBUTE  = "position";

  public static final String THESIS_CITATION_TYPE                   = "thesis";
  public static final String PATENT_CITATION_TYPE                   = "patent";
  public static final String SUBMISSION_CITATION_TYPE               = "submission";
  public static final String ARTICLE_CITATION_TYPE                  = "journal article";
  public static final String ONLINE_ARTICLE_CITATION_TYPE           = "online journal article";
  public static final String BOOK_CITATION_TYPE                     = "book";
  public static final String UNPUBLISHED_OBSERVATION_CITATION_TYPE  = "unpublished observations";

  public static final String COMMENT_TYPE_DISEASE                         = "disease";
  public static final String COMMENT_TYPE_FUNCTION                        = "function";
  public static final String COMMENT_TYPE_COFACTOR                        = "cofactor";
  public static final String COMMENT_TYPE_CATALYTIC_ACTIVITY              = "catalytic activity";
  public static final String COMMENT_TYPE_ENZYME_REGULATION               = "enzyme regulation";
  public static final String COMMENT_TYPE_BIOPHYSICOCHEMICAL_PROPERTIES   = "biophysicochemical properties";
  public static final String COMMENT_TYPE_SUBUNIT                         = "subunit";
  public static final String COMMENT_TYPE_PATHWAY                         = "pathway";
  public static final String COMMENT_TYPE_SUBCELLULAR_LOCATION            = "subcellular location";
  public static final String COMMENT_TYPE_TISSUE_SPECIFICITY              = "tissue specificity";
  public static final String COMMENT_TYPE_DEVELOPMENTAL_STAGE             = "developmental stage";
  public static final String COMMENT_TYPE_INDUCTION                       = "induction";
  public static final String COMMENT_TYPE_DOMAIN                          = "domain";
  public static final String COMMENT_TYPE_POST_TRANSLATIONAL_MODIFICATION = "PTM";
  public static final String COMMENT_TYPE_RNA_EDITING                     = "RNA editing";
  public static final String COMMENT_TYPE_MASS_SPECTROMETRY               = "mass spectrometry";
  public static final String COMMENT_TYPE_POLYMORPHISM                    = "polymorphism";
  public static final String COMMENT_TYPE_DISRUPTION_PHENOTYPE            = "disruption phenotype";
  public static final String COMMENT_TYPE_ALLERGEN                        = "allergen";
  public static final String COMMENT_TYPE_TOXIC_DOSE                      = "toxic dose";
  public static final String COMMENT_TYPE_BIOTECHNOLOGY                   = "biotechnology";
  public static final String COMMENT_TYPE_PHARMACEUTICAL                  = "pharmaceutical";
  public static final String COMMENT_TYPE_MISCELLANEOUS                   = "miscellaneous";
  public static final String COMMENT_TYPE_SIMILARITY                      = "similarity";
  public static final String COMMENT_TYPE_INTERACTION                     = "interaction";
}

```




[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../../go/GoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: XMLConstants.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../edges/ProteinGeneLocation.java.md