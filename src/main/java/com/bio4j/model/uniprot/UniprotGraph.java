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

	public static interface BookType<
			N extends Book<N, NT>,
			NT extends BookType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface ThesisType<
			N extends Thesis<N, NT>,
			NT extends ThesisType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface ArticleType<
			N extends Article<N, NT>,
			NT extends ArticleType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface OnlineArticleType<
			N extends OnlineArticle<N, NT>,
			NT extends OnlineArticleType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface PubmedType<
			N extends Pubmed<N, NT>,
			NT extends PubmedType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface PublisherType<
			N extends Publisher<N, NT>,
			NT extends PublisherType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface PatentType<
			N extends Patent<N, NT>,
			NT extends PatentType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface JournalType<
			N extends Journal<N, NT>,
			NT extends JournalType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface InstituteType<
			N extends Institute<N, NT>,
			NT extends InstituteType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface CountryType<
			N extends Country<N, NT>,
			NT extends CountryType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface CityType<
			N extends City<N, NT>,
			NT extends CityType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface RefSeqType<
			N extends RefSeq<N, NT>,
			NT extends RefSeqType<N, NT>
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

	public static interface FeatureTypeType<
			N extends FeatureType<N, NT>,
			NT extends FeatureTypeType<N, NT>
			>
			extends Node.Type<N, NT> {
	}

	public static interface CommentTypeType<
			N extends CommentType<N, NT>,
			NT extends CommentTypeType<N, NT>
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

	public static interface ProteinRefSeqType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinRefSeq<S, ST, R, RT, T, TT>, RT extends ProteinRefSeqType<S, ST, R, RT, T, TT>,
			T extends RefSeq<T, TT>, TT extends RefSeqType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinFeatureType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinFeature<S, ST, R, RT, T, TT>, RT extends ProteinFeatureType<S, ST, R, RT, T, TT>,
			T extends FeatureType<T, TT>, TT extends FeatureTypeType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ProteinCommentType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinComment<S, ST, R, RT, T, TT>, RT extends ProteinCommentType<S, ST, R, RT, T, TT>,
			T extends CommentType<T, TT>, TT extends CommentTypeType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}

	public static interface ArticlePubmedType<
			S extends Article<S, ST>, ST extends ArticleType<S, ST>,
			R extends ArticlePubmed<S, ST, R, RT, T, TT>, RT extends ArticlePubmedType<S, ST, R, RT, T, TT>,
			T extends Pubmed<T, TT>, TT extends PubmedType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}
}
