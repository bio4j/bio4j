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

	public static interface ProteinKeywordType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinKeyword<S, ST, R, RT, T, TT>, RT extends ProteinKeywordType<S, ST, R, RT, T, TT>,
			T extends Keyword<T, TT>, TT extends KeywordType<T, TT>
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

	public static interface ProteinReactomeTermType<
			S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
			R extends ProteinReactomeTerm<S, ST, R, RT, T, TT>, RT extends ProteinReactomeTermType<S, ST, R, RT, T, TT>,
			T extends ReactomeTerm<T, TT>, TT extends ReactomeTermType<T, TT>
			>
			extends Relationship.Type.ManyToMany<S, ST, R, RT, T, TT> {
	}
}
