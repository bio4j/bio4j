
```java
package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.SubmissionDB;
import com.bio4j.angulillos.UntypedGraph;

import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class DB <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends UniProtGraph.UniProtVertex<
  DB<I, RV, RVT, RE, RET>,
  UniProtGraph<I, RV, RVT, RE, RET>.DBType,
  I, RV, RVT, RE, RET
  > {

  public DB(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.DBType type) {
  super(vertex, type);
  }

  @Override
  public DB<I, RV, RVT, RE, RET> self() {
  return this;
  }

  // properties
  public String name() {
  return get(type().name);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////

  // relationships

  // submissionDB
  // ingoing
  public Stream<SubmissionDB<I, RV, RVT, RE, RET>> submissionDB_in(){
  return inMany(graph().SubmissionDB());
  }
  public Stream<Submission<I, RV, RVT, RE, RET>> submissionDB_inV(){
  return inManyV(graph().SubmissionDB());
  }


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
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ../programs/ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ../programs/ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: ../programs/XMLConstants.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ../programs/ImportUniProtVertices.java.md
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