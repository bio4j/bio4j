package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
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
	public String sequence() {
		return get(type().sequence);
	}
	public String fullname() {
		return get(type().fullname);
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

	}
	public List<Enzyme<I, RV, RVT, RE, RET>>   enzymaticActivity_outNodes(){

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



}
