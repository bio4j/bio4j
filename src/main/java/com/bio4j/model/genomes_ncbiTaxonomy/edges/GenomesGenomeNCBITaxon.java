package com.bio4j.model.genomes_ncbiTaxonomy.edges;

import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesGenome;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.genomes_ncbiTaxonomy.GenomesNCBITaxonomyGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class GenomesGenomeNCBITaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        GenomesNCBITaxonomyGraph.GenomesNCBITaxonomyEdge<
                // src
                GenomesGenome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                GenomesGraph<I, RV, RVT, RE, RET>,
                // edge
                GenomesGenomeNCBITaxon<I, RV, RVT, RE, RET>,
                GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesGenomeNCBITaxonType,
                //tgt
                NCBITaxon<I, RV, RVT, RE, RET>,
                NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
                NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public GenomesGenomeNCBITaxon(RE edge, GenomesNCBITaxonomyGraph<I, RV, RVT, RE, RET>.GenomesGenomeNCBITaxonType type) {
        super(edge, type);
    }


    @Override
    public GenomesGenomeNCBITaxon<I, RV, RVT, RE, RET> self() {
        return this;
    }
}