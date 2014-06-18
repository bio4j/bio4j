package com.bio4j.model.uniprot;

import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Node;

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
}
