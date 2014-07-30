package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.*;
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

	// enzymaticActivity
	// outgoing
	public EnzymaticActivity<I, RV, RVT, RE, RET>   enzymaticActivity_out();
	public Enzyme<I, RV, RVT, RE, RET>   enzymaticActivity_outNodes();

	// uniref50Member
	// ingoing
	public UniRef50Member<I, RV, RVT, RE, RET>   uniref50Member_in();
	public UniRef50Cluster<I, RV, RVT, RE, RET>  uniref50Member_inNode();

	// uniref50Representant
	// ingoing
	public UniRef50Representant<I, RV, RVT, RE, RET>   uniref50Representant_in();
	public UniRef50Cluster<I, RV, RVT, RE, RET>  uniref50Representant_inNode();

	// uniref90Member
	// ingoing
	public UniRef90Member<I, RV, RVT, RE, RET>   uniref90Member_in();
	public UniRef90Cluster<I, RV, RVT, RE, RET>  uniref90Member_inNode();

	// uniref90Representant
	// ingoing
	public UniRef90Representant<I, RV, RVT, RE, RET>  uniref90Representant_in();
	public UniRef90Cluster<I, RV, RVT, RE, RET>  uniref90Representant_inNode();

	// uniref100Member
	// ingoing
	public UniRef100Member<I, RV, RVT, RE, RET>   uniref100Member_in();
	public UniRef100Cluster<I, RV, RVT, RE, RET>  uniref100Member_inNode();

	// uniref90Representant
	// ingoing
	public UniRef100Representant<I, RV, RVT, RE, RET> uniref100Representant_in();
	public UniRef100Cluster<I, RV, RVT, RE, RET> uniref100Representant_inNode();

	// proteinReference
	// outgoing
	public List<ProteinReference<I, RV, RVT, RE, RET>> proteinReference_out(){
		return outMany(graph().ProteinReference());
	}
	public List<Reference<I, RV, RVT, RE, RET>> proteinReference_outNodes(){
		return outManyV(graph().ProteinReference());
	}



}
