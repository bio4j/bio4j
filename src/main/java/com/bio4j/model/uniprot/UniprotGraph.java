package com.bio4j.model.uniprot;

import com.bio4j.model.uniprot.nodes.*;
import com.bio4j.model.uniprot.relationships.*;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface UniprotGraph {

	public static interface ProteinType<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface InterproType<
			N extends Interpro<N, NT>,
			NT extends InterproType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface EMBLType<
			N extends EMBL<N, NT>,
			NT extends EMBLType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface EnsemblType<
			N extends Ensembl<N, NT>,
			NT extends EnsemblType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface KeggType<
			N extends Kegg<N, NT>,
			NT extends KeggType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface PIRType<
			N extends PIR<N, NT>,
			NT extends PIRType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface UniGeneType<
			N extends UniGene<N, NT>,
			NT extends UniGeneType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface PfamType<
			N extends Pfam<N, NT>,
			NT extends PfamType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface ReactomeTermType<
			N extends ReactomeTerm<N, NT>,
			NT extends ReactomeTermType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface KeywordType<
			N extends Keyword<N, NT>,
			NT extends KeywordType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface OrganismType<
			N extends Organism<N, NT>,
			NT extends OrganismType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface DatasetType<
			N extends Dataset<N, NT>,
			NT extends DatasetType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface TaxonType<
			N extends Taxon<N, NT>,
			NT extends TaxonType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface ProteinDatasetType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinDataset<S, ST, R, RT, T, TT>, RT extends ProteinDatasetType<S, ST, R, RT, T, TT>,
			T extends Dataset<T, TT>, TT extends DatasetType<T, TT>
			>
			extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinOrganismType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinOrganism<S, ST, R, RT, T, TT>, RT extends ProteinOrganismType<S, ST, R, RT, T, TT>,
			T extends Organism<T, TT>, TT extends OrganismType<T, TT>
			>
			extends Relationship.Type.ManyToOne<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinPIRType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinPIR<S, ST, R, RT, T, TT>, RT extends ProteinPIRType<S, ST, R, RT, T, TT>,
			T extends PIR<T, TT>, TT extends PIRType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinKeywordType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinKeyword<S, ST, R, RT, T, TT>, RT extends ProteinKeywordType<S, ST, R, RT, T, TT>,
			T extends Keyword<T, TT>, TT extends KeywordType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinUniGeneType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinUniGene<S, ST, R, RT, T, TT>, RT extends ProteinUniGeneType<S, ST, R, RT, T, TT>,
			T extends UniGene<T, TT>, TT extends UniGeneType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinKeggType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinKegg<S, ST, R, RT, T, TT>, RT extends ProteinKeggType<S, ST, R, RT, T, TT>,
			T extends Kegg<T, TT>, TT extends KeggType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinEMBLType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinEMBL<S, ST, R, RT, T, TT>, RT extends ProteinEMBLType<S, ST, R, RT, T, TT>,
			T extends EMBL<T, TT>, TT extends EMBLType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinEnsemblType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinEnsembl<S, ST, R, RT, T, TT>, RT extends ProteinEnsemblType<S, ST, R, RT, T, TT>,
			T extends Ensembl<T, TT>, TT extends EnsemblType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinInterproType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinInterpro<S, ST, R, RT, T, TT>, RT extends ProteinInterproType<S, ST, R, RT, T, TT>,
			T extends Interpro<T, TT>, TT extends InterproType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinPfamType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinPfam<S, ST, R, RT, T, TT>, RT extends ProteinPfamType<S, ST, R, RT, T, TT>,
			T extends Pfam<T, TT>, TT extends PfamType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinReactomeTermType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinReactomeTerm<S, ST, R, RT, T, TT>, RT extends ProteinReactomeTermType<S, ST, R, RT, T, TT>,
			T extends ReactomeTerm<T, TT>, TT extends ReactomeTermType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface TaxonParentType<
			S extends Taxon<S, ST>, ST extends TaxonType<S, ST>,
			R extends TaxonParent<S, ST, R, RT, T, TT>, RT extends TaxonParentType<S, ST, R, RT, T, TT>,
			T extends Taxon<T, TT>, TT extends TaxonType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}

	public static interface OrganismTaxonType<
			S extends Organism<S, ST>, ST extends OrganismType<S, ST>,
			R extends OrganismTaxon<S, ST, R, RT, T, TT>, RT extends OrganismTaxonType<S, ST, R, RT, T, TT>,
			T extends Taxon<T, TT>, TT extends TaxonType<T, TT>
			>
			extends Relationship.Type.OneToOne<S, ST, R, RT, T, TT> {
	}
}
