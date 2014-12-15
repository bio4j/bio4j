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

	// proteinInterPro
	// outgoing
	public Optional<Stream<ProteinInterPro<I, RV, RVT, RE, RET>>> proteinInterPro_out(){
		return outManyOptional(graph().ProteinInterPro());
	}
	public Optional<Stream<InterPro<I, RV, RVT, RE, RET>>>  proteinInterPro_outV(){
		return outManyOptionalV(graph().ProteinInterPro());
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
