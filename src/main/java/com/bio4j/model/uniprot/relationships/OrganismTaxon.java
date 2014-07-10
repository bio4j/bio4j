package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.OrganismTaxonType;
import com.bio4j.model.uniprot.UniprotGraph.TaxonType;
import com.bio4j.model.uniprot.UniprotGraph.OrganismType;
import com.bio4j.model.uniprot.nodes.Organism;
import com.bio4j.model.uniprot.nodes.Taxon;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/10/2014.
 */
public interface OrganismTaxon<
		S extends Organism<S, ST>, ST extends OrganismType<S, ST>,
		R extends OrganismTaxon<S, ST, R, RT, T, TT>, RT extends OrganismTaxonType<S, ST, R, RT, T, TT>,
		T extends Taxon<T, TT>, TT extends TaxonType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
