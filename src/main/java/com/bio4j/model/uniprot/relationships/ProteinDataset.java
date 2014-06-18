package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.DatasetType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinDatasetType;
import com.bio4j.model.uniprot.nodes.Dataset;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface ProteinDataset<
		S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
		R extends ProteinDataset<S, ST, R, RT, T, TT>, RT extends ProteinDatasetType<S, ST, R, RT, T, TT>,
		T extends Dataset<T, TT>, TT extends DatasetType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
