package com.bio4j.model.genomes.edges;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesGenome;
import com.bio4j.model.genomes.vertices.GenomesChromosome;


public final class GenomesGenomeChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        GenomesGraph.GenomesEdge<
                // src
                GenomesGenome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                // edge
                GenomesGenomeChromosome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeChromosomeType,
                //tgt
                GenomesChromosome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public GenomesGenomeChromosome(RE edge, GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeChromosomeType type) {

        super(edge, type);
    }


    @Override
    public GenomesGenomeChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }
}