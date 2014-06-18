package com.bio4j.model.uniprot;

import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/18/2014.
 */
public class UniprotGraph {

	public static interface ProteinType<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>
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
}
