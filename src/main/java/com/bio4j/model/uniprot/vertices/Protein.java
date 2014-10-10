package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniprotGraph;
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
import java.util.List;
import java.util.stream.Stream;

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
	public Organism<I, RV, RVT, RE, RET>  proteinOrganism_outV(){
		return outOneV(graph().ProteinOrganism());
	}

	// proteinComment
	// outgoing
	public Stream<ProteinComment<I, RV, RVT, RE, RET>> proteinComment_out(){
		return outMany(graph().ProteinComment());
	}
	public Stream<CommentType<I, RV, RVT, RE, RET>>  proteinComment_outV(){
		return outManyV(graph().ProteinComment());
	}

	// proteinRefSeq
	// outgoing
	public Stream<ProteinRefSeq<I, RV, RVT, RE, RET>> proteinRefSeq_out(){
		return outMany(graph().ProteinRefSeq());
	}
	public Stream<RefSeq<I, RV, RVT, RE, RET>>  proteinRefSeq_outV(){
		return outManyV(graph().ProteinRefSeq());
	}

	// proteinSequenceCaution
	// outgoing
	public Stream<ProteinSequenceCaution<I, RV, RVT, RE, RET>> proteinSequenceCaution_out(){
		return outMany(graph().ProteinSequenceCaution());
	}
	public Stream<SequenceCaution<I, RV, RVT, RE, RET>>  proteinSequenceCaution_outV(){
		return outManyV(graph().ProteinSequenceCaution());
	}

	// proteinSubcellularLocation
	// outgoing
	public Stream<ProteinSubcellularLocation<I, RV, RVT, RE, RET>> proteinSubcellularLocation_out(){
		return outMany(graph().ProteinSubcellularLocation());
	}
	public Stream<SubcellularLocation<I, RV, RVT, RE, RET>>  proteinSubcellularLocation_outV(){
		return outManyV(graph().ProteinSubcellularLocation());
	}

	// proteinPIR
	// outgoing
	public Stream<ProteinPIR<I, RV, RVT, RE, RET>> proteinPIR_out(){
		return outMany(graph().ProteinPIR());
	}
	public Stream<PIR<I, RV, RVT, RE, RET>>  proteinPIR_outV(){
		return outManyV(graph().ProteinPIR());
	}

	// proteinFeature
	// outgoing
	public Stream<ProteinFeature<I, RV, RVT, RE, RET>> proteinFeature_out(){
		return outMany(graph().ProteinFeature());
	}
	public Stream<FeatureType<I, RV, RVT, RE, RET>>  proteinFeature_outV(){
		return outManyV(graph().ProteinFeature());
	}

	// proteinEMBL
	// outgoing
	public Stream<ProteinEMBL<I, RV, RVT, RE, RET>> proteinEMBL_out(){
		return outMany(graph().ProteinEMBL());
	}
	public Stream<EMBL<I, RV, RVT, RE, RET>>  proteinEMBL_outV(){
		return outManyV(graph().ProteinEMBL());
	}

	// proteinDisease
	// outgoing
	public Stream<ProteinDisease<I, RV, RVT, RE, RET>> proteinDisease_out(){
		return outMany(graph().ProteinDisease());
	}
	public Stream<Disease<I, RV, RVT, RE, RET>>  proteinDisease_outV(){
		return outManyV(graph().ProteinDisease());
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
	public Stream<ProteinInterpro<I, RV, RVT, RE, RET>> proteinIntepro_out(){
		return outMany(graph().ProteinInterpro());
	}
	public Stream<Interpro<I, RV, RVT, RE, RET>>  proteinInterpro_outV(){
		return outManyV(graph().ProteinInterpro());
	}

	// proteinReactomeTerm
	// outgoing
	public Stream<ProteinReactomeTerm<I, RV, RVT, RE, RET>> proteinReactomeTerm_out(){
		return outMany(graph().ProteinReactomeTerm());
	}
	public Stream<ReactomeTerm<I, RV, RVT, RE, RET>>  proteinReactomeTerm_outV(){
		return outManyV(graph().ProteinReactomeTerm());
	}

	// proteinEnsembl
	// outgoing
	public Stream<ProteinEnsembl<I, RV, RVT, RE, RET>> proteinEnsembl_out(){
		return outMany(graph().ProteinEnsembl());
	}
	public Stream<Ensembl<I, RV, RVT, RE, RET>>  proteinEnsembl_outV(){
		return outManyV(graph().ProteinEnsembl());
	}

	// proteinKeyword
	// outgoing
	public Stream<ProteinKeyword<I, RV, RVT, RE, RET>> proteinKeyword_out(){
		return outMany(graph().ProteinKeyword());
	}
	public Stream<Keyword<I, RV, RVT, RE, RET>>  proteinKeyword_outV(){
		return outManyV(graph().ProteinKeyword());
	}

	// proteinKegg
	// outgoing
	public Stream<ProteinKegg<I, RV, RVT, RE, RET>> proteinKegg_out(){
		return outMany(graph().ProteinKegg());
	}
	public Stream<Kegg<I, RV, RVT, RE, RET>>  proteinKegg_outV(){
		return outManyV(graph().ProteinKegg());
	}

	// proteinPfam
	// outgoing
	public Stream<ProteinPfam<I, RV, RVT, RE, RET>> proteinPfam_out(){
		return outMany(graph().ProteinPfam());
	}
	public Stream<Pfam<I, RV, RVT, RE, RET>>  proteinPfam_outV(){
		return outManyV(graph().ProteinPfam());
	}

	// proteinUniGene
	// outgoing
	public Stream<ProteinUniGene<I, RV, RVT, RE, RET>> proteinUniGene_out(){
		return outMany(graph().ProteinUniGene());
	}
	public Stream<UniGene<I, RV, RVT, RE, RET>>  proteinUniGene_outV(){
		return outManyV(graph().ProteinUniGene());
	}

	// enzymaticActivity
	// outgoing
	public Stream<EnzymaticActivity<I, RV, RVT, RE, RET>>   enzymaticActivity_out(){
        return outMany(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}
	public Stream<Enzyme<I, RV, RVT, RE, RET>>   enzymaticActivity_outV(){
        return outManyV(graph().uniprotEnzymeDBGraph().EnzymaticActivity());
	}

	// goAnnotation
	// outgoing
	public Stream<GoAnnotation<I, RV, RVT, RE, RET>>  goAnnotation_out(){
		return outMany(graph().uniprotGoGraph().GoAnnotation());
	}
	public Stream<GoTerm<I, RV, RVT, RE, RET>>   goAnnotation_outV(){
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
	public Stream<ProteinReference<I, RV, RVT, RE, RET>> proteinReference_out(){
		return outMany(graph().ProteinReference());
	}
	public Stream<Reference<I, RV, RVT, RE, RET>> proteinReference_outV(){
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
	public Stream<ProteinGeneLocation<I, RV, RVT, RE, RET>> proteinGeneLocation_out(){
		return outMany(graph().ProteinGeneLocation());
	}
	public Stream<GeneLocation<I, RV, RVT, RE, RET>> proteinGeneLocation_outV(){
		return outManyV(graph().ProteinGeneLocation());
	}

	// proteinProteinInteraction
	// outgoing
	public Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>> proteinProteinInteraction_out(){
		return outMany(graph().ProteinProteinInteraction());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinProteinInteraction_outV(){
		return outManyV(graph().ProteinProteinInteraction());
	}

	// proteinProteinInteraction
	// ingoing
	public Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>> proteinProteinInteraction_in(){
		return inMany(graph().ProteinProteinInteraction());
	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinProteinInteraction_inV(){
		return inManyV(graph().ProteinProteinInteraction());
	}

	// proteinIsoformInteraction
	// outgoing
	public Stream<ProteinIsoformInteraction<I, RV, RVT, RE, RET>> proteinIsoformInteraction_out(){
		return outMany(graph().ProteinIsoformInteraction());
	}
	public Stream<Isoform<I, RV, RVT, RE, RET>> proteinIsoformInteraction_outV(){
		return outManyV(graph().ProteinIsoformInteraction());
	}
}
