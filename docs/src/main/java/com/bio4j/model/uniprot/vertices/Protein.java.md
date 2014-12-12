
```java
package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.model.uniprot_enzymedb.edges.EnzymaticActivity;
import com.bio4j.model.uniprot_go.edges.GoAnnotation;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Protein <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniProtVertex<
		Protein<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
		I, RV, RVT, RE, RET
		> {

	public Protein(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType type) {
		super(vertex, type);
	}

	@Override
	public Protein<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String accession() {
		return get(type().accession);
	}
	public String shortName() {
		return get(type().shortName);
	}
    public String name() { return get(type().name); }
	public String sequence() {
		return get(type().sequence);
	}
	public String fullName() {
	return get(type().fullName);
	}
	public Date modifiedDate() {
		return get(type().modifiedDate);
	}
	public Date createdDate() {
		return get(type().createdDate);
	}
	public String mass() {
		return get(type().mass);
	}
	public Integer version() {
		return get(type().version);
	}
	public Integer length() {
		return get(type().length);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// relationships
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// proteinOrganism
	// outgoing
	public ProteinOrganism<I, RV, RVT, RE, RET> proteinOrganism_out(){
		return outOne(graph().ProteinOrganism());
	}
	public Organism<I, RV, RVT, RE, RET>  proteinOrganism_outV(){
		return outOneV(graph().ProteinOrganism());
	}

	// proteinComment
	// outgoing
	public Optional<Stream<ProteinComment<I, RV, RVT, RE, RET>>> proteinComment_out(){
		return outManyOptional(graph().ProteinComment());
	}
	public Optional<Stream<CommentType<I, RV, RVT, RE, RET>>>  proteinComment_outV(){
		return outManyOptionalV(graph().ProteinComment());
	}

	// proteinRefSeq
	// outgoing
	public Optional<Stream<ProteinRefSeq<I, RV, RVT, RE, RET>>> proteinRefSeq_out(){
		return outManyOptional(graph().ProteinRefSeq());
	}
	public Optional<Stream<RefSeq<I, RV, RVT, RE, RET>>>  proteinRefSeq_outV(){
		return outManyOptionalV(graph().ProteinRefSeq());
	}

	// proteinSequenceCaution
	// outgoing
	public Optional<Stream<ProteinSequenceCaution<I, RV, RVT, RE, RET>>> proteinSequenceCaution_out(){
		return outManyOptional(graph().ProteinSequenceCaution());
	}
	public Optional<Stream<SequenceCaution<I, RV, RVT, RE, RET>>>  proteinSequenceCaution_outV(){
		return outManyOptionalV(graph().ProteinSequenceCaution());
	}

	// proteinSubcellularLocation
	// outgoing
	public Optional<Stream<ProteinSubcellularLocation<I, RV, RVT, RE, RET>>> proteinSubcellularLocation_out(){
		return outManyOptional(graph().ProteinSubcellularLocation());
	}
	public Optional<Stream<SubcellularLocation<I, RV, RVT, RE, RET>>>  proteinSubcellularLocation_outV(){
		return outManyOptionalV(graph().ProteinSubcellularLocation());
	}

	// proteinPIR
	// outgoing
	public Optional<Stream<ProteinPIR<I, RV, RVT, RE, RET>>> proteinPIR_out(){
		return outManyOptional(graph().ProteinPIR());
	}
	public Optional<Stream<PIR<I, RV, RVT, RE, RET>>>  proteinPIR_outV(){
		return outManyOptionalV(graph().ProteinPIR());
	}

	// proteinFeature
	// outgoing
	public Optional<Stream<ProteinFeature<I, RV, RVT, RE, RET>>> proteinFeature_out(){
		return outManyOptional(graph().ProteinFeature());
	}
	public Optional<Stream<FeatureType<I, RV, RVT, RE, RET>>>  proteinFeature_outV(){
		return outManyOptionalV(graph().ProteinFeature());
	}

	// proteinEMBL
	// outgoing
	public Optional<Stream<ProteinEMBL<I, RV, RVT, RE, RET>>> proteinEMBL_out(){
		return outManyOptional(graph().ProteinEMBL());
	}
	public Optional<Stream<EMBL<I, RV, RVT, RE, RET>>>  proteinEMBL_outV(){
		return outManyOptionalV(graph().ProteinEMBL());
	}

	// proteinDisease
	// outgoing
	public Optional<Stream<ProteinDisease<I, RV, RVT, RE, RET>>> proteinDisease_out(){
		return outManyOptional(graph().ProteinDisease());
	}
	public Optional<Stream<Disease<I, RV, RVT, RE, RET>>>  proteinDisease_outV(){
		return outManyOptionalV(graph().ProteinDisease());
	}

	// proteinDataset
	// outgoing
	public ProteinDataset<I, RV, RVT, RE, RET> proteinDataset_out(){
		return outOne(graph().ProteinDataset());
	}
	public Dataset<I, RV, RVT, RE, RET>  proteinDataset_outV(){
		return outOneV(graph().ProteinDataset());
	}

	// proteinInterpro
	// outgoing
	public Optional<Stream<ProteinInterpro<I, RV, RVT, RE, RET>>> proteinInterpro_out(){
		return outManyOptional(graph().ProteinInterpro());
	}
	public Optional<Stream<Interpro<I, RV, RVT, RE, RET>>>  proteinInterpro_outV(){
		return outManyOptionalV(graph().ProteinInterpro());
	}

	// proteinReactomeTerm
	// outgoing
	public Optional<Stream<ProteinReactomeTerm<I, RV, RVT, RE, RET>>> proteinReactomeTerm_out(){
		return outManyOptional(graph().ProteinReactomeTerm());
	}
	public Optional<Stream<ReactomeTerm<I, RV, RVT, RE, RET>>>  proteinReactomeTerm_outV(){
		return outManyOptionalV(graph().ProteinReactomeTerm());
	}

	// proteinEnsembl
	// outgoing
	public Optional<Stream<ProteinEnsembl<I, RV, RVT, RE, RET>>> proteinEnsembl_out(){
		return outManyOptional(graph().ProteinEnsembl());
	}
	public Optional<Stream<Ensembl<I, RV, RVT, RE, RET>>>  proteinEnsembl_outV(){
		return outManyOptionalV(graph().ProteinEnsembl());
	}

	// proteinKeyword
	// outgoing
	public Optional<Stream<ProteinKeyword<I, RV, RVT, RE, RET>>> proteinKeyword_out(){
		return outManyOptional(graph().ProteinKeyword());
	}
	public Optional<Stream<Keyword<I, RV, RVT, RE, RET>>>  proteinKeyword_outV(){
		return outManyOptionalV(graph().ProteinKeyword());
	}

	// proteinKegg
	// outgoing
	public Optional<Stream<ProteinKegg<I, RV, RVT, RE, RET>>> proteinKegg_out(){
		return outManyOptional(graph().ProteinKegg());
	}
	public Optional<Stream<Kegg<I, RV, RVT, RE, RET>>>  proteinKegg_outV(){
		return outManyOptionalV(graph().ProteinKegg());
	}

	// proteinPfam
	// outgoing
	public Optional<Stream<ProteinPfam<I, RV, RVT, RE, RET>>> proteinPfam_out(){
		return outManyOptional(graph().ProteinPfam());
	}
	public Optional<Stream<Pfam<I, RV, RVT, RE, RET>>>  proteinPfam_outV(){
		return outManyOptionalV(graph().ProteinPfam());
	}

	// proteinUniGene
	// outgoing
	public Optional<Stream<ProteinUniGene<I, RV, RVT, RE, RET>>> proteinUniGene_out(){
		return outManyOptional(graph().ProteinUniGene());
	}
	public Optional<Stream<UniGene<I, RV, RVT, RE, RET>>>  proteinUniGene_outV(){
		return outManyOptionalV(graph().ProteinUniGene());
	}

	// enzymaticActivity
	// outgoing
	public Optional<Stream<EnzymaticActivity<I, RV, RVT, RE, RET>>>   enzymaticActivity_out(){
        return outManyOptional(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}
	public Optional<Stream<Enzyme<I, RV, RVT, RE, RET>>>   enzymaticActivity_outV(){
        return outManyOptionalV(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}

	// goAnnotation
	// outgoing
	public Optional<Stream<GoAnnotation<I, RV, RVT, RE, RET>>>  goAnnotation_out(){
		return outManyOptional(graph().uniprotGoGraph().GoAnnotation());
	}
	public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>>   goAnnotation_outV(){
		return outManyOptionalV(graph().uniprotGoGraph().GoAnnotation());
	}

	// uniref50Member
	// outgoing
	public Optional<UniRef50Member<I, RV, RVT, RE, RET>> uniref50Member_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef50Member());
	}
	public Optional<UniRef50Cluster<I, RV, RVT, RE, RET>> uniref50Member_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef50Member());
	}

	// uniref50Representant
	// outgoing
	public Optional<UniRef50Representant<I, RV, RVT, RE, RET>> uniref50Representant_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef50Representant());
	}
	public Optional<UniRef50Cluster<I, RV, RVT, RE, RET>>  uniref50Representant_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef50Representant());
	}

	// uniref90Member
	// outgoing
	public Optional<UniRef90Member<I, RV, RVT, RE, RET>> uniref90Member_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef90Member());
	}
	public Optional<UniRef90Cluster<I, RV, RVT, RE, RET>> uniref90Member_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef90Member());
	}

	// uniref90Representant
	// outgoing
	public Optional<UniRef90Representant<I, RV, RVT, RE, RET>> uniref90Representant_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef90Representant());
	}
	public Optional<UniRef90Cluster<I, RV, RVT, RE, RET>> uniref90Representant_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef90Representant());
	}

	// uniref100Member
	// outgoing
	public Optional<UniRef100Member<I, RV, RVT, RE, RET>> uniref100Member_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef100Member());
	}
	public Optional<UniRef100Cluster<I, RV, RVT, RE, RET>> uniref100Member_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef100Member());
	}

	// uniref90Representant
	// outgoing
	public Optional<UniRef100Representant<I, RV, RVT, RE, RET>> uniref100Representant_out(){
		return outOneOptional(graph().uniprotUniRefGraph().UniRef100Representant());
	}
	public Optional<UniRef100Cluster<I, RV, RVT, RE, RET>> uniref100Representant_outV(){
		return outOneOptionalV(graph().uniprotUniRefGraph().UniRef100Representant());
	}

	// proteinReference
	// outgoing
	public Optional<Stream<ProteinReference<I, RV, RVT, RE, RET>>> proteinReference_out(){
		return outManyOptional(graph().ProteinReference());
	}
	public Optional<Stream<Reference<I, RV, RVT, RE, RET>>> proteinReference_outV(){
		return outManyOptionalV(graph().ProteinReference());
	}


	//----proteinNCBITaxon-------
	// outgoing
	public Optional<ProteinNCBITaxon<I, RV, RVT, RE, RET>> proteinNCBITaxon_out(){
		return outOneOptional(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}
	public Optional<NCBITaxon<I, RV, RVT, RE, RET>> proteinNCBITaxon_outV(){
		return outOneOptionalV(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}

	// proteinGeneLocation
	// outgoing
	public Optional<Stream<ProteinGeneLocation<I, RV, RVT, RE, RET>>> proteinGeneLocation_out(){
		return outManyOptional(graph().ProteinGeneLocation());
	}
	public Optional<Stream<GeneLocation<I, RV, RVT, RE, RET>>> proteinGeneLocation_outV(){
		return outManyOptionalV(graph().ProteinGeneLocation());
	}

	// proteinProteinInteraction
	// outgoing
	public Optional<Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>>> proteinProteinInteraction_out(){
		return outManyOptional(graph().ProteinProteinInteraction());
	}
	public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> proteinProteinInteraction_outV(){
		return outManyOptionalV(graph().ProteinProteinInteraction());
	}

	// proteinProteinInteraction
	// ingoing
	public Optional<Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>>> proteinProteinInteraction_in(){
		return inManyOptional(graph().ProteinProteinInteraction());
	}
	public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> proteinProteinInteraction_inV(){
		return inManyOptionalV(graph().ProteinProteinInteraction());
	}

	// proteinIsoformInteraction
	// outgoing
	public Optional<Stream<ProteinIsoformInteraction<I, RV, RVT, RE, RET>>> proteinIsoformInteraction_out(){
		return outManyOptional(graph().ProteinIsoformInteraction());
	}
	public Optional<Stream<Isoform<I, RV, RVT, RE, RET>>> proteinIsoformInteraction_outV(){
		return outManyOptionalV(graph().ProteinIsoformInteraction());
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
              + [UniProtUniRefGraph.java][main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]
              + edges
                + [UniRef100Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]
                + [UniRef50Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]
                + [UniRef100Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]
                + [UniRef90Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]
                + [UniRef50Representant.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]
              + programs
                + [ImportUniProtUniRef.java][main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]
              + [UniProtEnzymeDBGraph.java][main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]
              + programs
                + [ImportUniProtEnzymeDB.java][main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]
            + enzymedb
              + [EnzymeDBGraph.java][main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]
              + vertices
                + [Enzyme.java][main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]
              + programs
                + [ImportEnzymeDB.java][main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]
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
              + [UniProtGraph.java][main/java/com/bio4j/model/uniprot/UniProtGraph.java]
              + edges
                + [BookCity.java][main/java/com/bio4j/model/uniprot/edges/BookCity.java]
                + [ProteinReference.java][main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]
                + [ProteinIsoform.java][main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]
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
              + programs
                + [ImportUniProt.java][main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]
                + [ImportIsoformSequences.java][main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]
              + [UniProtNCBITaxonomyGraph.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]
              + programs
                + [ImportUniProtNCBITaxonomy.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]
            + geninfo
              + vertices
                + [GenInfo.java][main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]
              + [GenInfoGraph.java][main/java/com/bio4j/model/geninfo/GenInfoGraph.java]
            + ncbiTaxonomy_geninfo
              + [NCBITaxonomyGenInfoGraph.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]
              + edges
                + [GenInfoNCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]
              + programs
                + [ImportGenInfoNCBITaxonIndex.java][main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]
              + tests
                + [ImportUniProtGoTest.java][main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]
              + programs
                + [ImportUniProtGo.java][main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]
              + [UniProtGoGraph.java][main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]

[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
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
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../edges/ProteinIsoform.java.md
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
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ../programs/ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../../uniprot_go/UniProtGoGraph.java.md