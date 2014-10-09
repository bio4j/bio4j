
```java
package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.ReferenceOnlineArticle;
import com.ohnosequences.typedGraphs.UntypedGraph;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class OnlineArticle <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		OnlineArticle<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType,
		I, RV, RVT, RE, RET
		>  {

	public OnlineArticle(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.OnlineArticleType type) {
		super(vertex, type);
	}

	@Override
	public OnlineArticle<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referenceOnlineArticle
	// ingoing
	public ReferenceOnlineArticle<I, RV, RVT, RE, RET> referenceOnlineArticle_in(){
		return inOne(graph().ReferenceOnlineArticle());
	}
	public Reference<I, RV, RVT, RE, RET> referenceOnlineArticle_inV(){
		return inOneV(graph().ReferenceOnlineArticle());
	}
}

```


------

### Index

+ src
  + main
    + java
      + com
        + bio4j
          + model
            + uniref
              + vertices
                + [UniRef100Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]
                + [UniRef90Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]
                + [UniRef50Cluster.java][main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]
              + [UniRefGraph.java][main/java/com/bio4j/model/uniref/UniRefGraph.java]
              + programs
                + [ImportUniRef.java][main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]
            + uniprot_uniref
              + edges
                + [UniRef100Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]
                + [UniRef50Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]
                + [UniRef100Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]
                + [UniRef90Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]
                + [UniRef50Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]
              + [UniprotUniRefGraph.java][main/java/com/bio4j/model/uniprot_uniref/UniprotUniRefGraph.java]
              + programs
                + [ImportUniprotUniRef.java][main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniprotUniRef.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]
              + [UniprotEnzymeDBGraph.java][main/java/com/bio4j/model/uniprot_enzymedb/UniprotEnzymeDBGraph.java]
              + programs
                + [ImportUniprotEnzymeDB.java][main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java]
            + enzymedb
              + [EnzymeDBGraph.java][main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]
              + vertices
                + [Enzyme.java][main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]
              + programs
                + [ImportEnzymeDB.java][main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]
            + enums
            + ncbiTaxonomy
              + [NCBITaxonomyGraph.java][main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]
              + edges
                + [NCBITaxonParent.java][main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]
              + vertices
                + [NCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]
              + programs
                + [ImportNCBITaxonomy.java][main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]
            + go
              + edges
                + goSlims
                  + [GoSlim.java][main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]
                  + [PlantSlim.java][main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]
                + [PositivelyRegulates.java][main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]
                + [HasPartOf.java][main/java/com/bio4j/model/go/edges/HasPartOf.java]
                + [Regulates.java][main/java/com/bio4j/model/go/edges/Regulates.java]
                + [PartOf.java][main/java/com/bio4j/model/go/edges/PartOf.java]
                + [IsA.java][main/java/com/bio4j/model/go/edges/IsA.java]
                + [NegativelyRegulates.java][main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]
                + [SubOntology.java][main/java/com/bio4j/model/go/edges/SubOntology.java]
              + [GoGraph.java][main/java/com/bio4j/model/go/GoGraph.java]
              + vertices
                + [SubOntologies.java][main/java/com/bio4j/model/go/vertices/SubOntologies.java]
                + [GoTerm.java][main/java/com/bio4j/model/go/vertices/GoTerm.java]
                + [GoSlims.java][main/java/com/bio4j/model/go/vertices/GoSlims.java]
              + programs
                + [ImportGO.java][main/java/com/bio4j/model/go/programs/ImportGO.java]
            + uniprot
              + edges
                + [BookCity.java][main/java/com/bio4j/model/uniprot/edges/BookCity.java]
                + [ProteinReference.java][main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]
                + [ProteinFeature.java][main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]
                + [ReferenceThesis.java][main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]
                + [ArticlePubmed.java][main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]
                + [ReferenceAuthorConsortium.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]
                + [ReferencePatent.java][main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]
                + [TaxonParent.java][main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]
                + [ProteinReactomeTerm.java][main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]
                + [ProteinDisease.java][main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]
                + [BookPublisher.java][main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]
                + [InstituteCountry.java][main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]
                + [ReferenceOnlineArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]
                + [BookEditor.java][main/java/com/bio4j/model/uniprot/edges/BookEditor.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]
                + [ReferenceSubmission.java][main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]
                + [ProteinEnsembl.java][main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]
                + [SubcellularLocationParent.java][main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]
                + [ProteinComment.java][main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]
                + [ArticleJournal.java][main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]
                + [ProteinPIR.java][main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]
                + [SubmissionDB.java][main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]
                + [OnlineArticleOnlineJournal.java][main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]
                + [OrganismTaxon.java][main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]
                + [ThesisInstitute.java][main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]
                + [ProteinUniGene.java][main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]
                + [ProteinKegg.java][main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]
                + [ProteinRefSeq.java][main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]
                + [ReferenceUnpublishedObservation.java][main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]
                + [ReferenceBook.java][main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]
                + [IsoformEventGenerator.java][main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]
                + [IsoformProteinInteraction.java][main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]
                + [ReferenceArticle.java][main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]
                + [ProteinInterpro.java][main/java/com/bio4j/model/uniprot/edges/ProteinInterpro.java]
                + [ProteinEMBL.java][main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]
                + [ProteinSequenceCaution.java][main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]
                + [ReferenceAuthorPerson.java][main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]
                + [ProteinGeneLocation.java][main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]
              + vertices
                + [Article.java][main/java/com/bio4j/model/uniprot/vertices/Article.java]
                + [Taxon.java][main/java/com/bio4j/model/uniprot/vertices/Taxon.java]
                + [Publisher.java][main/java/com/bio4j/model/uniprot/vertices/Publisher.java]
                + [Book.java][main/java/com/bio4j/model/uniprot/vertices/Book.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]
                + [GeneLocation.java][main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]
                + [Person.java][main/java/com/bio4j/model/uniprot/vertices/Person.java]
                + [SubcellularLocation.java][main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]
                + [FeatureType.java][main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]
                + [PIR.java][main/java/com/bio4j/model/uniprot/vertices/PIR.java]
                + [ReactomeTerm.java][main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]
                + [Thesis.java][main/java/com/bio4j/model/uniprot/vertices/Thesis.java]
                + [Ensembl.java][main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]
                + [Keyword.java][main/java/com/bio4j/model/uniprot/vertices/Keyword.java]
                + [Protein.java][main/java/com/bio4j/model/uniprot/vertices/Protein.java]
                + [Submission.java][main/java/com/bio4j/model/uniprot/vertices/Submission.java]
                + [CommentType.java][main/java/com/bio4j/model/uniprot/vertices/CommentType.java]
                + [Pubmed.java][main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]
                + [Reference.java][main/java/com/bio4j/model/uniprot/vertices/Reference.java]
                + [UniGene.java][main/java/com/bio4j/model/uniprot/vertices/UniGene.java]
                + [SequenceCaution.java][main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]
                + [EMBL.java][main/java/com/bio4j/model/uniprot/vertices/EMBL.java]
                + [DB.java][main/java/com/bio4j/model/uniprot/vertices/DB.java]
                + [City.java][main/java/com/bio4j/model/uniprot/vertices/City.java]
                + [AlternativeProduct.java][main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]
                + [Institute.java][main/java/com/bio4j/model/uniprot/vertices/Institute.java]
                + [Isoform.java][main/java/com/bio4j/model/uniprot/vertices/Isoform.java]
                + [RefSeq.java][main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]
                + [Kegg.java][main/java/com/bio4j/model/uniprot/vertices/Kegg.java]
                + [Consortium.java][main/java/com/bio4j/model/uniprot/vertices/Consortium.java]
                + [Pfam.java][main/java/com/bio4j/model/uniprot/vertices/Pfam.java]
                + [Disease.java][main/java/com/bio4j/model/uniprot/vertices/Disease.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]
                + [Patent.java][main/java/com/bio4j/model/uniprot/vertices/Patent.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]
                + [Interpro.java][main/java/com/bio4j/model/uniprot/vertices/Interpro.java]
                + [Organism.java][main/java/com/bio4j/model/uniprot/vertices/Organism.java]
                + [Dataset.java][main/java/com/bio4j/model/uniprot/vertices/Dataset.java]
                + [Journal.java][main/java/com/bio4j/model/uniprot/vertices/Journal.java]
                + [Country.java][main/java/com/bio4j/model/uniprot/vertices/Country.java]
              + [UniprotGraph.java][main/java/com/bio4j/model/uniprot/UniprotGraph.java]
              + programs
                + [ImportIsoformSequences.java][main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]
                + [ImportUniprot.java][main/java/com/bio4j/model/uniprot/programs/ImportUniprot.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]
              + [UniprotNCBITaxonomyGraph.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java]
            + geninfo
              + vertices
                + [GenInfo.java][main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]
              + [GenInfoGraph.java][main/java/com/bio4j/model/geninfo/GenInfoGraph.java]
            + ncbiTaxonomy_geninfo
              + [NCBITaxonomyGenInfoGraph.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]
              + edges
                + [GenInfoNCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]
              + [UniprotGoGraph.java][main/java/com/bio4j/model/uniprot_go/UniprotGoGraph.java]
              + programs
                + [ImportUniprotGo.java][main/java/com/bio4j/model/uniprot_go/programs/ImportUniprotGo.java]

[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniprotUniRefGraph.java]: ../../uniprot_uniref/UniprotUniRefGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniprotUniRef.java]: ../../uniprot_uniref/programs/ImportUniprotUniRef.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniprotEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniprotEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniprotEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/go/edges/goSlims/GoSlim.java]: ../../go/edges/goSlims/GoSlim.java.md
[main/java/com/bio4j/model/go/edges/goSlims/PlantSlim.java]: ../../go/edges/goSlims/PlantSlim.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../../go/GoGraph.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterpro.java]: ../edges/ProteinInterpro.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../edges/ProteinGeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: Person.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: City.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Interpro.java]: Interpro.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: Country.java.md
[main/java/com/bio4j/model/uniprot/UniprotGraph.java]: ../UniprotGraph.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniprot.java]: ../programs/ImportUniprot.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniprotNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/UniprotGoGraph.java]: ../../uniprot_go/UniprotGoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniprotGo.java]: ../../uniprot_go/programs/ImportUniprotGo.java.md