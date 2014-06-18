package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinOrganismType;
import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface ProteinOrganism<
		S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
		R extends ProteinOrganism<S, ST, R, RT, T, TT>, RT extends ProteinOrganismType<S, ST, R, RT, T, TT>,
		T extends Organism<T, TT>, TT extends OrganismType<T, TT>
>
		extends Relationship<S, ST, R, RT, T, TT> { {
}
