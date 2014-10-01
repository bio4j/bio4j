package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.bio4j.model.uniprot_ncbiTaxonomy.relationships.ProteinNCBITaxon;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.Date;
import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Protein <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Protein<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
		I, RV, RVT, RE, RET
		> {

	public Protein(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType type) {
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
	public Organism<I, RV, RVT, RE, RET>  proteinOrganism_outNodes(){
		return outOneV(graph().ProteinOrganism());
	}

	// proteinComment
	// outgoing
	public List<ProteinComment<I, RV, RVT, RE, RET>> proteinComment_out(){
		return outMany(graph().ProteinComment());
	}
	public List<CommentType<I, RV, RVT, RE, RET>>  proteinComment_outNodes(){
		return outManyV(graph().ProteinComment());
	}

	// proteinRefSeq
	// outgoing
	public List<ProteinRefSeq<I, RV, RVT, RE, RET>> proteinRefSeq_out(){
		return outMany(graph().ProteinRefSeq());
	}
	public List<RefSeq<I, RV, RVT, RE, RET>>  proteinRefSeq_outNodes(){
		return outManyV(graph().ProteinRefSeq());
	}

	// proteinSequenceCaution
	// outgoing
	public List<ProteinSequenceCaution<I, RV, RVT, RE, RET>> proteinSequenceCaution_out(){
		return outMany(graph().ProteinSequenceCaution());
	}
	public List<SequenceCaution<I, RV, RVT, RE, RET>>  proteinSequenceCaution_outNodes(){
		return outManyV(graph().ProteinSequenceCaution());
	}

	// proteinSubcellularLocation
	// outgoing
	public List<ProteinSubcellularLocation<I, RV, RVT, RE, RET>> proteinSubcellularLocation_out(){
		return outMany(graph().ProteinSubcellularLocation());
	}
	public List<SubcellularLocation<I, RV, RVT, RE, RET>>  proteinSubcellularLocation_outNodes(){
		return outManyV(graph().ProteinSubcellularLocation());
	}

	// proteinPIR
	// outgoing
	public List<ProteinPIR<I, RV, RVT, RE, RET>> proteinPIR_out(){
		return outMany(graph().ProteinPIR());
	}
	public List<PIR<I, RV, RVT, RE, RET>>  proteinPIR_outNodes(){
		return outManyV(graph().ProteinPIR());
	}

	// proteinFeature
	// outgoing
	public List<ProteinFeature<I, RV, RVT, RE, RET>> proteinFeature_out(){
		return outMany(graph().ProteinFeature());
	}
	public List<FeatureType<I, RV, RVT, RE, RET>>  proteinFeature_outNodes(){
		return outManyV(graph().ProteinFeature());
	}

	// proteinEMBL
	// outgoing
	public List<ProteinEMBL<I, RV, RVT, RE, RET>> proteinEMBL_out(){
		return outMany(graph().ProteinEMBL());
	}
	public List<EMBL<I, RV, RVT, RE, RET>>  proteinEMBL_outNodes(){
		return outManyV(graph().ProteinEMBL());
	}

	// proteinDisease
	// outgoing
	public List<ProteinDisease<I, RV, RVT, RE, RET>> proteinDisease_out(){
		return outMany(graph().ProteinDisease());
	}
	public List<Disease<I, RV, RVT, RE, RET>>  proteinDisease_outNodes(){
		return outManyV(graph().ProteinDisease());
	}

	// proteinDataset
	// outgoing
	public ProteinDataset<I, RV, RVT, RE, RET> proteinDataset_out(){
		return outOne(graph().ProteinDataset());
	}
	public Dataset<I, RV, RVT, RE, RET>  proteinDataset_outNodes(){
		return outOneV(graph().ProteinDataset());
	}

	// proteinInterpro
	// outgoing
	public List<ProteinInterpro<I, RV, RVT, RE, RET>> proteinIntepro_out(){
		return outMany(graph().ProteinInterpro());
	}
	public List<Interpro<I, RV, RVT, RE, RET>>  proteinInterpro_outNodes(){
		return outManyV(graph().ProteinInterpro());
	}

	// proteinReactomeTerm
	// outgoing
	public List<ProteinReactomeTerm<I, RV, RVT, RE, RET>> proteinReactomeTerm_out(){
		return outMany(graph().ProteinReactomeTerm());
	}
	public List<ReactomeTerm<I, RV, RVT, RE, RET>>  proteinReactomeTerm_outNodes(){
		return outManyV(graph().ProteinReactomeTerm());
	}

	// proteinEnsembl
	// outgoing
	public List<ProteinEnsembl<I, RV, RVT, RE, RET>> proteinEnsembl_out(){
		return outMany(graph().ProteinEnsembl());
	}
	public List<Ensembl<I, RV, RVT, RE, RET>>  proteinEnsembl_outNodes(){
		return outManyV(graph().ProteinEnsembl());
	}

	// proteinKeyword
	// outgoing
	public List<ProteinKeyword<I, RV, RVT, RE, RET>> proteinKeyword_out(){
		return outMany(graph().ProteinKeyword());
	}
	public List<Keyword<I, RV, RVT, RE, RET>>  proteinKeyword_outNodes(){
		return outManyV(graph().ProteinKeyword());
	}

	// proteinKegg
	// outgoing
	public List<ProteinKegg<I, RV, RVT, RE, RET>> proteinKegg_out(){
		return outMany(graph().ProteinKegg());
	}
	public List<Kegg<I, RV, RVT, RE, RET>>  proteinKegg_outNodes(){
		return outManyV(graph().ProteinKegg());
	}

	// proteinPfam
	// outgoing
	public List<ProteinPfam<I, RV, RVT, RE, RET>> proteinPfam_out(){
		return outMany(graph().ProteinPfam());
	}
	public List<Pfam<I, RV, RVT, RE, RET>>  proteinPfam_outNodes(){
		return outManyV(graph().ProteinPfam());
	}

	// proteinUniGene
	// outgoing
	public List<ProteinUniGene<I, RV, RVT, RE, RET>> proteinUniGene_out(){
		return outMany(graph().ProteinUniGene());
	}
	public List<UniGene<I, RV, RVT, RE, RET>>  proteinUniGene_outNodes(){
		return outManyV(graph().ProteinUniGene());
	}

	// enzymaticActivity
	// outgoing
	public List<EnzymaticActivity<I, RV, RVT, RE, RET>>   enzymaticActivity_out(){
        return outMany(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}
	public List<Enzyme<I, RV, RVT, RE, RET>>   enzymaticActivity_outNodes(){
        return outManyV(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}

	// goAnnotation
	// outgoing
	public List<GoAnnotation<I, RV, RVT, RE, RET>>  goAnnotation_out(){
		return outMany(graph().uniprotGoGraph().GoAnnotation());
	}
	public List<GoTerm<I, RV, RVT, RE, RET>>   goAnnotation_outNodes(){
		return outManyV(graph().uniprotGoGraph().GoAnnotation());
	}

	// uniref50Member
	// outgoing
	public UniRef50Member<I, RV, RVT, RE, RET> uniref50Member_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef50Member());
	}
	public UniRef50Cluster<I, RV, RVT, RE, RET> uniref50Member_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef50Member());
	}

	// uniref50Representant
	// outgoing
	public UniRef50Representant<I, RV, RVT, RE, RET> uniref50Representant_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef50Representant());
	}
	public UniRef50Cluster<I, RV, RVT, RE, RET>  uniref50Representant_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef50Representant());
	}

	// uniref90Member
	// outgoing
	public UniRef90Member<I, RV, RVT, RE, RET> uniref90Member_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef90Member());
	}
	public UniRef90Cluster<I, RV, RVT, RE, RET>  uniref90Member_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef90Member());
	}

	// uniref90Representant
	// outgoing
	public UniRef90Representant<I, RV, RVT, RE, RET> uniref90Representant_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef90Representant());
	}
	public UniRef90Cluster<I, RV, RVT, RE, RET> uniref90Representant_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef90Representant());
	}

	// uniref100Member
	// outgoing
	public UniRef100Member<I, RV, RVT, RE, RET> uniref100Member_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef100Member());
	}
	public UniRef100Cluster<I, RV, RVT, RE, RET> uniref100Member_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef100Member());
	}

	// uniref90Representant
	// outgoing
	public UniRef100Representant<I, RV, RVT, RE, RET> uniref100Representant_out(){
		return outOne(graph().uniprotUniRefGraph().UniRef100Representant());
	}
	public UniRef100Cluster<I, RV, RVT, RE, RET> uniref100Representant_outNode(){
		return outOneV(graph().uniprotUniRefGraph().UniRef100Representant());
	}

	// proteinReference
	// outgoing
	public List<ProteinReference<I, RV, RVT, RE, RET>> proteinReference_out(){
		return outMany(graph().ProteinReference());
	}
	public List<Reference<I, RV, RVT, RE, RET>> proteinReference_outNodes(){
		return outManyV(graph().ProteinReference());
	}


	//----proteinNCBITaxon-------
	// outgoing
	public ProteinNCBITaxon<I, RV, RVT, RE, RET> proteinNCBITaxon_out(){
		return outOne(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}
	public NCBITaxon<I, RV, RVT, RE, RET> proteinNCBITaxon_outV(){
		return outOneV(graph().uniprotNCBITaxonomyGraph().ProteinNCBITaxon());
	}

	// proteinGeneLocation
	// outgoing
	public List<ProteinGeneLocation<I, RV, RVT, RE, RET>> proteinGeneLocation_out(){
		return outMany(graph().ProteinGeneLocation());
	}
	public List<GeneLocation<I, RV, RVT, RE, RET>> proteinGeneLocation_outNodes(){
		return outManyV(graph().ProteinGeneLocation());
	}

	// proteinProteinInteraction
	// outgoing
	public List<ProteinProteinInteraction<I, RV, RVT, RE, RET>> proteinProteinInteraction_out(){
		return outMany(graph().ProteinProteinInteraction());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinProteinInteraction_outV(){
		return outManyV(graph().ProteinProteinInteraction());
	}

	// proteinProteinInteraction
	// ingoing
	public List<ProteinProteinInteraction<I, RV, RVT, RE, RET>> proteinProteinInteraction_in(){
		return inMany(graph().ProteinProteinInteraction());
	}
	public List<Protein<I, RV, RVT, RE, RET>> proteinProteinInteraction_inV(){
		return inManyV(graph().ProteinProteinInteraction());
	}

	// proteinIsoformInteraction
	// outgoing
	public List<ProteinIsoformInteraction<I, RV, RVT, RE, RET>> proteinIsoformInteraction_out(){
		return outMany(graph().ProteinIsoformInteraction());
	}
	public List<Isoform<I, RV, RVT, RE, RET>> proteinIsoformInteraction_outV(){
		return outManyV(graph().ProteinIsoformInteraction());
	}
}
